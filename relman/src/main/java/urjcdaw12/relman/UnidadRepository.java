package urjcdaw12.relman;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UnidadRepository extends JpaRepository<Unidad,Long>{
	List<Unidad> findByNombre(String nombre);
}
