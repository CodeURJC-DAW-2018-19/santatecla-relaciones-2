package urjcdaw12.relman;

import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/teacher")
public class TeacherController {

	
	@RequestMapping("/")
	 public String cargar() {
	 
	 return "teacherIndex";
	 }
}
