package urjcdaw12.relman;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private UnidadRepository unidadRep;


	@RequestMapping("/")
	 public String cargar(Model model) {
		model.addAttribute("unidades",unidadRep.findAll());

	 return "studentIndex";
	 }
	
}
