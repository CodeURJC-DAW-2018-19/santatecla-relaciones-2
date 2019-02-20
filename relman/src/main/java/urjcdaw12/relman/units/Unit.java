package urjcdaw12.relman.units;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import urjcdaw12.relman.cards.Card;
import urjcdaw12.relman.relations.Relation;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
