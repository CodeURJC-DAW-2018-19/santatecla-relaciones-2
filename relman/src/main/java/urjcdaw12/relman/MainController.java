package urjcdaw12.relman;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import urjcdaw12.relman.Cards.CardService;
import urjcdaw12.relman.Relations.Relation;
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
		
		if (unitServ.findByName(unit).size()!=0) {
			
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
			
			model.addAttribute("related",unitServ.findAll());
			
			return "units";
		}else {
			return "index";
		}
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
	

	
	@RequestMapping("/addUnit")
	public String addUnit(Model model, @RequestParam String newUnit,HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		unitServ.save(new Unit(newUnit));
		
		return "redirect:/";
	}
	
	@RequestMapping("/addRelationOrigin/{type}/{unit}")
	public String addRelationOrigin(Model model, @RequestParam String selected, HttpServletRequest request, @PathVariable String unit, @PathVariable String type) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		Unit unitRelated = unitServ.findByName(unit).get(0);
		
		String newSel= request.getParameter("selected");
				
		Unit unitSelected = unitServ.findByName(newSel).get(0);
		
		relationServ.save(new Relation(type,unitSelected,unitRelated));
		
		
		
		return "redirect:/"+unit;
	}
	
	@RequestMapping("/addRelationDestiny/{type}/{unit}")
	public String addRelationDestiny(Model model, @RequestParam String selected, HttpServletRequest request, @PathVariable String unit, @PathVariable String type) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		Unit unitRelated = unitServ.findByName(unit).get(0);
		
		String newSel= request.getParameter("selected");
				
		Unit unitSelected = unitServ.findByName(newSel).get(0);
		
		relationServ.save(new Relation(type,unitRelated,unitSelected));
		
		
		
		return "redirect:/"+unit;
	}
	
	@RequestMapping("/deleteRelation/{type}/{origin}/{destiny}")
	public String deleteRelationOrigin(Model model, @PathVariable String type, @PathVariable String origin,@PathVariable String destiny ,HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		Unit unitOrigin = unitServ.findByName(origin).get(0);
		
		List<Relation> unitsCand=relationServ.findByTypeAndOrigin(type, unitOrigin);
		
		Relation relationToRemove = unitsCand.get(0);
		
		relationServ.delete(relationToRemove);		
		
		return "redirect:/"+destiny;
	}
	
	@RequestMapping("/deleteRelationDestiny/{type}/{destiny}/{origin}")
	public String deleteRelationDestiny(Model model, @PathVariable String type, @PathVariable String origin,@PathVariable String destiny ,HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		Unit unitDestiny = unitServ.findByName(destiny).get(0);
		
		List<Relation> unitsCand=relationServ.findByTypeAndDestiny(type, unitDestiny);
		
		Relation relationToRemove = unitsCand.get(0);
		
		relationServ.delete(relationToRemove);		
		
		return "redirect:/"+origin;
	}
	
	

}
