package urjcdaw12.relman;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@Autowired
	private UnidadRepository unidadRep;

	@Autowired
	private RelationRepository relationRep;

	@RequestMapping("/")
	public String cargar(Model model, HttpServletRequest request) {
		model.addAttribute("unidades", unidadRep.findAll());
		model.addAttribute("student", request.isUserInRole("USER"));
		model.addAttribute("teacher", request.isUserInRole("ADMIN"));
		return "index";
	}

	@RequestMapping("/unit")
	public String openUnit(Model model, HttpServletRequest request) {
		model.addAttribute("student", request.isUserInRole("USER"));
		model.addAttribute("teacher", request.isUserInRole("ADMIN"));

		model.addAttribute("unidad", unidadRep.findByName("HTML").get(0));
		model.addAttribute("padres", relationRep.findByTypeAndDestiny("Herencia", unidadRep.findByName("HTML").get(0)));
		model.addAttribute("hijas", relationRep.findByTypeAndOrigin("Herencia", unidadRep.findByName("HTML").get(0)));

		model.addAttribute("compuestos",
				relationRep.findByTypeAndDestiny("Composición", unidadRep.findByName("HTML").get(0)));
		model.addAttribute("partes",
				relationRep.findByTypeAndOrigin("Composición", unidadRep.findByName("HTML").get(0)));

		model.addAttribute("usan", relationRep.findByTypeAndDestiny("Uso", unidadRep.findByName("HTML").get(0)));
		model.addAttribute("usa", relationRep.findByTypeAndOrigin("Uso", unidadRep.findByName("HTML").get(0)));

		model.addAttribute("asociados a",
				relationRep.findByTypeAndDestiny("Asociación", unidadRep.findByName("HTML").get(0)));
		model.addAttribute("asociado a",
				relationRep.findByTypeAndOrigin("Asociación", unidadRep.findByName("HTML").get(0)));

		return "units";
	}

	@RequestMapping("/{unit}")
	public String openConcreteUnit(Model model, @PathVariable String unit, HttpServletRequest request) {
		model.addAttribute("student", request.isUserInRole("USER"));
		model.addAttribute("teacher", request.isUserInRole("ADMIN"));

		model.addAttribute("unidad", unidadRep.findByName(unit).get(0));
		model.addAttribute("padres", relationRep.findByTypeAndDestiny("Herencia", unidadRep.findByName(unit).get(0)));
		model.addAttribute("hijas", relationRep.findByTypeAndOrigin("Herencia", unidadRep.findByName(unit).get(0)));

		model.addAttribute("compuestos",
				relationRep.findByTypeAndDestiny("Composición", unidadRep.findByName(unit).get(0)));
		model.addAttribute("partes", relationRep.findByTypeAndOrigin("Composición", unidadRep.findByName(unit).get(0)));

		model.addAttribute("usan", relationRep.findByTypeAndDestiny("Uso", unidadRep.findByName(unit).get(0)));
		model.addAttribute("usa", relationRep.findByTypeAndOrigin("Uso", unidadRep.findByName(unit).get(0)));

		model.addAttribute("asociados a",
				relationRep.findByTypeAndDestiny("Asociación", unidadRep.findByName(unit).get(0)));
		model.addAttribute("asociado a",
				relationRep.findByTypeAndOrigin("Asociación", unidadRep.findByName(unit).get(0)));
		return "units";
	}
}
