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

import com.fasterxml.jackson.annotation.JsonView;

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

	@GetMapping(value = "/context")
	@ResponseStatus(HttpStatus.OK)
	public List<Page<Relation>> getRelations(Pageable page, @PathVariable String unitName) {
		Unit unit = unitServ.findByName(unitName);

		return relationServ.findContextByName(unit, page);
	}

	@JsonView(Relation.Basic.class)
	@GetMapping(value = "/relation/{type}")
	public ResponseEntity<Page<Relation>> getRelation(@PathVariable String type, @PathVariable String unitName, Pageable page) {
		Unit unit = unitServ.findByName(unitName);
		if (unit != null) {
			return new ResponseEntity<>(relationServ.findByNameAndConcreteType(unit, type, page), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//EL TYPE NO SIRVE PARA NADA NO? METES LA RELACION YA CON SU TIPO ENTRE SUS ATRIBUTOS
	@PostMapping(value = "/relation/{type}")
	public ResponseEntity<Relation> postRelation(@RequestBody Relation relation, @PathVariable String type, @PathVariable String unitName, Pageable page) {
		Unit unit = unitServ.findByName(unitName);
		//if (relationServ.findByNameAndConcreteType(unit, type, page) == null) { //EN LA WEB TENEMOS QUE SE PUEDEN DUPLICADOS...
			relationServ.save(relation);
			return new ResponseEntity<>(relation, HttpStatus.OK);
		//} else {
		//	return new ResponseEntity<>(HttpStatus.CONFLICT);
		//}
	}
/*
	@DeleteMapping(value = "/{type}/{unitRelated}")
	public ResponseEntity<Relation> deleteUnit(@PathVariable String unitName, @PathVariable String type, @PathVariable String unitRelated) {
		Unit unit = unitServ.findByName(unitName);
		Unit uRelated = unitServ.findByName(unitRelated);


		String newType = relationServ.translateTypeRelation(type);
		String decide = relationServ.decideOriginOrDestiny(type);
		
		if (decide.equals("origin")) {
			Relation relation = relationServ.findByTypeAndDestiny(newType, uRelated).get(0);
			relationServ.delete(relation);
			return new ResponseEntity<>(relation, HttpStatus.OK);
		}else if (decide.equals("destiny")) {
			Relation relation = relationServ.findByTypeAndOrigin(newType, uRelated).get(0);
			relationServ.delete(relation);
			return new ResponseEntity<>(relation, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
*/
	
	@DeleteMapping(value = "/relation/{type}/{unitRelated}")
	public ResponseEntity<Relation> deleteRelation(@PathVariable String unitName, @PathVariable String type, @PathVariable String unitRelated) {
		Unit unit = unitServ.findByName(unitName);
		Unit uRelated = unitServ.findByName(unitRelated);

		String newType = relationServ.translateTypeRelation(type);
		String decide = relationServ.decideOriginOrDestiny(type);
		
		Relation relation = null;
		
		if (decide.equals("origin")) {
			relation = relationServ.findByTypeAndOriginAndDestiny(newType, unit, uRelated);
		}else if (decide.equals("destiny")) {
			relation = relationServ.findByTypeAndOriginAndDestiny(newType, uRelated, unit);
		}
		
		if (relation!=null) {
			relationServ.delete(relation);
			return new ResponseEntity<>(relation, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
