package urjcdaw12.relman;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import urjcdaw12.relman.Cards.CardService;
import urjcdaw12.relman.Relations.Relation;
import urjcdaw12.relman.Relations.RelationService;
import urjcdaw12.relman.Units.Unit;
import urjcdaw12.relman.Units.UnitService;
import urjcdaw12.relman.Users.UserComponent;

@Controller
public class UnitsController {

	@Autowired
	private UnitService unitServ;

	@Autowired
	private RelationService relationServ;

	@Autowired
	private CardService cardServ;

	@Autowired
	private UserComponent userComponent;

	@RequestMapping("/{unit}")
	public String openConcreteUnit(Model model, @PathVariable String unit, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		userComponent.addTab(unit);
		model.addAttribute("units", userComponent.getTabs());

		if (unitServ.findByName(unit).size() != 0) {

			Unit unitConc = unitServ.findByName(unit).get(0);

			model.addAttribute("student", request.isUserInRole("USER"));
			model.addAttribute("teacher", request.isUserInRole("ADMIN"));

			model.addAttribute("unidad", unitServ.findByName(unit).get(0));
			model.addAttribute("padres", relationServ.findByTypeAndDestiny("Herencia", unitConc));
			model.addAttribute("hijas", relationServ.findByTypeAndOrigin("Herencia", unitConc));

			model.addAttribute("compuestos", relationServ.findByTypeAndDestiny("Composición", unitServ.findByName(unit).get(0)));
			model.addAttribute("partes", relationServ.findByTypeAndOrigin("Composición", unitConc));

			model.addAttribute("usan", relationServ.findByTypeAndDestiny("Uso", unitConc));
			model.addAttribute("usa", relationServ.findByTypeAndOrigin("Uso", unitConc));

			model.addAttribute("asociados a", relationServ.findByTypeAndDestiny("Asociación", unitConc));
			model.addAttribute("asociado a", relationServ.findByTypeAndOrigin("Asociación", unitConc));

			model.addAttribute("cards", cardServ.findByUnitAsoc(unitConc));

			model.addAttribute("related", unitServ.findAll());

			return "units";
		} else {
			return "error";
		}
	}

	@RequestMapping("/addRelationOrigin/{type}/{unit}")
	public String addRelationOrigin(Model model, @RequestParam String selected, HttpServletRequest request, @PathVariable String unit, @PathVariable String type) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		Unit unitRelated = unitServ.findByName(unit).get(0);

		String newSel = request.getParameter("selected");

		Unit unitSelected = unitServ.findByName(newSel).get(0);

		relationServ.save(new Relation(type, unitSelected, unitRelated));

		return "redirect:/" + unit;
	}

	@RequestMapping("/addRelationDestiny/{type}/{unit}")
	public String addRelationDestiny(Model model, @RequestParam String selected, HttpServletRequest request, @PathVariable String unit, @PathVariable String type) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		Unit unitRelated = unitServ.findByName(unit).get(0);

		String newSel = request.getParameter("selected");

		Unit unitSelected = unitServ.findByName(newSel).get(0);

		relationServ.save(new Relation(type, unitRelated, unitSelected));

		return "redirect:/" + unit;
	}

	@RequestMapping("/deleteRelation/{type}/{origin}/{destiny}")
	public String deleteRelationOrigin(Model model, @PathVariable String type, @PathVariable String origin, @PathVariable String destiny, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		Unit unitOrigin = unitServ.findByName(origin).get(0);

		List<Relation> unitsCand = relationServ.findByTypeAndOrigin(type, unitOrigin);

		Relation relationToRemove = unitsCand.get(0);

		relationServ.delete(relationToRemove);

		return "redirect:/" + destiny;
	}

	@RequestMapping("/deleteRelationDestiny/{type}/{destiny}/{origin}")
	public String deleteRelationDestiny(Model model, @PathVariable String type, @PathVariable String origin, @PathVariable String destiny, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());

		Unit unitDestiny = unitServ.findByName(destiny).get(0);

		List<Relation> unitsCand = relationServ.findByTypeAndDestiny(type, unitDestiny);

		Relation relationToRemove = unitsCand.get(0);

		relationServ.delete(relationToRemove);

		return "redirect:/" + origin;
	}

}
