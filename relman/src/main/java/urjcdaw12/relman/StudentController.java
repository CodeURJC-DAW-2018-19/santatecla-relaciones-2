package urjcdaw12.relman;


import javax.servlet.http.HttpServletRequest;

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
	 public String cargar(Model model, HttpServletRequest request) {
		model.addAttribute("unidades",unidadRep.findAll());
		model.addAttribute("student", request.isUserInRole("USER"));
		model.addAttribute("teacher", request.isUserInRole("ADMIN"));
	 return "studentIndex";
	 }
	
}
