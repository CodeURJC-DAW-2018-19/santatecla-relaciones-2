package urjcdaw12.relman;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<Relation,Long>{
	List<Relation> findByTypeAndOrigin(String type, Unit origin);
	List<Relation> findByTypeAndDestiny(String type, Unit destiny);


}
