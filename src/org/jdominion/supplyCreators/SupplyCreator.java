package org.jdominion.supplyCreators;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.gui.Config;

public class SupplyCreator {

	private List<Class<? extends Card>> selectedCards;

	public SupplyCreator() {
		this.selectedCards = Config.getSupplyCards();
	}

	public List<Class<? extends Card>> getSelectedCards() {
		return selectedCards;
	}

	public void setSelectedCards(List<Class<? extends Card>> selectedCards) {
		Config.setSupplyCards(selectedCards);
		this.selectedCards = selectedCards;
	}

}
