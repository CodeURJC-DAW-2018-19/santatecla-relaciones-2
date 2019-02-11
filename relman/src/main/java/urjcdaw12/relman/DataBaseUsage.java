package urjcdaw12.relman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DataBaseUsage implements CommandLineRunner {
	
	@Autowired
	private UnidadRepository unidadRep;

	@Override
	public void run(String... args) throws Exception {
		unidadRep.save(new Unidad("HTML"));
		unidadRep.save(new Unidad("CSS"));
		unidadRep.save(new Unidad("JAVASCRIPT"));
		unidadRep.save(new Unidad("API REST"));
		unidadRep.save(new Unidad("SPRING"));
		unidadRep.save(new Unidad("ANGULAR"));
		
		

		
	}

}
