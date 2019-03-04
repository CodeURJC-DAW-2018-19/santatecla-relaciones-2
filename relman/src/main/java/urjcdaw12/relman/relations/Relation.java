package urjcdaw12.relman.relations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import urjcdaw12.relman.units.Unit;

@Entity
public class Relation {

	public interface Basic {}
	
	public interface OnlyOrigin extends Relation.Basic{}
	
	public interface OnlyDestination extends Relation.Basic{}
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonView(Basic.class)
	private String type;

	@JsonView(OnlyOrigin.class)
	@ManyToOne
	private Unit origin;

	@JsonView(OnlyDestination.class)
	@ManyToOne
	private Unit destiny;

	protected Relation() {}

	public Relation(String type, Unit origin, Unit destiny) {
		this.origin = origin;
		this.destiny = destiny;
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Unit getOrigin() {
		return origin;
	}

	public void setOrigin(Unit origin) {
		this.origin = origin;
	}

	public Unit getDestiny() {
		return destiny;
	}

	public void setDestiny(Unit destiny) {
		this.destiny = destiny;
	}

	@Override
	public String toString() {
		return origin.toString();
	}

}
