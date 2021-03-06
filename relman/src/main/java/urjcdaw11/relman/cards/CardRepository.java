package urjcdaw11.relman.cards;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import urjcdaw11.relman.units.Unit;

public interface CardRepository extends JpaRepository<Card, Long> {
	List<Card> findByType(String type);

	List<Card> findByUnitAsoc(Unit unit);

	Card findByUnitAsocAndType(Unit unit, String type);
	
	Page<Card> findByUnitAsoc(Unit unit, Pageable page);
}
