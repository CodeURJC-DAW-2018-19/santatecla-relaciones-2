package urjcdaw12.relman;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import urjcdaw12.relman.cards.Card;
import urjcdaw12.relman.cards.CardService;
import urjcdaw12.relman.relations.Relation;
import urjcdaw12.relman.relations.RelationService;
import urjcdaw12.relman.units.Unit;
import urjcdaw12.relman.units.UnitService;
import urjcdaw12.relman.users.User;
import urjcdaw12.relman.users.UserService;

@Component
public class DatabaseInitializer {

	@Autowired
	private RelationService relationServ;

	@Autowired
	private UnitService unitServ;

	@Autowired
	UserService userServ;

	@Autowired
	CardService cardServ;

	@PostConstruct
	public void init() {

		// Units
		Unit html = unitServ.save(new Unit("HTML"));
		Unit html2 = unitServ.save(new Unit("HTML2.1"));
		Unit html4 = unitServ.save(new Unit("HTML4.0"));

		unitServ.save(new Unit("CSS"));
		unitServ.save(new Unit("JAVASCRIPT"));
		unitServ.save(new Unit("API_REST"));
		unitServ.save(new Unit("SPRING"));
		unitServ.save(new Unit("ANGULAR"));
		Unit sgml = unitServ.save(new Unit("SGML"));
		Unit maqWeb = unitServ.save(new Unit("MAQUETACIÓN_WEB"));

		Unit c1 = unitServ.save(new Unit("COMP1"));
		Unit c2 = unitServ.save(new Unit("COMP2"));

		Unit usa1 = unitServ.save(new Unit("USA1"));
		Unit usa2 = unitServ.save(new Unit("USA2"));

		Unit asociados1 = unitServ.save(new Unit("ASOCIADOS1"));
		Unit asociados2 = unitServ.save(new Unit("ASOCIADOS2"));

		Unit asociado1 = unitServ.save(new Unit("ASOCIADO1"));
		Unit asociado2 = unitServ.save(new Unit("ASOCIADO2"));

		Unit usan1 = unitServ.save(new Unit("USAN1"));
		Unit usan2 = unitServ.save(new Unit("USAN2"));

		Unit partes1 = unitServ.save(new Unit("PARTES1"));
		Unit partes2 = unitServ.save(new Unit("PARTES2"));

		relationServ.save(new Relation("Herencia", sgml, html));
		relationServ.save(new Relation("Herencia", maqWeb, html));

		relationServ.save(new Relation("Herencia", html, html2));
		relationServ.save(new Relation("Herencia", html, html4));

		relationServ.save(new Relation("Composición", c1, html));
		relationServ.save(new Relation("Composición", c2, html));

		relationServ.save(new Relation("Composición", html, partes1));
		relationServ.save(new Relation("Composición", html, partes2));

		relationServ.save(new Relation("Uso", usan1, html));
		relationServ.save(new Relation("Uso", usan2, html));

		relationServ.save(new Relation("Uso", html, usa1));
		relationServ.save(new Relation("Uso", html, usa2));

		relationServ.save(new Relation("Asociación", asociados1, html));
		relationServ.save(new Relation("Asociación", asociados2, html));

		relationServ.save(new Relation("Asociación", html, asociado1));
		relationServ.save(new Relation("Asociación", html, asociado2));
		
		
		
		//Testing Composition Hierarchy
		relationServ.save(new Relation("Composición", partes1, usan1));
		relationServ.save(new Relation("Composición", partes1, usan2));
		relationServ.save(new Relation("Composición", usan1, asociado1));
		relationServ.save(new Relation("Composición", usan2, asociado2));
		relationServ.save(new Relation("Composición", asociado2, maqWeb));

		
		
		

		userServ.save(new User("user", "12345", "ROLE_USER"));
		userServ.save(new User("student", "12345", "ROLE_USER"));
		userServ.save(new User("teacher", "12345", "ROLE_USER", "ROLE_ADMIN"));

		cardServ.save(new Card("Por qué", "Porque...", html));
		cardServ.save(new Card("Cuándo", "Cuando...", html));
		cardServ.save(new Card("Cómo", "Así...", html));
		cardServ.save(new Card("Para qué", "Para...", html));
		cardServ.save(new Card("Quién", "Este...", html));
		cardServ.save(new Card("Dónde", "Donde...", html));

	}

}
