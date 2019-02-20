package urjcdaw12.relman.Units;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadRepository extends JpaRepository<Unit,Long>{
	Unit findByName(String nombre);
}
