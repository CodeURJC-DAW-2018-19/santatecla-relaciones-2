package urjcdaw12.relman;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Unit {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	
	private String name;
	
	@OneToMany(mappedBy="unitAsoc",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Card> cards;
	
	@OneToMany(mappedBy="origin",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Relation> relationsOrig;
	
	@OneToMany(mappedBy="destiny",cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Relation> relationsDest;
	
	
	
	//Supongo que todas las listas deberian ser atributos
	
	protected Unit() {}
	
	public Unit (String name) {
		this.name=name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
