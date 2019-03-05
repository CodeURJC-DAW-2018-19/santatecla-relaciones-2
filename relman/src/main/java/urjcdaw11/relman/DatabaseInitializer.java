package urjcdaw11.relman;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import urjcdaw11.relman.cards.Card;
import urjcdaw11.relman.cards.CardService;
import urjcdaw11.relman.relations.Relation;
import urjcdaw11.relman.relations.RelationService;
import urjcdaw11.relman.units.Unit;
import urjcdaw11.relman.units.UnitService;
import urjcdaw11.relman.users.User;
import urjcdaw11.relman.users.UserService;

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
		Unit css = unitServ.save(new Unit("CSS"));
		Unit js = unitServ.save(new Unit("JAVASCRIPT"));
		Unit apirest = unitServ.save(new Unit("API_REST"));
		Unit spring = unitServ.save(new Unit("SPRING"));
		Unit angular = unitServ.save(new Unit("ANGULAR"));
		Unit xml = unitServ.save(new Unit("XML"));
		Unit sgml = unitServ.save(new Unit("SGML"));
		Unit maqWeb = unitServ.save(new Unit("MAQUETACIÓN_WEB"));
		Unit etiq = unitServ.save(new Unit("ETIQUETAS"));
		Unit disWeb = unitServ.save(new Unit("DISEÑO_WEB"));
		Unit estCss = unitServ.save(new Unit("ESTILOS_CSS"));
		Unit java = unitServ.save(new Unit("JAVA"));
		Unit segSpring = unitServ.save(new Unit("SEGURIDAD_SPRING"));
		Unit docker = unitServ.save(new Unit("DOCKER_SPRING"));
		Unit imag = unitServ.save(new Unit("IMAGENES"));
		Unit videos = unitServ.save(new Unit("VIDEOS"));
		Unit attr = unitServ.save(new Unit("ATRIBUTOS"));
		Unit bootstrap = unitServ.save(new Unit("BOOTSTRAP"));
		Unit nodejs = unitServ.save(new Unit("NODE_JS"));
		Unit backend = unitServ.save(new Unit("BACKEND"));
		Unit jquery = unitServ.save(new Unit("JQUERY"));
		Unit ecmaSc = unitServ.save(new Unit("ECMA_SCRIPT"));
		
		
		//// units relations

		relationServ.save(new Relation("inheritance", sgml, html));
		relationServ.save(new Relation("inheritance", maqWeb, html));
		relationServ.save(new Relation("inheritance", maqWeb, css));
		relationServ.save(new Relation("inheritance", backend, js));

		relationServ.save(new Relation("inheritance", html, html2));
		relationServ.save(new Relation("inheritance", html, html4));
		relationServ.save(new Relation("inheritance", js, ecmaSc));
		relationServ.save(new Relation("inheritance", ecmaSc, nodejs));

		relationServ.save(new Relation("composition",css, html));
		relationServ.save(new Relation("composition",etiq, html));
		relationServ.save(new Relation("composition",attr, html));

		relationServ.save(new Relation("composition", html, disWeb));
		relationServ.save(new Relation("composition", html, etiq));
		relationServ.save(new Relation("composition", spring, segSpring));
		relationServ.save(new Relation("composition", spring, docker));
		relationServ.save(new Relation("composition", spring, imag));
		relationServ.save(new Relation("composition", css, bootstrap));
		relationServ.save(new Relation("composition", html, videos));
	
		relationServ.save(new Relation("use", js, html));
		relationServ.save(new Relation("use", angular, html));
		relationServ.save(new Relation("use", estCss, css));
		relationServ.save(new Relation("use", java, spring));
		relationServ.save(new Relation("use", apirest, spring));
		
		relationServ.save(new Relation("use", html, css));
		relationServ.save(new Relation("use", html, xml));
		relationServ.save(new Relation("use", spring, java));

		relationServ.save(new Relation("association", css, html));
		relationServ.save(new Relation("association", xml, html));
		relationServ.save(new Relation("association", jquery, js));

		relationServ.save(new Relation("association", html, css));
		relationServ.save(new Relation("association", html, xml));
		relationServ.save(new Relation("association", spring, java));
		
		// users

		userServ.save(new User("user", "12345", "ROLE_USER"));
		userServ.save(new User("student", "12345", "ROLE_USER"));
		userServ.save(new User("teacher", "12345", "ROLE_USER", "ROLE_ADMIN"));
		
		
		//cards

		cardServ.save(new Card("Por qué", "Porque es facil de aprender y de usar", html));
		cardServ.save(new Card("Cuándo", "Html se lanzó inicialmente en 1993", html));
		cardServ.save(new Card("Cómo", "En internet hay muchos tutoriales para aprender a como usar html", html));
		cardServ.save(new Card("Para qué", "Para representar informacion de manera estatica", html));
		cardServ.save(new Card("Quién", "Ampliación de SGML", html));
		cardServ.save(new Card("Donde", "World Wide Web Consortium", html));
		
		cardServ.save(new Card("Por qué", "Porque es facil de aprender y de usar", css));
		cardServ.save(new Card("Cuándo", "CSS se lanzó inicialmente en 1996", css));
		cardServ.save(new Card("Cómo", "En internet hay muchos tutoriales para aprender a como usar css", css));
		cardServ.save(new Card("Para qué", "Para representar informacion de manera usando estilos", css));
		cardServ.save(new Card("Quién", "Lenguaje de hoja de estilos", css));
		cardServ.save(new Card("Dónde", "World Wide Web Consortium", css));
		

		
		
	}

}
