package urjcdaw12.relman.Units;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UnitService {
	
	@Autowired
	private UnidadRepository unidadRep;
	
	public Optional<Unit> findOne(long id) {
		return unidadRep.findById(id);
	}

	public Page<Unit> findAll(Pageable page) {
		return unidadRep.findAll(page);
	}


	public Unit save(Unit unit) {
		return unidadRep.save(unit);
	}

	public void delete(long id) {
		unidadRep.deleteById(id);
	}
	
	public void delete(Unit unit) {
		unidadRep.delete(unit);
	}


	public Unit findByName(String nombre){
		return unidadRep.findByName(nombre);
	}

	
	public long totalElements(){
		return unidadRep.count();
	}
}
