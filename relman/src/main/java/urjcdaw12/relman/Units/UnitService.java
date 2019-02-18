package urjcdaw12.relman.Units;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UnitService {
	
	@Autowired
	private UnidadRepository unidadRep;
	
	public Optional<Unit> findOne(long id) {
		return unidadRep.findById(id);
	}

	public List<Unit> findAll() {
		return unidadRep.findAll();
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


	public List<Unit> findByName(String nombre){
		return unidadRep.findByName(nombre);
	}
}
