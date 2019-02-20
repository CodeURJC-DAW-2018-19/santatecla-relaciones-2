package urjcdaw12.relman.cards;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import urjcdaw12.relman.units.Unit;

@Service
public class CardService {

	
	@Autowired
	private CardRepository cardRep;
	
	public Optional<Card> findOne(long id) {
		return cardRep.findById(id);
	}

	public List<Card> findAll() {
		return cardRep.findAll();
	}

	public Card save(Card card) {
		return cardRep.save(card);
	}

	public void delete(long id) {
		cardRep.deleteById(id);
	}
	
	public List<Card>findByType(String type){
		return cardRep.findByType(type);
	}
	
	public List<Card>findByUnitAsoc(Unit unit){
		return cardRep.findByUnitAsoc(unit);
	}
	
	public Card findByUnitAsocAndType(Unit unit, String type) {
		return cardRep.findByUnitAsocAndType(unit, type);
	}
	
}
