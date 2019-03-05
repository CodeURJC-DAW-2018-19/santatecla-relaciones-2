package urjcdaw11.relman;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import urjcdaw11.relman.cards.Card;
import urjcdaw11.relman.cards.CardService;
import urjcdaw11.relman.relations.Relation;
import urjcdaw11.relman.relations.RelationService;
import urjcdaw11.relman.units.Unit;
import urjcdaw11.relman.units.UnitService;
import urjcdaw11.relman.users.UserComponent;

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

	@Autowired
	private UMLCreator umlCreator;

	public String redirect(Model model, String redirection, HttpServletResponse httpServletResponse) {
		try {
			httpServletResponse.sendRedirect("/" + redirection);
		} catch (IOException e) {
			model.addAttribute("error", "Error en la redirección");
			return "error";
		}
		return null;
	}

	@RequestMapping("/{unit}")
	public String openConcreteUnit(Model model, @PathVariable String unit, HttpServletRequest request) {

		userComponent.addTab(unit);
		model.addAttribute("tabs", userComponent.getTabs());

		Pageable pageDef = PageRequest.of(0, 5);

		userComponent.addTab(unit);
		model.addAttribute("tabs", userComponent.getTabs());

		Unit unitConc = unitServ.findByName(unit);

		model.addAttribute("student", request.isUserInRole("USER"));
		model.addAttribute("teacher", request.isUserInRole("ADMIN"));

		model.addAttribute("unit", unitServ.findByName(unit));
		model.addAttribute("parents", relationServ.findByTypeAndDestiny("inheritance", unitConc, pageDef));
		model.addAttribute("children", relationServ.findByTypeAndOrigin("inheritance", unitConc, pageDef));

		model.addAttribute("components", relationServ.findByTypeAndDestiny("composition", unitConc, pageDef));
		model.addAttribute("parts", relationServ.findByTypeAndOrigin("composition", unitConc, pageDef));

		model.addAttribute("useDestiny", relationServ.findByTypeAndDestiny("use", unitConc, pageDef));
		model.addAttribute("use", relationServ.findByTypeAndOrigin("use", unitConc, pageDef));

		model.addAttribute("associatedDestiny", relationServ.findByTypeAndDestiny("association", unitConc, pageDef));
		model.addAttribute("associatedOrigin", relationServ.findByTypeAndOrigin("association", unitConc, pageDef));

		model.addAttribute("cards", cardServ.findByUnitAsoc(unitConc));

		model.addAttribute("related", unitServ.findAll(PageRequest.of(0, Integer.MAX_VALUE)));

		int nParents = relationServ.findByTypeAndDestiny("inheritance", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nParents", nParents);
		model.addAttribute("showParents", nParents > 0);

		int nChildren = relationServ.findByTypeAndOrigin("inheritance", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nChildren", nChildren);
		model.addAttribute("showChildren", nChildren > 0);

		int nComponents = relationServ.findByTypeAndDestiny("composition", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nComponents", nComponents);
		model.addAttribute("showComponents", nComponents > 0);

		int nParts = relationServ.findByTypeAndOrigin("composition", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nParts", nParts);
		model.addAttribute("showParts", nParts > 0);

		int nUseDestiny = relationServ.findByTypeAndDestiny("use", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nUseDestiny", nUseDestiny);
		model.addAttribute("showUseDestiny", nUseDestiny > 0);

		int nUse = relationServ.findByTypeAndOrigin("use", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nUse", nUse);
		model.addAttribute("showUse", nUse > 0);

		int nAssociatedDestiny = relationServ.findByTypeAndDestiny("association", unitConc).size()
				- pageDef.getPageSize();
		model.addAttribute("nAssociatedDestiny", nAssociatedDestiny);
		model.addAttribute("showAssociatedDestiny", nAssociatedDestiny > 0);

		int nAssociatedOrigin = relationServ.findByTypeAndOrigin("association", unitConc).size()
				- pageDef.getPageSize();
		model.addAttribute("nAssociatedOrigin", nAssociatedOrigin);
		model.addAttribute("showAssociatedOrigin", nAssociatedOrigin > 0);

		if (relationServ.findByTypeAndOrigin("composition", unitConc).size() != 0) { // Generates the UML just if the
																						// unit has "Partes"
			umlCreator.compositionUML(unitConc, model);
		}

		if (relationServ.findByTypeAndOrigin("inheritance", unitConc).size() != 0) { // Generates the UML just if the
																						// unit has "Hijas"
			umlCreator.clasificationUML(unitConc, model);
		}

		umlCreator.contextUML(unitConc);

		model.addAttribute("photoCompExists", unitConc.isPhotoComp());
		model.addAttribute("photoClasExists", unitConc.isPhotoClas());

		model.addAttribute("photoComp", "comp" + unit + ".png");
		model.addAttribute("photoClas", "clas" + unit + ".png");
		model.addAttribute("photoContext", "context" + unit + ".png");

		return "units";

	}

	@RequestMapping("/rel/{unit}/{relation}/{page}/")
	public String loadAjaxUnit(Model model, HttpServletRequest request, @PathVariable String unit,
			@PathVariable String relation, @PathVariable int page, @RequestParam Optional<String> search) {

		model.addAttribute("teacher", request.isUserInRole("ADMIN"));
		model.addAttribute("student", request.isUserInRole("USER"));

		Unit unitConc = unitServ.findByName(unit);
		if (relation.equals("parents")) {
			Page<Relation> ok = relationServ.findByTypeAndDestiny("inheritance", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
		}
		if (relation.equals("children")) {
			Page<Relation> ok = relationServ.findByTypeAndOrigin("inheritance", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
			return "ajaxUnit2";
		}
		if (relation.equals("components")) {
			Page<Relation> ok = relationServ.findByTypeAndDestiny("composition", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
		}
		if (relation.equals("parts")) {
			Page<Relation> ok = relationServ.findByTypeAndOrigin("composition", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
			return "ajaxUnit2";
		}
		if (relation.equals("useDestiny")) {
			Page<Relation> ok = relationServ.findByTypeAndDestiny("use", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
		}
		if (relation.equals("use")) {
			Page<Relation> ok = relationServ.findByTypeAndOrigin("use", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
			return "ajaxUnit2";
		}
		if (relation.equals("associatedDestiny")) {
			Page<Relation> ok = relationServ.findByTypeAndDestiny("association", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
		}
		if (relation.equals("associatedOrigin")) {
			Page<Relation> ok = relationServ.findByTypeAndOrigin("association", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
			return "ajaxUnit2";
		}

		return "ajaxUnit";
	}

	@RequestMapping("/addRelationOrigin/{type}/{unit}")
	public String addRelationOrigin(Model model, @RequestParam String selected, HttpServletRequest request,
			@PathVariable String unit, @PathVariable String type, HttpServletResponse httpServletResponse) {
		Unit unitRelated = unitServ.findByName(unit);

		String newSel = request.getParameter("selected");

		Unit unitSelected = unitServ.findByName(newSel);

		relationServ.save(new Relation(type, unitSelected, unitRelated));

		return redirect(model, unit, httpServletResponse);
	}

	@RequestMapping("/addRelationDestiny/{type}/{unit}")
	public String addRelationDestiny(Model model, @RequestParam String selected, HttpServletRequest request,
			@PathVariable String unit, @PathVariable String type, HttpServletResponse httpServletResponse) {
		Unit unitRelated = unitServ.findByName(unit);

		String newSel = request.getParameter("selected");

		Unit unitSelected = unitServ.findByName(newSel);

		relationServ.save(new Relation(type, unitRelated, unitSelected));

		return redirect(model, unit, httpServletResponse);

	}

	@RequestMapping("/deleteRelation/{type}/{origin}/{destiny}")
	public String deleteRelationOrigin(Model model, @PathVariable String type, @PathVariable String origin,
			@PathVariable String destiny, HttpServletRequest request, HttpServletResponse httpServletResponse) {
		Unit unitOrigin = unitServ.findByName(origin);

		List<Relation> unitsCand = relationServ.findByTypeAndOrigin(type, unitOrigin);

		Relation relationToRemove = unitsCand.get(0);

		relationServ.delete(relationToRemove);

		return redirect(model, destiny, httpServletResponse);
	}

	@RequestMapping("/deleteRelationDestiny/{type}/{destiny}/{origin}")
	public String deleteRelationDestiny(Model model, @PathVariable String type, @PathVariable String origin,
			@PathVariable String destiny, HttpServletRequest request, HttpServletResponse httpServletResponse) {
		Unit unitDestiny = unitServ.findByName(destiny);

		List<Relation> unitsCand = relationServ.findByTypeAndDestiny(type, unitDestiny);

		Relation relationToRemove = unitsCand.get(0);

		relationServ.delete(relationToRemove);

		return redirect(model, origin, httpServletResponse);
	}

	@RequestMapping("/saveCard/{type}/{unit}")
	public String saveCard(Model model, @PathVariable String type, @PathVariable String unit, @RequestParam String desc,
			HttpServletResponse httpServletResponse) {
		Unit unitConc = unitServ.findByName(unit);
		Card card = cardServ.findByUnitAsocAndType(unitConc, type);
		card.setDesc(desc);
		cardServ.save(card);

		return redirect(model, unit, httpServletResponse);
	}

	@RequestMapping("/addCard/{unit}")
	public String addCard(Model model, @PathVariable String unit, @RequestParam String newCard,
			HttpServletResponse httpServletResponse) {
		Unit unitConc = unitServ.findByName(unit);
		Card card = new Card(newCard, "", unitConc);
		cardServ.save(card);

		return redirect(model, unit, httpServletResponse);

	}

	@PostMapping("/image/{type}/{unit}")
	public String saveImage(Model model, @PathVariable String type, @PathVariable String unit,
			@RequestParam("file") MultipartFile file, HttpServletResponse httpServletResponse) {
		Unit unitConc = unitServ.findByName(unit);
		Card card = cardServ.findByUnitAsocAndType(unitConc, type);
		
		if (!file.isEmpty()) {
			try {
				cardServ.saveImage(file, unit, card, type);
				return redirect(model, unit, httpServletResponse);
			} catch (Exception e) {
				model.addAttribute("error", e.getClass().getName() + ":" + e.getMessage());
				return "error";
			}
		} else {
			model.addAttribute("error", "The file is empty");
			return "error";
		}
	}

	@GetMapping("/image/{unit}/{type}")
	public void handleFileDownload(@PathVariable String unit, @PathVariable String type, HttpServletResponse response) throws FileNotFoundException, IOException {

		Path image = cardServ.getImage(unit, type);

		if (Files.exists(image)) {
			response.setContentType("image/jpeg");
			response.setContentLength((int) image.toFile().length());
			FileCopyUtils.copy(Files.newInputStream(image), response.getOutputStream());
		} else {
			response.sendError(404, "File" + unit + type + "(" + image.toAbsolutePath() + ") does not exist");
		}
	}

}
