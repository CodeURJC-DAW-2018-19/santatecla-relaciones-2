package urjcdaw12.relman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;
import urjcdaw12.relman.relations.Relation;
import urjcdaw12.relman.relations.RelationService;
import urjcdaw12.relman.trees.LinkedTree;
import urjcdaw12.relman.trees.Position;
import urjcdaw12.relman.units.Unit;
import urjcdaw12.relman.units.UnitService;

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
	
	//METHODS FOR COMPOSITION HIERARCHY
	
	public void compositionUML(Unit unit, Model model) {
		this.treeComp= new LinkedTree<Unit>();
		Position<Unit> root= treeComp.addRoot(unit);
		createTreeComp(root);
 
		
		String path="images/comp"+unit.getName()+".plantuml";
		
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writeOnPlantUMLComp(writer);
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
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createTreeComp(Position<Unit>unit){
		List<Relation> list2=relationServ.findByTypeAndOrigin("Composición", unit.getElement());
		for (Relation rel: list2) {
			Position<Unit> son=treeComp.add(rel.getDestiny(), unit);
			createTreeComp(son);
		}
	}
	
	
	//Writting the PlantUML
	public void writeOnPlantUMLComp(PrintWriter writer) {
		writer.println("@startuml");
		Position<Unit> root= treeComp.root();
		Unit unit=root.getElement();
		List<Position<Unit>> sons= (List<Position<Unit>>) treeComp.children(root);
		
		for(Position<Unit> unitSon:sons) {
			writer.println(unit.getName()+" *-- "+unitSon.getElement().getName());
			writeOnPlantUMLCompRec(writer, unitSon);
		}
		
		writer.println("@enduml");
	}
	
	public void writeOnPlantUMLCompRec(PrintWriter writer, Position<Unit> unit) {
		List<Position<Unit>> sons= (List<Position<Unit>>) treeComp.children(unit);
		
		for(Position<Unit> unitSon:sons) {
			writer.println(unit.getElement().getName()+" *-- "+unitSon.getElement().getName());
			writeOnPlantUMLCompRec(writer, unitSon);
		}
	}
	
	
	
	
	
	//MEHTODS to make the Classification UML
	public void clasificationUML(Unit unit, Model model) {
		this.treeClas= new LinkedTree<Unit>();
		Position<Unit> root= treeClas.addRoot(unit);
		createTreeClas(root);
 
		
		String path="images/clas"+unit.getName()+".plantuml";
		
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writeOnPlantUMLClas(writer);
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
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createTreeClas(Position<Unit>unit){
		List<Relation> list2=relationServ.findByTypeAndOrigin("Herencia", unit.getElement());
		for (Relation rel: list2) {
			Position<Unit> son=treeClas.add(rel.getDestiny(), unit);
			createTreeClas(son);
		}
	}
	
	
	//Writting the PlantUML
	public void writeOnPlantUMLClas(PrintWriter writer) {
		writer.println("@startuml \n set namespaceSeparator none");
		Position<Unit> root= treeClas.root();
		Unit unit=root.getElement();
		List<Position<Unit>> sons= (List<Position<Unit>>) treeClas.children(root);
		
		for(Position<Unit> unitSon:sons) {
			String unitTitle= unitSon.getElement().getName();
			writer.println(unit.getName()+" <|-- "+unitTitle);
			writeOnPlantUMLClasRec(writer, unitSon);
		}
		
		writer.println("@enduml");
	}
	
	public void writeOnPlantUMLClasRec(PrintWriter writer, Position<Unit> unit) {
		List<Position<Unit>> sons= (List<Position<Unit>>) treeClas.children(unit);
		
		for(Position<Unit> unitSon:sons) {
			String unitTitle= unitSon.getElement().getName();
			writer.println(unit.getElement().getName()+" <|-- "+unitTitle);
			writeOnPlantUMLClasRec(writer, unitSon);
		}
	}

	
	

}
