package urjcdaw12.relman;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class DatabaseInitializer {

	@Autowired
	private RelationRepository relationRep;

	@Autowired
	private UnidadRepository unidadRep;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CardRepository cardRepository;

	@PostConstruct
	public void init() {

		// Units
		Unit html= unidadRep.save(new Unit("HTML"));
		Unit html2= unidadRep.save(new Unit("HTML2.1"));
		Unit html4= unidadRep.save(new Unit("HTML4.0"));

		Unit css = unidadRep.save(new Unit("CSS"));
		Unit javascript = unidadRep.save(new Unit("JAVASCRIPT"));
		Unit apiRest = unidadRep.save(new Unit("API_REST"));
		Unit spring= unidadRep.save(new Unit("SPRING"));
		Unit angular = unidadRep.save(new Unit("ANGULAR"));
		Unit sgml = unidadRep.save(new Unit("SGML"));
		Unit maqWeb = unidadRep.save(new Unit("MAQUETACIÓN_WEB"));
		
		Unit c1 = unidadRep.save(new Unit("COMP1"));
		Unit c2 = unidadRep.save(new Unit("COMP2"));
		
		Unit usa1 = unidadRep.save(new Unit("USA1"));
		Unit usa2 = unidadRep.save(new Unit("USA2"));
		
		Unit asociados1 = unidadRep.save(new Unit("ASOCIADOS1"));
		Unit asociados2 = unidadRep.save(new Unit("ASOCIADOS2"));
		
		Unit asociado1 = unidadRep.save(new Unit("ASOCIADO1"));
		Unit asociado2 = unidadRep.save(new Unit("ASOCIADO2"));

		Unit usan1 = unidadRep.save(new Unit("USAN1"));
		Unit usan2 = unidadRep.save(new Unit("USAN2"));
		
		Unit partes1 = unidadRep.save(new Unit("PARTES1"));
		Unit partes2 = unidadRep.save(new Unit("PARTES2"));
		
		
		relationRep.save(new Relation("Herencia",sgml,html));
		relationRep.save(new Relation("Herencia",maqWeb,html));
		
		relationRep.save(new Relation("Herencia",html,html2));
		relationRep.save(new Relation("Herencia",html,html4));
		
		relationRep.save(new Relation("Composición",c1,html));
		relationRep.save(new Relation("Composición",c2,html));

		relationRep.save(new Relation("Composición",html,partes1));
		relationRep.save(new Relation("Composición",html,partes2));
		
		relationRep.save(new Relation("Uso",usan1,html));
		relationRep.save(new Relation("Uso",usan2,html));

		relationRep.save(new Relation("Uso",html,usa1));
		relationRep.save(new Relation("Uso",html,usa2));
		
		relationRep.save(new Relation("Asociación",asociados1,html));
		relationRep.save(new Relation("Asociación",asociados2,html));

		relationRep.save(new Relation("Asociación",html,asociado1));
		relationRep.save(new Relation("Asociación",html,asociado2));
		
		userRepository.save(new User("user@user","12345","ROLE_USER"));
		userRepository.save(new User("student","12345","ROLE_USER"));
		userRepository.save(new User("teacher@teacher","adminpass","ROLE_USER","ROLE_ADMIN"));
		
		cardRepository.save(new Card("Por qué","Porque...",html));
		cardRepository.save(new Card("Cuándo","Cuando...",html));
		cardRepository.save(new Card("Cómo","Así...",html));
		cardRepository.save(new Card("Para qué","Para...",html));
		cardRepository.save(new Card("Quién","Este...",html));
		cardRepository.save(new Card("Dónde","Donde...",html));



		
	}

}
