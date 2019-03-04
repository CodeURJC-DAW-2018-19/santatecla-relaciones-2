package urjcdaw12.relman.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import urjcdaw12.relman.cards.Card;
import urjcdaw12.relman.cards.CardService;
import urjcdaw12.relman.units.Unit;
import urjcdaw12.relman.units.UnitService;

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
		return cardServ.findByUnitAsoc(unit,page);
	}
	
	@GetMapping(value = "/card/{type}")
	public ResponseEntity<Card> getCard(@PathVariable String unitName, @PathVariable String type){
		Unit unit = unitServ.findByName(unitName);
		Card card =cardServ.findByUnitAsocAndType(unit, type);
		
		if (card!=null) {
			return new ResponseEntity<>(card, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/card/{type}")
	public ResponseEntity<Card> postCard(@RequestBody Card card,@PathVariable String unitName, @PathVariable String type){
		Unit unit = unitServ.findByName(unitName);
		
		if (cardServ.findByUnitAsocAndType(unit, type)==null) {
			cardServ.save(card);
			return new ResponseEntity<>(card, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping(value ="/card/{type}")
	public ResponseEntity<Card> deleteCard(@PathVariable String unitName, @PathVariable String type){
		Unit unit = unitServ.findByName(unitName);
		Card card = cardServ.findByUnitAsocAndType(unit, type);
		if (card !=null) {
			cardServ.delete(card.getId());
			return new ResponseEntity<>(card, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
