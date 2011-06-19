package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.TrashForCoin;

public class Salvager extends Card {

	public Salvager() {
		super("Salvager", 4);
		this.addCardEffect(new AddBuys(1));
		this.addCardEffect(new TrashForCoin());
	}

}
