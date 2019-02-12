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
	
	@PostConstruct
	public void init() {
		unidadRep.save(new Unit("HTML"));
		unidadRep.save(new Unit("CSS"));
		unidadRep.save(new Unit("JAVASCRIPT"));
		unidadRep.save(new Unit("API REST"));
		unidadRep.save(new Unit("SPRING"));
		unidadRep.save(new Unit("ANGULAR"));
	}
	
		@RequestMapping("/")
		 public String cargar(Model model) {
			model.addAttribute("unidades",unidadRep.findAll());
		 return "index";
		 }
}


