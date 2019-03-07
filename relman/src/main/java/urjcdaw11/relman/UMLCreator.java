package urjcdaw11.relman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;
import urjcdaw11.relman.relations.Relation;
import urjcdaw11.relman.relations.RelationService;
import urjcdaw11.relman.trees.LinkedTree;
import urjcdaw11.relman.trees.Position;
import urjcdaw11.relman.units.Unit;
import urjcdaw11.relman.units.UnitService;

@Component
public class UMLCreator {

	private LinkedTree<Unit> treeComp;

	private LinkedTree<Unit> treeClas;

	@Autowired
	private RelationService relationServ;
	
	@Autowired
	private UnitService unitServ;

	public LinkedTree<Unit> getTreeComp() {
		return treeComp;
	}

	public void setTreeComp(LinkedTree<Unit> treeComp) {
		this.treeComp = treeComp;
	}

	public LinkedTree<Unit> getTreeClas() {
		return treeClas;
	}

	public void setTreeClas(LinkedTree<Unit> treeClas) {
		this.treeClas = treeClas;
	}

	protected UMLCreator() {
	}

	// METHODS FOR COMPOSITION HIERARCHY

	public void compositionUML(Unit unit) {
		unit.setPhotoComp(true);
		unitServ.save(unit);
		this.treeComp = new LinkedTree<Unit>();
		Position<Unit> root = treeComp.addRoot(unit);
		createTreeComp(root);

		String path = "images/comp" + unit.getName() + ".plantuml";

		try {
			if(treeComp.size()!=1) {
				PrintWriter writer = new PrintWriter(path, "UTF-8");
				writeOnPlantUMLComp(writer);
				writer.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if(treeComp.size()!=1) {
			File source = new File(path);
	
			try {
				SourceFileReader reader = new SourceFileReader(source);
				List<GeneratedImage> list = reader.getGeneratedImages();
				File png = list.get(0).getPngFile();

			
			MultipartFile multipartFile = new MockMultipartFile(png.getName(), new FileInputStream(new File(png.getAbsolutePath())));
			unitServ.saveImageUML(multipartFile, unit.getName(), "comp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void createTreeComp(Position<Unit> unit) {
		List<Relation> list2 = relationServ.findByTypeAndOrigin("composition", unit.getElement());
		for (Relation rel : list2) {
			if(!treeComp.contains(rel.getDestiny())) {
				Position<Unit> child = treeComp.add(rel.getDestiny(), unit);
				createTreeComp(child);
			}
		}
	}

	// Writing the PlantUML
	private void writeOnPlantUMLComp(PrintWriter writer) {
		writer.println("@startuml");
		Position<Unit> root = treeComp.root();
		Unit unit = root.getElement();
		List<Position<Unit>> children = (List<Position<Unit>>) treeComp.children(root);

		for (Position<Unit> unitChild : children) {
			writer.println(unit.getName() + " *-- " + unitChild.getElement().getName());
			writeOnPlantUMLCompRec(writer, unitChild);
		}

		writer.println("@enduml");
	}

	private void writeOnPlantUMLCompRec(PrintWriter writer, Position<Unit> unit) {
		List<Position<Unit>> children = (List<Position<Unit>>) treeComp.children(unit);

		for (Position<Unit> unitChild : children) {
			writer.println(unit.getElement().getName() + " *-- " + unitChild.getElement().getName());
			writeOnPlantUMLCompRec(writer, unitChild);
		}
	}

	// MEHTODS to make the Classification UML
	public void clasificationUML(Unit unit) {
		unit.setPhotoClas(true);
		unitServ.save(unit);
		this.treeClas = new LinkedTree<Unit>();
		Position<Unit> root = treeClas.addRoot(unit);
		createTreeClas(root);

		String path = "images/clas" + unit.getName() + ".plantuml";

		try {
			if(treeClas.size()!=1) {
				PrintWriter writer = new PrintWriter(path, "UTF-8");
				writeOnPlantUMLClas(writer);
				writer.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if(treeClas.size()!=1) {

			File source = new File(path);
	
			try {
				SourceFileReader reader = new SourceFileReader(source);
				List<GeneratedImage> list = reader.getGeneratedImages();
				File png = list.get(0).getPngFile();

			
			MultipartFile multipartFile = new MockMultipartFile(png.getName(), new FileInputStream(new File(png.getAbsolutePath())));
			unitServ.saveImageUML(multipartFile, unit.getName(), "clas");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void createTreeClas(Position<Unit> unit) {
		List<Relation> list2 = relationServ.findByTypeAndOrigin("inheritance", unit.getElement());
		for (Relation rel : list2) {
			if(!treeClas.contains(rel.getDestiny())) {
				Position<Unit> child = treeClas.add(rel.getDestiny(), unit);
				createTreeClas(child);
			}
		}
	}

	// Writing the PlantUML
	private void writeOnPlantUMLClas(PrintWriter writer) {
		writer.println("@startuml \n set namespaceSeparator none");
		Position<Unit> root = treeClas.root();
		Unit unit = root.getElement();
		List<Position<Unit>> children = (List<Position<Unit>>) treeClas.children(root);

		for (Position<Unit> unitChild : children) {
			String unitTitle = unitChild.getElement().getName();
			writer.println(unit.getName() + " <|-- " + unitTitle);
			writeOnPlantUMLClasRec(writer, unitChild);
		}

		writer.println("@enduml");
	}

	private void writeOnPlantUMLClasRec(PrintWriter writer, Position<Unit> unit) {
		List<Position<Unit>> children = (List<Position<Unit>>) treeClas.children(unit);

		for (Position<Unit> unitChild : children) {
			String unitTitle = unitChild.getElement().getName();
			writer.println(unit.getElement().getName() + " <|-- " + unitTitle);
			writeOnPlantUMLClasRec(writer, unitChild);
		}
	}

	// METHODS to do the Context

	public void contextUML(Unit unit) {
		String path = "images/context" + unit.getName() + ".plantuml";

		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writeOnPlantUMLContext(writer, unit);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		File source = new File(path);

		try {
			SourceFileReader reader = new SourceFileReader(source);
			List<GeneratedImage> list = reader.getGeneratedImages();
			File png = list.get(0).getPngFile();
			
			MultipartFile multipartFile = new MockMultipartFile(png.getName(), new FileInputStream(new File(png.getAbsolutePath())));
			unitServ.saveImageUML(multipartFile, unit.getName(), "context");


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeOnPlantUMLContext(PrintWriter writer, Unit unit) {
		writer.println("@startuml \n set namespaceSeparator none");
		List<Relation> parents = relationServ.findByTypeAndDestiny("inheritance", unit);
		List<Relation> children = relationServ.findByTypeAndOrigin("inheritance", unit);

		List<Relation> comps = relationServ.findByTypeAndDestiny("composition", unit);
		List<Relation> parts = relationServ.findByTypeAndOrigin("composition", unit);

		List<Relation> useTo = relationServ.findByTypeAndDestiny("use", unit);
		List<Relation> use = relationServ.findByTypeAndOrigin("use", unit);

		List<Relation> asocsTo = relationServ.findByTypeAndDestiny("association", unit);
		List<Relation> asocs = relationServ.findByTypeAndOrigin("association", unit);

		for (Relation rel : parents) {
			writer.println(rel.getOrigin().getName() + " <|-- " + unit.getName());
		}

		for (Relation rel : comps) {
			writer.println(rel.getOrigin().getName() + " *-- " + unit.getName());
		}

		for (Relation rel : useTo) {
			writer.println(rel.getOrigin().getName() + " <-- " + unit.getName());
		}

		for (Relation rel : asocsTo) {
			writer.println(rel.getOrigin().getName() + " -- " + unit.getName());
		}

		for (Relation rel : children) {
			writer.println(unit.getName() + " <|-- " + rel.getDestiny().getName());
		}

		for (Relation rel : parts) {
			writer.println(unit.getName() + " *-- " + rel.getDestiny().getName());
		}

		for (Relation rel : use) {
			writer.println(unit.getName() + " <-- " + rel.getDestiny().getName());
		}

		for (Relation rel : asocs) {
			writer.println(unit.getName() + " -- " + rel.getDestiny().getName());
		}

		writer.println("@enduml");

	}

}
