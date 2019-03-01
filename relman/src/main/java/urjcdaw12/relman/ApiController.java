package urjcdaw12.relman;

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

import urjcdaw12.relman.units.Unit;
import urjcdaw12.relman.units.UnitService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private UnitService unitServ;

	@GetMapping(value = "/units")
	@ResponseStatus(HttpStatus.OK)
	public Page<Unit> getUnits(Pageable page) {

		return unitServ.findAll(page);
		
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
	@ResponseStatus(HttpStatus.CREATED)
	public Unit nuevoAnuncio(@RequestBody Unit unit) {
		if (unitServ.findByName(unit.getName()) == null) {
			unitServ.save(unit);
		}
		return unit;
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
