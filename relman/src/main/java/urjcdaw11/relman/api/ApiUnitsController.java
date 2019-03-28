package urjcdaw11.relman.api;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import urjcdaw11.relman.relations.RelationService;
import urjcdaw11.relman.units.Unit;
import urjcdaw11.relman.units.UnitService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/api")
public class ApiUnitsController {

	@Autowired
	private UnitService unitServ;

	@Autowired
	private RelationService relationServ;

	@GetMapping(value = "/units")
	@ResponseStatus(HttpStatus.OK)
	public Page<Unit> getUnits(Pageable page, @RequestParam Optional<String> search) {
		if (!search.isPresent()) {
			return unitServ.findAll(page);
		} else {
			return unitServ.findSearch(page, search.get());
		}
	}

	@GetMapping(value = "/unit/{unitName}")
	public ResponseEntity<Unit> getUnit(@PathVariable String unitName) {
		Unit unit = unitServ.findByName(unitName);

		if (unit != null) {
			if (relationServ.findByTypeAndOrigin("composition", unit).size() != 0) {
				unitServ.createCompUML(unit);
			}

			if (relationServ.findByTypeAndOrigin("inheritance", unit).size() != 0) {
				unitServ.createClasUML(unit);
			}

			return new ResponseEntity<>(unit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/unit/{unitName}/image/{typeImage}")
	public ResponseEntity<byte[]> getImage(@PathVariable String unitName, @PathVariable String typeImage)
			throws IOException {
		Unit unit = unitServ.findByName(unitName);

		if (unit != null) {
			byte[] bytes = null;

			if (typeImage.equals("context")) {
				unitServ.createContextUML(unit);
				bytes = Files.readAllBytes(unitServ.getImage(unitName, "context"));
			} else if (typeImage.equals("classification")
					&& relationServ.findByTypeAndOrigin("inheritance", unit).size() != 0) {
				unitServ.createClasUML(unit);
				bytes = Files.readAllBytes(unitServ.getImage(unitName, "clas"));
			} else if (typeImage.equals("composition")
					&& relationServ.findByTypeAndOrigin("composition", unit).size() != 0) {
				unitServ.createCompUML(unit);
				bytes = Files.readAllBytes(unitServ.getImage(unitName, "comp"));
			}

			if (bytes != null) {
				final HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.IMAGE_JPEG);
				return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/unit")
	public ResponseEntity<Unit> postUnit(@RequestBody Unit unit) {
		if (unitServ.findByName(unit.getName()) == null) {
			unitServ.save(unit);
			return new ResponseEntity<>(unit, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping(value = "/unit/{unitName}")
	public ResponseEntity<Unit> deleteUnit(@PathVariable String unitName) {
		Unit unit = unitServ.findByName(unitName);

		if (unit != null) {
			unitServ.delete(unit);
			return new ResponseEntity<>(unit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
