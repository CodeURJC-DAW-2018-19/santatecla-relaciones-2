package urjcdaw12.relman.Units;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UnidadRepository extends JpaRepository<Unit,Long>{
	List<Unit> findByName(String nombre);
}
