package urjcdaw12.relman;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Relation {
	
	private enum tipo {Composicion,Uso,Asociacion,Herencia};
	
	@ManyToOne
	private Unit origin;
	
	@ManyToOne
	private Unit destiny;
	
	protected Relation() {}
	
	

}
