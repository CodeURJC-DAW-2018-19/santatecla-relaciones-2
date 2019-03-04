package urjcdaw12.relman.cards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import urjcdaw12.relman.units.Unit;

@Entity
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String type;
	@Column(columnDefinition = "LONGTEXT")
	private String description;


	@ManyToOne
	private Unit unitAsoc; 

	private boolean photo;

	protected Card() {
		this.photo = false;
	}

	public boolean isPhoto() {
		return photo;
	}

	public void setPhoto(boolean photo) {
		this.photo = photo;
	}

	public Card(String t, String de, Unit unit) {
		this.type = t;
		this.description = de;
		this.unitAsoc = unit;
	}

	public Unit getUnitAsoc() {
		return unitAsoc;
	}

	public void setUnitAsoc(Unit unitAsoc) {
		this.unitAsoc = unitAsoc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

}
