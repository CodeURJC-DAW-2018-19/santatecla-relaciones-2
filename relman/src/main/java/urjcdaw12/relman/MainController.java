package urjcdaw12.relman;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
	private UnidadRepository unidadRep;

	@Autowired
	private RelationRepository relationRep;
	
	@Autowired
	private CardRepository cardRep;
	
	@Autowired
	private UserRepository userRep;

	@RequestMapping("/")
	public String cargar(Model model, HttpServletRequest request) {
		model.addAttribute("unidades", unidadRep.findAll());
		model.addAttribute("teacher", request.isUserInRole("ADMIN"));
		model.addAttribute("student", request.isUserInRole("USER"));

		return "index";
	}



	@RequestMapping("/{unit}")
	public String openConcreteUnit(Model model, @PathVariable String unit, HttpServletRequest request) {
			Unit unitConc = unidadRep.findByName(unit).get(0);
		
			model.addAttribute("student", request.isUserInRole("USER"));
			model.addAttribute("teacher", request.isUserInRole("ADMIN"));
	
			model.addAttribute("unidad", unidadRep.findByName(unit).get(0));
			model.addAttribute("padres", relationRep.findByTypeAndDestiny("Herencia", unitConc));
			model.addAttribute("hijas", relationRep.findByTypeAndOrigin("Herencia", unitConc));
	
			model.addAttribute("compuestos",
					relationRep.findByTypeAndDestiny("Composición", unidadRep.findByName(unit).get(0)));
			model.addAttribute("partes", relationRep.findByTypeAndOrigin("Composición", unitConc));
	
			model.addAttribute("usan", relationRep.findByTypeAndDestiny("Uso",unitConc));
			model.addAttribute("usa", relationRep.findByTypeAndOrigin("Uso", unitConc));
	
			model.addAttribute("asociados a",
					relationRep.findByTypeAndDestiny("Asociación", unitConc));
			model.addAttribute("asociado a",
					relationRep.findByTypeAndOrigin("Asociación", unitConc));
			
			model.addAttribute("cards",cardRep.findByUnitAsoc(unitConc));
		
		return "units";
	}
	
	
	@RequestMapping("/register")
	public String register(@RequestParam("userInput") String user, @RequestParam("pass1") String pass1, @RequestParam("pass2") String pass2) {
		if (pass1.equals(pass2)) {
			userRep.save(new User (user,pass1,"ROLE_USER"));
		}
		
		return "redirect:/";
	}
	
	//Ahora mismo funciona perfectamente, pero habría que meter un modal de confirmación de borrado, actualmente refresca la pagina actualizada		
	@RequestMapping("/delete/{unit}")
	public String deleteUnit(Model model,@PathVariable String unit) {
		Unit unitConc = unidadRep.findByName(unit).get(0);
		
		if (unitConc!=null) {
			unidadRep.delete(unitConc);
			
		}
		
		return "redirect:/"; 
		
	}
	
}
