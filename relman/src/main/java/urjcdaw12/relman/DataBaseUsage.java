package urjcdaw12.relman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DataBaseUsage implements CommandLineRunner {
	
	@Autowired
	private UnidadRepository unidadRep;

	@Override
	public void run(String... args) throws Exception {
		unidadRep.save(new Unit("HTML"));
		unidadRep.save(new Unit("CSS"));
		unidadRep.save(new Unit("JAVASCRIPT"));
		unidadRep.save(new Unit("API REST"));
		unidadRep.save(new Unit("SPRING"));
		unidadRep.save(new Unit("ANGULAR"));
		
		

		
	}

}
