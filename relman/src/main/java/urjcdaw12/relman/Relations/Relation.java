package urjcdaw12.relman.Relations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import urjcdaw12.relman.Units.Unit;

@Entity
public class Relation {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String type;  /*Esta puesto asi porque no se puede hacer enum de Strings de forma sencilla. 
	Controlamos luego que no sea uno de los 4 posibles y ya*/

	
	@ManyToOne
	private Unit origin;
	
	@ManyToOne
	private Unit destiny;
	
	protected Relation() {}
	
	public Relation(String tipo,Unit origin, Unit destiny) {
		this.origin=origin;
		this.destiny=destiny;
		this.setType(tipo);
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
	
	

}
