package urjcdaw12.relman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private UnidadRepository unidadRep;

	@Autowired
	private RelationRepository relationRep;

	
	@RequestMapping("/")
	 public String cargar(Model model) {
		model.addAttribute("unidades",unidadRep.findAll());

	 return "teacherIndex";
	 }
	
	@RequestMapping("/unit")
	 public String openUnit(Model model) {
		model.addAttribute("unidad",unidadRep.findByName("HTML").get(0));
		model.addAttribute("padres", relationRep.findByTypeAndDestiny("Herencia",unidadRep.findByName("HTML").get(0)));
		model.addAttribute("hijas", relationRep.findByTypeAndOrigin("Herencia",unidadRep.findByName("HTML").get(0)));

		model.addAttribute("compuestos", relationRep.findByTypeAndDestiny("Composición",unidadRep.findByName("HTML").get(0)));
		model.addAttribute("partes", relationRep.findByTypeAndOrigin("Composición",unidadRep.findByName("HTML").get(0)));

		model.addAttribute("usan", relationRep.findByTypeAndDestiny("Uso",unidadRep.findByName("HTML").get(0)));
		model.addAttribute("usa", relationRep.findByTypeAndOrigin("Uso",unidadRep.findByName("HTML").get(0)));

		model.addAttribute("asociados a", relationRep.findByTypeAndDestiny("Asociación",unidadRep.findByName("HTML").get(0)));
		model.addAttribute("asociado a", relationRep.findByTypeAndOrigin("Asociación",unidadRep.findByName("HTML").get(0)));

		return "teacherUnit";
	 }
	
	@RequestMapping("/{unit}")
	public String openConcreteUnit(Model model, @PathVariable String unit) {
		model.addAttribute("unidad",unidadRep.findByName(unit).get(0));
		model.addAttribute("padres", relationRep.findByTypeAndDestiny("Herencia",unidadRep.findByName(unit).get(0)));
		model.addAttribute("hijas", relationRep.findByTypeAndOrigin("Herencia",unidadRep.findByName(unit).get(0)));

		model.addAttribute("compuestos", relationRep.findByTypeAndDestiny("Composición",unidadRep.findByName(unit).get(0)));
		model.addAttribute("partes", relationRep.findByTypeAndOrigin("Composición",unidadRep.findByName(unit).get(0)));

		model.addAttribute("usan", relationRep.findByTypeAndDestiny("Uso",unidadRep.findByName(unit).get(0)));
		model.addAttribute("usa", relationRep.findByTypeAndOrigin("Uso",unidadRep.findByName(unit).get(0)));

		model.addAttribute("asociados a", relationRep.findByTypeAndDestiny("Asociación",unidadRep.findByName(unit).get(0)));
		model.addAttribute("asociado a", relationRep.findByTypeAndOrigin("Asociación",unidadRep.findByName(unit).get(0)));
	return "teacherUnit";
	}
}
