package urjcdaw12.relman.relations;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import urjcdaw12.relman.units.Unit;

@Service
public class RelationService {

	@Autowired
	private RelationRepository relationRep;

	public Optional<Relation> findOne(long id) {
		return relationRep.findById(id);
	}
	
	public List<Page<Relation>> findContextByName(Unit unit, Pageable page){
		List list = new LinkedList();
		list.add(this.findByTypeAndDestiny("inheritance", unit, page));
		list.add(this.findByTypeAndOrigin("inheritance", unit, page));
		list.add(this.findByTypeAndDestiny("composition", unit, page));
		list.add(this.findByTypeAndOrigin("composition", unit, page));
		list.add(this.findByTypeAndDestiny("use", unit, page));
		list.add(this.findByTypeAndOrigin("use", unit, page));
		list.add(this.findByTypeAndDestiny("association", unit, page));
		list.add(this.findByTypeAndOrigin("association", unit, page));

		return list;
	}

	public List<Relation> findAll() {
		return relationRep.findAll();
	}
	
	public Page<Relation> findAll(Pageable page) {
		return relationRep.findAll(page);
	}

	public Relation save(Relation relation) {
		return relationRep.save(relation);
	}

	public void delete(long id) {
		relationRep.deleteById(id);
	}

	public void delete(Relation relation) {
		relationRep.delete(relation);
	}

	public List<Relation> findByTypeAndOrigin(String type, Unit origin) {
		return relationRep.findByTypeAndOrigin(type, origin);
	}

	public List<Relation> findByTypeAndDestiny(String type, Unit destiny) {
		return relationRep.findByTypeAndDestiny(type, destiny);
	}

	public Page<Relation> findByTypeAndOrigin(String type, Unit origin, Pageable page) {
		return relationRep.findByTypeAndOrigin(type, origin, page);
	}

	public Page<Relation> findByTypeAndDestiny(String type, Unit destiny, Pageable page) {
		return relationRep.findByTypeAndDestiny(type, destiny, page);
	}
	
	
	//@return the concrete part of a relation (Inheritance->Children||Parents)
	public Page<Relation> findByNameAndConcreteType(Unit unit, String type,Pageable page){
		String relType=translateTypeRelation(type);
		String decide = this.decideOriginOrDestiny(type);
		
		
		if (decide.equals("destiny")) {
			return this.findByTypeAndDestiny (relType,unit, page);
		} else if (decide.equals("origin")) {
			return this.findByTypeAndOrigin (relType,unit, page);
		} else {
			return null;
		}
	}
		
	public String translateTypeRelation(String type) {
		String relType = "";
		if (type.equals("parents") || type.equals("children")) {
			relType = "inheritance";
		}
		if (type.equals("composites") || type.equals("parts")) {
			relType = "composition";
		}
		if (type.equals("associatedBy") || type.equals("associatedTo")) {
			relType = "association";
		}
		if (type.equals("uses") || type.equals("usedBy")) {
			relType = "use";
		}
		return relType;
	}
	
	
	//Method to decide the search by origin or destiny
	public String decideOriginOrDestiny(String type) {
		if (type.equals("parents") || type.equals("composites") || type.equals("associatedBy")
				|| type.equals("usedBy")) {
			return "destiny";
		} else if (type.equals("uses") || type.equals("parts") || type.equals("children")
				|| type.equals("associatedTo")) {
			return "origin";
		} else {
			return null;
		}
	}
	
	
	public Relation findByTypeAndOriginAndDestiny(String type, Unit origin, Unit destiny){
		return relationRep.findByTypeAndOriginAndDestiny(type, origin, destiny);
	}
		
		
	
}
