package urjcdaw12.relman.units;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UnitRepository extends JpaRepository<Unit, Long> {

	Unit findByName(String nombre);

	@Query("SELECT u FROM Unit u WHERE u.name LIKE %:search%")
	Page<Unit> findSearch(Pageable page, @Param("search") String search);
}
