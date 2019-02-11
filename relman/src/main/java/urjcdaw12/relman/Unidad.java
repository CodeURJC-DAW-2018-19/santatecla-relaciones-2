package urjcdaw12.relman;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Unidad {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String nombre;
	
	//Supongo que todas las listas deberian ser atributos
	
	protected Unidad() {}
	
	public Unidad (String nombre) {
		this.nombre=nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Unidad [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
}
