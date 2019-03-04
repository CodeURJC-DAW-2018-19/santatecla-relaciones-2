package urjcdaw12.relman.api;

import java.util.List;

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

import urjcdaw12.relman.relations.Relation;
import urjcdaw12.relman.relations.RelationService;
import urjcdaw12.relman.units.Unit;
import urjcdaw12.relman.units.UnitService;

@RestController
@RequestMapping(value = "/api/unit/{unitName}")
public class ApiRelationsController {

	@Autowired
	private RelationService relationServ;

	@Autowired
	private UnitService unitServ;

	@GetMapping(value = "/relations")
	@ResponseStatus(HttpStatus.OK)
	public Page<Relation> getRelations(Pageable page) {
		return relationServ.findAll(page);
	}

	@GetMapping(value = "/{type}")
	public ResponseEntity<Page<Relation>> getUnit(@PathVariable String type, @PathVariable String unitName, Pageable page) {
		Unit unit = unitServ.findByName(unitName);
		if (unit != null) {
			return new ResponseEntity<>(relationServ.findByNameAndConcreteType(unit, type, page), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/unit/{unitName}")
	public ResponseEntity<Unit> postUnit(@RequestBody Unit unit) {
		if (unitServ.findByName(unit.getName()) == null) {
			unitServ.save(unit);
			return new ResponseEntity<>(unit, HttpStatus.OK);
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
