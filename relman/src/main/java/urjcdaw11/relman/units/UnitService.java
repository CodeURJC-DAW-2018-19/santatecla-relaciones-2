package urjcdaw11.relman.units;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import urjcdaw11.relman.UMLCreator;

@Service
public class UnitService {

	@Autowired
	private UnitRepository unitRep;
	
	@Autowired
	private UMLCreator umlCreator;
	
	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");

	@PostConstruct
	public void init() throws IOException {
		if (!Files.exists(FILES_FOLDER)) {
			Files.createDirectories(FILES_FOLDER);
		}
	}

	public Optional<Unit> findOne(long id) {
		return unitRep.findById(id);
	}

	public Page<Unit> findAll(Pageable page) {
		return unitRep.findAll(page);
	}

	public Page<Unit> findSearch(Pageable page, String search) {
		return unitRep.findSearch(page, search);
	}

	public Unit save(Unit unit) {
		return unitRep.save(unit);
	}

	public void delete(long id) {
		unitRep.deleteById(id);
	}

	public void delete(Unit unit) {
		unitRep.delete(unit);
	}

	public Unit findByName(String nombre) {
		return unitRep.findByName(nombre);
	}

	public long totalElements() {
		return unitRep.count();
	}
	
	
	public Path getImage(String unit, String type) {
		return FILES_FOLDER.resolve(type + unit + ".png");
	}

	
	public void createCompUML(Unit unitConc) {
		 umlCreator.compositionUML(unitConc);
	}
	
	public void createClasUML(Unit unitConc) {
		 umlCreator.clasificationUML(unitConc);
	}
	
	public void createContextUML(Unit unitConc) {
		 umlCreator.contextUML(unitConc);
	}
}
