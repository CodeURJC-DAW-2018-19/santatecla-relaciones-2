package urjcdaw12.relman;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import urjcdaw12.relman.Cards.CardService;
import urjcdaw12.relman.Relations.RelationService;
import urjcdaw12.relman.Units.Unit;
import urjcdaw12.relman.Units.UnitService;
import urjcdaw12.relman.Users.User;
import urjcdaw12.relman.Users.UserComponent;
import urjcdaw12.relman.Users.UserService;

@Controller
public class MainController {

	@Autowired
	private UnitService unitServ;

	@Autowired
	private RelationService relationServ;

	@Autowired
	private CardService cardServ;

	@Autowired
	private UserService userServ;
	
	@Autowired
	private UserComponent userComponent;

	@RequestMapping("/")
	public String cargar(Model model, HttpServletRequest request) {
		
		//cada vez que genero un formulario cojo el form de la request y la pongo en el modelo
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		model.addAttribute("units",userComponent.getTabs());

		model.addAttribute("unidades", unitServ.findAll());
		model.addAttribute("teacher", request.isUserInRole("ADMIN"));
		model.addAttribute("student", request.isUserInRole("USER"));

		return "index";
	}

	@RequestMapping("/{unit}")
	public String openConcreteUnit(Model model, @PathVariable String unit, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		userComponent.addTab(unit);
		model.addAttribute("units",userComponent.getTabs());
		
		Unit unitConc = unitServ.findByName(unit).get(0);

		model.addAttribute("student", request.isUserInRole("USER"));
		model.addAttribute("teacher", request.isUserInRole("ADMIN"));

		model.addAttribute("unidad", unitServ.findByName(unit).get(0));
		model.addAttribute("padres", relationServ.findByTypeAndDestiny("Herencia", unitConc));
		model.addAttribute("hijas", relationServ.findByTypeAndOrigin("Herencia", unitConc));

		model.addAttribute("compuestos",relationServ.findByTypeAndDestiny("Composición", unitServ.findByName(unit).get(0)));
		model.addAttribute("partes", relationServ.findByTypeAndOrigin("Composición", unitConc));

		model.addAttribute("usan", relationServ.findByTypeAndDestiny("Uso", unitConc));
		model.addAttribute("usa", relationServ.findByTypeAndOrigin("Uso", unitConc));

		model.addAttribute("asociados a", relationServ.findByTypeAndDestiny("Asociación", unitConc));
		model.addAttribute("asociado a", relationServ.findByTypeAndOrigin("Asociación", unitConc));

		model.addAttribute("cards", cardServ.findByUnitAsoc(unitConc));
		
		return "units";
	}

	@RequestMapping("/register")
	public String register(Model model, @RequestParam("userInput") String user, @RequestParam("pass1") String pass1,
			@RequestParam("pass2") String pass2, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		if (pass1.equals(pass2)) {
			userServ.save(new User(user, pass1, "ROLE_USER"));
		}

		return "redirect:/";
	}

	// Ahora mismo funciona perfectamente, pero habría que meter un modal de
	// confirmación de borrado, actualmente refresca la pagina actualizada
	@RequestMapping("/delete/{unit}")
	public String deleteUnit(Model model, @PathVariable String unit, HttpServletRequest request) {
		
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		Unit unitConc = unitServ.findByName(unit).get(0);

		if (unitConc != null) {
			unitServ.delete(unitConc);

		}

		return "redirect:/";

	}

}
