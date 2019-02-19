package urjcdaw12.relman;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import urjcdaw12.relman.Units.Unit;
import urjcdaw12.relman.Units.UnitService;
import urjcdaw12.relman.Users.User;
import urjcdaw12.relman.Users.UserComponent;
import urjcdaw12.relman.Users.UserService;

@Controller
public class MainController {

	@Autowired
	private UnitService unitServ;

	@Autowired
	private UserService userServ;

	@Autowired
	private UserComponent userComponent;

	@RequestMapping("/")
	public String cargar(Model model, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		model.addAttribute("units", userComponent.getTabs());

		model.addAttribute("unidades", unitServ.findAll());
		model.addAttribute("teacher", request.isUserInRole("ADMIN"));
		model.addAttribute("student", request.isUserInRole("USER"));

		return "index";
	}

	@RequestMapping("/register")
	public String register(Model model, @RequestParam("userInput") String user, @RequestParam("pass1") String pass1, @RequestParam("pass2") String pass2, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		if (pass1.equals(pass2)) {
			userServ.save(new User(user, pass1, "ROLE_USER"));
		}

		return "redirect:/";
	}

	@RequestMapping("/delete/{unit}")
	public String deleteUnit(Model model, @PathVariable String unit, HttpServletRequest request) {

		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		userComponent.removeTab(unit);
		
		Unit unitConc = unitServ.findByName(unit).get(0);

		if (unitConc != null) {
			unitServ.delete(unitConc);

		}
		return "redirect:/";

	}

	@RequestMapping("/addUnit")
	public String addUnit(Model model, @RequestParam String newUnit, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		unitServ.save(new Unit(newUnit));

		return "redirect:/";
	}
	
	@RequestMapping("/removeTab/{unit}")
	public String removeTab(Model model, HttpServletRequest request, @PathVariable String unit) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		
		userComponent.removeTab(unit);
		
		return "redirect:/";
	}

}
