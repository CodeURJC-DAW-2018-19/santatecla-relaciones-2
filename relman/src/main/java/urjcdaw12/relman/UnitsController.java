package urjcdaw12.relman;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import urjcdaw12.relman.Cards.Card;
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

		userComponent.addTab(unit);
		model.addAttribute("units", userComponent.getTabs());


			Unit unitConc = unitServ.findByName(unit);

			model.addAttribute("student", request.isUserInRole("USER"));
			model.addAttribute("teacher", request.isUserInRole("ADMIN"));

			model.addAttribute("unidad", unitServ.findByName(unit));
			model.addAttribute("padres", relationServ.findByTypeAndDestiny("Herencia", unitConc));
			model.addAttribute("hijas", relationServ.findByTypeAndOrigin("Herencia", unitConc));

			model.addAttribute("compuestos", relationServ.findByTypeAndDestiny("Composición", unitServ.findByName(unit)));
			model.addAttribute("partes", relationServ.findByTypeAndOrigin("Composición", unitConc));

			model.addAttribute("usan", relationServ.findByTypeAndDestiny("Uso", unitConc));
			model.addAttribute("usa", relationServ.findByTypeAndOrigin("Uso", unitConc));

			model.addAttribute("asociados a", relationServ.findByTypeAndDestiny("Asociación", unitConc));
			model.addAttribute("asociado a", relationServ.findByTypeAndOrigin("Asociación", unitConc));

			model.addAttribute("cards", cardServ.findByUnitAsoc(unitConc));

			
			model.addAttribute("related", unitServ.findAll());

			return "units";
		 
	}

	@RequestMapping("/addRelationOrigin/{type}/{unit}")
	public String addRelationOrigin(Model model, @RequestParam String selected, HttpServletRequest request, @PathVariable String unit, @PathVariable String type) {
		Unit unitRelated = unitServ.findByName(unit);

		String newSel = request.getParameter("selected");

		Unit unitSelected = unitServ.findByName(newSel);

		relationServ.save(new Relation(type, unitSelected, unitRelated));

		return "redirect:/" + unit;
	}

	@RequestMapping("/addRelationDestiny/{type}/{unit}")
	public String addRelationDestiny(Model model, @RequestParam String selected, HttpServletRequest request, @PathVariable String unit, @PathVariable String type) {
		Unit unitRelated = unitServ.findByName(unit);

		String newSel = request.getParameter("selected");

		Unit unitSelected = unitServ.findByName(newSel);

		relationServ.save(new Relation(type, unitRelated, unitSelected));

		return "redirect:/" + unit;
	}

	@RequestMapping("/deleteRelation/{type}/{origin}/{destiny}")
	public String deleteRelationOrigin(Model model, @PathVariable String type, @PathVariable String origin, @PathVariable String destiny, HttpServletRequest request) {
		Unit unitOrigin = unitServ.findByName(origin);

		List<Relation> unitsCand = relationServ.findByTypeAndOrigin(type, unitOrigin);

		Relation relationToRemove = unitsCand.get(0);

		relationServ.delete(relationToRemove);

		return "redirect:/" + destiny;
	}

	@RequestMapping("/deleteRelationDestiny/{type}/{destiny}/{origin}")
	public String deleteRelationDestiny(Model model, @PathVariable String type, @PathVariable String origin, @PathVariable String destiny, HttpServletRequest request) {
		Unit unitDestiny = unitServ.findByName(destiny);

		List<Relation> unitsCand = relationServ.findByTypeAndDestiny(type, unitDestiny);

		Relation relationToRemove = unitsCand.get(0);

		relationServ.delete(relationToRemove);

		return "redirect:/" + origin;
	}

	@RequestMapping("/saveCard/{type}/{unit}")
	public String saveCard(Model model, @PathVariable String type, @PathVariable String unit, @RequestParam String desc) {
		Unit unitConc = unitServ.findByName(unit);
		Card card = cardServ.findByUnitAsocAndType(unitConc, type);
		card.setDesc(desc);
		cardServ.save(card);
		
		return "redirect:/{unit}";
	}
	
	@RequestMapping("/addCard/{unit}")
	public String addCard(Model model, @PathVariable String unit, @RequestParam String newCard) {
		Unit unitConc = unitServ.findByName(unit);
		Card card = new Card(newCard,"",unitConc);
		cardServ.save(card);
		
		return "redirect:/{unit}";
	}
}
