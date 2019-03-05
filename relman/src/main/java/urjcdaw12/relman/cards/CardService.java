package urjcdaw12.relman.cards;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import urjcdaw12.relman.units.Unit;

@Service
public class CardService {

	@Autowired
	private CardRepository cardRep;
	
	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");

	@PostConstruct
	public void init() throws IOException {
		if (!Files.exists(FILES_FOLDER)) {
			Files.createDirectories(FILES_FOLDER);
		}
	}

	public Optional<Card> findOne(long id) {
		return cardRep.findById(id);
	}

	public List<Card> findAll() {
		return cardRep.findAll();
	}
	
	public Page<Card>findByUnitAsoc(Unit unit, Pageable page) {
		return cardRep.findByUnitAsoc(unit, page);
	}

	public Card save(Card card) {
		return cardRep.save(card);
	}

	public void delete(long id) {
		cardRep.deleteById(id);
	}

	public List<Card> findByType(String type) {
		return cardRep.findByType(type);
	}

	public List<Card> findByUnitAsoc(Unit unit) {
		return cardRep.findByUnitAsoc(unit);
	}

	public Card findByUnitAsocAndType(Unit unit, String type) {
		return cardRep.findByUnitAsocAndType(unit, type);
	}
	
	public void saveImage(MultipartFile file, String unit, Card card, String type) throws Exception{
		File uploadedFile = new File(FILES_FOLDER.toFile(), unit + type);
		file.transferTo(uploadedFile);
		card.setPhoto(true);
		this.save(card);
	}
	
	public Path getImage(String unit, String type) {
		return FILES_FOLDER.resolve(unit + type);
	}

}
