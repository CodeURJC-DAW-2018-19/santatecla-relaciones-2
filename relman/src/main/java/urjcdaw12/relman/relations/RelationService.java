package urjcdaw12.relman.relations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import urjcdaw12.relman.units.Unit;

@Service
public class RelationService {

	
	@Autowired
	private RelationRepository relationRep;
	
	public Optional<Relation> findOne(long id) {
		return relationRep.findById(id);
	}

	public List<Relation> findAll() {
		return relationRep.findAll();
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
	
	public List<Relation> findByTypeAndOrigin(String type, Unit origin){
		return relationRep.findByTypeAndOrigin(type, origin);
	}
	
	public List<Relation> findByTypeAndDestiny(String type, Unit destiny){
		return relationRep.findByTypeAndDestiny(type, destiny);
	}
}
