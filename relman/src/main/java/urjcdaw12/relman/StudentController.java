package urjcdaw12.relman;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/student")
public class StudentController {

	@RequestMapping("/")
	 public String cargar() {
	
	 return "studentIndex";
	 }
	
}
