package urjcdaw12.relman;

import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import urjcdaw12.relman.units.Unit;
import urjcdaw12.relman.units.UnitService;
import urjcdaw12.relman.users.User;
import urjcdaw12.relman.users.UserComponent;
import urjcdaw12.relman.users.UserService;

@Controller
public class MainController {

	@Autowired
	private UnitService unitServ;

	@Autowired
	private UserService userServ;

	@Autowired
	private UserComponent userComponent;

	@RequestMapping("/")
	public String cargar(Model model, HttpServletRequest request, Pageable page, @RequestParam Optional<String> search) {

		model.addAttribute("tabs", userComponent.getTabs());

		model.addAttribute("teacher", request.isUserInRole("ADMIN"));
		model.addAttribute("student", request.isUserInRole("USER"));

		if (!search.isPresent()) {
			model.addAttribute("units", unitServ.findAll(page));
		} else {
			model.addAttribute("units", unitServ.findSearch(page, search.get()));
		}
		boolean plusbutton = page.getPageSize() < unitServ.totalElements();
		model.addAttribute("plusbutton", plusbutton);
		model.addAttribute("totalelements", unitServ.totalElements() - page.getPageSize());
		return "index";
	}
	
	@RequestMapping("/page/{page}/{size}")
	public String loadAjax(Model model, HttpServletRequest request, @PathVariable int page, @PathVariable int size, @RequestParam Optional<String> search) {

		model.addAttribute("tabs", userComponent.getTabs());
		model.addAttribute("teacher", request.isUserInRole("ADMIN"));
		model.addAttribute("student", request.isUserInRole("USER"));
		
		if (!search.isPresent()) {
			
			model.addAttribute("units", unitServ.findAll(PageRequest.of(page, size)));
			
			
		} else {
			model.addAttribute("units", unitServ.findSearch(PageRequest.of(page, size), search.get()));
		}

		return "ajaxIndex";
	}

	@RequestMapping("/register")
	public String register(Model model, @RequestParam("userInput") String user, @RequestParam("pass1") String pass1, @RequestParam("pass2") String pass2, HttpServletRequest request) {

		if ((pass1.equals(pass2)) && (userServ.findByName(user) == null)) {
			userServ.save(new User(user, pass1, "ROLE_USER"));
			try {
				request.login(user, pass1);
			} catch (ServletException e) {
				e.printStackTrace();
			}
			return "redirect:/";
		} else {
			// model.addAttribute("desc","...");
			return "error"; // pagina de error a implementar
		}
	}

	@RequestMapping("/delete/{unit}")
	public String deleteUnit(Model model, @PathVariable String unit, HttpServletRequest request) {

		userComponent.removeTab(unit);

		Unit unitConc = unitServ.findByName(unit);

		if (unitConc != null) {
			unitServ.delete(unitConc);

		}
		return "redirect:/";

	}

	@RequestMapping("/addUnit")
	public String addUnit(Model model, @RequestParam String newUnit, HttpServletRequest request) {

		if (unitServ.findByName(newUnit) == null) {
			unitServ.save(new Unit(newUnit));
		}

		return "redirect:/";
	}

	@RequestMapping("/removeTab/{unit}")
	public String removeTab(Model model, HttpServletRequest request, @PathVariable String unit) {
		userComponent.removeTab(unit);

		return "redirect:/";
	}

}
