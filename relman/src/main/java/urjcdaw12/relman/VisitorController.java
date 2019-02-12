package urjcdaw12.relman;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class VisitorController {
	
	
	@Autowired
	private UnidadRepository unidadRep;
	

	
		@RequestMapping("/")
		 public String cargar(Model model) {
			model.addAttribute("unidades",unidadRep.findAll());
		 return "index";
		 }
}


