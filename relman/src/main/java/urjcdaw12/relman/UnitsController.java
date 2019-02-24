package urjcdaw12.relman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import urjcdaw12.relman.cards.Card;
import urjcdaw12.relman.cards.CardService;
import urjcdaw12.relman.relations.Relation;
import urjcdaw12.relman.relations.RelationService;
import urjcdaw12.relman.units.Unit;
import urjcdaw12.relman.units.UnitService;
import urjcdaw12.relman.users.UserComponent;

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

	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");

	@PostConstruct
	public void init() throws IOException {
		if (!Files.exists(FILES_FOLDER)) {
			Files.createDirectories(FILES_FOLDER);
		}
	}

	@RequestMapping("/{unit}")
	public String openConcreteUnit(Model model, @PathVariable String unit, HttpServletRequest request, Pageable page) {

		userComponent.addTab(unit);
		model.addAttribute("tabs", userComponent.getTabs());

		Pageable pageDef = PageRequest.of(0, 5);

		userComponent.addTab(unit);
		model.addAttribute("tabs", userComponent.getTabs());

		Unit unitConc = unitServ.findByName(unit);

		model.addAttribute("student", request.isUserInRole("USER"));
		model.addAttribute("teacher", request.isUserInRole("ADMIN"));

		model.addAttribute("unit", unitServ.findByName(unit));
		model.addAttribute("padres", relationServ.findByTypeAndDestiny("inheritance", unitConc, pageDef));
		model.addAttribute("hijas", relationServ.findByTypeAndOrigin("inheritance", unitConc, pageDef));

		model.addAttribute("compuestos", relationServ.findByTypeAndDestiny("composition", unitConc, pageDef));
		model.addAttribute("partes", relationServ.findByTypeAndOrigin("composition", unitConc, pageDef));

		model.addAttribute("usan", relationServ.findByTypeAndDestiny("use", unitConc, pageDef));
		model.addAttribute("usa", relationServ.findByTypeAndOrigin("use", unitConc, pageDef));

		model.addAttribute("asociados a", relationServ.findByTypeAndDestiny("association", unitConc, pageDef));
		model.addAttribute("asociado a", relationServ.findByTypeAndOrigin("association", unitConc, pageDef));

		model.addAttribute("cards", cardServ.findByUnitAsoc(unitConc));

		model.addAttribute("related", unitServ.findAll(PageRequest.of(0, Integer.MAX_VALUE)));

		int nPadres = relationServ.findByTypeAndDestiny("inheritance", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nPadres", nPadres);
		model.addAttribute("showPadres", nPadres > 0);

		int nHijas = relationServ.findByTypeAndOrigin("inheritance", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nHijas", nHijas);
		model.addAttribute("showHijas", nHijas > 0);

		int nCompuestos = relationServ.findByTypeAndDestiny("composition", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nCompuestos", nCompuestos);
		model.addAttribute("showCompuestos", nCompuestos > 0);

		int nPartes = relationServ.findByTypeAndOrigin("composition", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nPartes", nPartes);
		model.addAttribute("showPartes", nPartes > 0);

		int nUsan = relationServ.findByTypeAndDestiny("use", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nUsan", nUsan);
		model.addAttribute("showUsan", nUsan > 0);

		int nUsa = relationServ.findByTypeAndOrigin("use", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nUsa", nUsa);
		model.addAttribute("showUsa", nUsa > 0);

		int nAsociados = relationServ.findByTypeAndDestiny("association", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nAsociados", nAsociados);
		model.addAttribute("showAsociados", nAsociados > 0);

		int nAsociado = relationServ.findByTypeAndOrigin("association", unitConc).size() - pageDef.getPageSize();
		model.addAttribute("nAsociado", nAsociado);
		model.addAttribute("showAsociado", nAsociado > 0);

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
		if (relation.equals("padres")) {
			Page<Relation> ok = relationServ.findByTypeAndDestiny("inheritance", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
		}
		if (relation.equals("hijas")) {
			Page<Relation> ok = relationServ.findByTypeAndOrigin("inheritance", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
			return "ajaxUnit2";
		}
		if (relation.equals("compuestos")) {
			Page<Relation> ok = relationServ.findByTypeAndDestiny("composition", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
		}
		if (relation.equals("partes")) {
			Page<Relation> ok = relationServ.findByTypeAndOrigin("composition", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
			return "ajaxUnit2";
		}
		if (relation.equals("usan")) {
			Page<Relation> ok = relationServ.findByTypeAndDestiny("use", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
		}
		if (relation.equals("usa")) {
			Page<Relation> ok = relationServ.findByTypeAndOrigin("use", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
			return "ajaxUnit2";
		}
		if (relation.equals("asociados")) {
			Page<Relation> ok = relationServ.findByTypeAndDestiny("association", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
		}
		if (relation.equals("asociado")) {
			Page<Relation> ok = relationServ.findByTypeAndOrigin("association", unitConc, PageRequest.of(page, 5));
			model.addAttribute("ajax", ok);
			return "ajaxUnit2";
		}

		return "ajaxUnit";
	}

	@RequestMapping("/addRelationOrigin/{type}/{unit}")
	public String addRelationOrigin(Model model, @RequestParam String selected, HttpServletRequest request,
			@PathVariable String unit, @PathVariable String type) {
		Unit unitRelated = unitServ.findByName(unit);

		String newSel = request.getParameter("selected");

		Unit unitSelected = unitServ.findByName(newSel);

		relationServ.save(new Relation(type, unitSelected, unitRelated));

		return "redirect:/" + unit;
	}

	@RequestMapping("/addRelationDestiny/{type}/{unit}")
	public String addRelationDestiny(Model model, @RequestParam String selected, HttpServletRequest request,
			@PathVariable String unit, @PathVariable String type) {
		Unit unitRelated = unitServ.findByName(unit);

		String newSel = request.getParameter("selected");

		Unit unitSelected = unitServ.findByName(newSel);

		relationServ.save(new Relation(type, unitRelated, unitSelected));

		return "redirect:/" + unit;
	}

	@RequestMapping("/deleteRelation/{type}/{origin}/{destiny}")
	public String deleteRelationOrigin(Model model, @PathVariable String type, @PathVariable String origin,
			@PathVariable String destiny, HttpServletRequest request) {
		Unit unitOrigin = unitServ.findByName(origin);

		List<Relation> unitsCand = relationServ.findByTypeAndOrigin(type, unitOrigin);

		Relation relationToRemove = unitsCand.get(0);

		relationServ.delete(relationToRemove);

		return "redirect:/" + destiny;
	}

	@RequestMapping("/deleteRelationDestiny/{type}/{destiny}/{origin}")
	public String deleteRelationDestiny(Model model, @PathVariable String type, @PathVariable String origin,
			@PathVariable String destiny, HttpServletRequest request) {
		Unit unitDestiny = unitServ.findByName(destiny);

		List<Relation> unitsCand = relationServ.findByTypeAndDestiny(type, unitDestiny);

		Relation relationToRemove = unitsCand.get(0);

		relationServ.delete(relationToRemove);

		return "redirect:/" + origin;
	}

	@RequestMapping("/saveCard/{type}/{unit}")
	public String saveCard(Model model, @PathVariable String type, @PathVariable String unit,
			@RequestParam String desc) {
		Unit unitConc = unitServ.findByName(unit);
		Card card = cardServ.findByUnitAsocAndType(unitConc, type);
		card.setDesc(desc);
		cardServ.save(card);

		return "redirect:/{unit}";
	}

	@RequestMapping("/addCard/{unit}")
	public String addCard(Model model, @PathVariable String unit, @RequestParam String newCard) {
		Unit unitConc = unitServ.findByName(unit);
		Card card = new Card(newCard, "", unitConc);
		cardServ.save(card);

		return "redirect:/{unit}";
	}

	@PostMapping("/saveImage/{type}/{unit}")
	public String saveImage(Model model, @PathVariable String type, @PathVariable String unit,
			@RequestParam("file") MultipartFile file) {
		Unit unitConc = unitServ.findByName(unit);
		Card card = cardServ.findByUnitAsocAndType(unitConc, type);

		if (!file.isEmpty()) {
			try {
				File uploadedFile = new File(FILES_FOLDER.toFile(), unit + type);
				file.transferTo(uploadedFile);
				card.setPhoto(true);
				cardServ.save(card);

				return "redirect:/{unit}";
			} catch (Exception e) {
				model.addAttribute("error", e.getClass().getName() + ":" + e.getMessage());
				return "error";
			}
		} else {
			model.addAttribute("error", "The file is empty");
			return "error";
		}
	}

	@RequestMapping("/image/{unit}/{type}")
	public void handleFileDownload(@PathVariable String unit, @PathVariable String type, HttpServletResponse response)
			throws FileNotFoundException, IOException {

		Path image = FILES_FOLDER.resolve(unit + type);

		if (Files.exists(image)) {
			response.setContentType("image/jpeg");
			response.setContentLength((int) image.toFile().length());
			FileCopyUtils.copy(Files.newInputStream(image), response.getOutputStream());
		} else {
			response.sendError(404, "File" + unit + type + "(" + image.toAbsolutePath() + ") does not exist");
		}
	}

}
