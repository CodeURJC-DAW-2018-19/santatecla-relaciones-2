package urjcdaw12.relman;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class VisitorController {
	
		@RequestMapping("/")
		 public String cargar() {
		 
		 return "index";
		 }
}


