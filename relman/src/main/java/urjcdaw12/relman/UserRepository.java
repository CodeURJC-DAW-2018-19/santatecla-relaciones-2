package urjcdaw12.relman;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	
	 User findByName(String name);
	 
}
