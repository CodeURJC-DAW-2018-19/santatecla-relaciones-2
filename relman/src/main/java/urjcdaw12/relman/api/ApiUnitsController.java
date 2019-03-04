package urjcdaw12.relman.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import urjcdaw12.relman.units.Unit;
import urjcdaw12.relman.units.UnitService;

@RestController
@RequestMapping(value = "/api")
public class ApiUnitsController {

	@Autowired
	private UnitService unitServ;

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
			return new ResponseEntity<>(unit, HttpStatus.OK);
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
