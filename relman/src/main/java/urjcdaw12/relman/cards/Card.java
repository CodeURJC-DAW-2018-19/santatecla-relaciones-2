package urjcdaw12.relman.cards;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import urjcdaw12.relman.units.Unit;

@Entity
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String type;
	private String desc;
	private String photoSrc;
	
	@ManyToOne
	private Unit unitAsoc;
	
	protected Card () {}
	
	public Card (String t, String de, Unit unit) {
		this.type=t;
		this.desc=de;
		this.unitAsoc=unit;
	}

	public Unit getUnitAsoc() {
		return unitAsoc;
	}

	public void setUnitAsoc(Unit unitAsoc) {
		this.unitAsoc = unitAsoc;
	}

	public String getPhotoSrc() {
		return photoSrc;
	}

	public void setPhotoSrc(String photoSrc) {
		this.photoSrc = photoSrc;
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
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
