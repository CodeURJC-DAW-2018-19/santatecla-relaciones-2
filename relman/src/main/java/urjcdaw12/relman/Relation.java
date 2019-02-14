package urjcdaw12.relman;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Relation {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String type;  //Esta puesto asi porque no se puede hacer enum de Strings de forma sencilla. 
	//Controlamos luego que no sea uno de los 4 posibles y ya

	
	@ManyToOne
	private Unit origin;
	
	@ManyToOne
	private Unit destiny;
	
	protected Relation() {}
	
	public Relation(String tipo,Unit origin, Unit destiny) {
		this.origin=origin;
		this.destiny=destiny;
		this.type=tipo;
	}
	
	

}