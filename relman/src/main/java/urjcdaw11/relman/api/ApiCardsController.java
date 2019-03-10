package urjcdaw11.relman.api;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import urjcdaw11.relman.cards.Card;
import urjcdaw11.relman.cards.CardService;
import urjcdaw11.relman.units.Unit;
import urjcdaw11.relman.units.UnitService;

@RestController
@RequestMapping(value = "/api/unit/{unitName}")
public class ApiCardsController {

	@Autowired
	private CardService cardServ;

	@Autowired
	private UnitService unitServ;

	@GetMapping(value = "/cards")
	@ResponseStatus(HttpStatus.OK)
	public Page<Card> getCards(Pageable page, @PathVariable String unitName) {
		Unit unit = unitServ.findByName(unitName);
		return cardServ.findByUnitAsoc(unit, page);
	}

	@GetMapping(value = "/card/{type}")
	public ResponseEntity<Card> getCard(@PathVariable String unitName, @PathVariable String type) {
		Unit unit = unitServ.findByName(unitName);
		Card card = cardServ.findByUnitAsocAndType(unit, type);

		if (card != null) {
			return new ResponseEntity<>(card, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/card")
	public ResponseEntity<Card> postCard(@RequestBody Card card, @PathVariable String unitName) {
		Unit unit = unitServ.findByName(unitName);

		if (cardServ.findByUnitAsocAndType(unit, card.getType()) == null) {
			cardServ.save(new Card(card.getType(), card.getDesc(), unit));
			return new ResponseEntity<>(card, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping(value = "/card/{type}")
	public ResponseEntity<Card> deleteCard(@PathVariable String unitName, @PathVariable String type) {
		Unit unit = unitServ.findByName(unitName);
		Card card = cardServ.findByUnitAsocAndType(unit, type);
		if (card != null) {
			cardServ.delete(card.getId());
			return new ResponseEntity<>(card, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/card/{type}/image")
	public ResponseEntity<byte[]> getImage(@PathVariable String unitName, @PathVariable String type)
			throws IOException {
		Unit unit = unitServ.findByName(unitName);
		Card card = cardServ.findByUnitAsocAndType(unit, type);

		if (card != null) {
			if (!card.isPhoto()){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			byte[] bytes = Files.readAllBytes(cardServ.getImage(unitName, type));
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/card/{type}/image")
	public ResponseEntity<byte[]> postImage(@RequestBody MultipartFile file, @PathVariable String unitName,
			@PathVariable String type) throws Exception {
		if(file == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Unit unit = unitServ.findByName(unitName);
		Card card = cardServ.findByUnitAsocAndType(unit, type);
		if(unit == null || card == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		cardServ.saveImage(file, unitName, card, type);
		byte[] bytes = Files.readAllBytes(cardServ.getImage(unitName, type));
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}

}
