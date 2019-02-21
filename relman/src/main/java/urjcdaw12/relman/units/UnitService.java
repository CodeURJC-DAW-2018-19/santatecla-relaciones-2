package urjcdaw12.relman.units;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UnitService {
	
	@Autowired
	private UnitRepository unitRep;
	
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


	public Unit findByName(String nombre){
		return unitRep.findByName(nombre);
	}

	
	public long totalElements(){
		return unitRep.count();
	}
}
