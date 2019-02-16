package urjcdaw12.relman;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long>{
	List<Card>findByType(String type);
	List<Card>findByUnitAsoc(Unit unit);
}
