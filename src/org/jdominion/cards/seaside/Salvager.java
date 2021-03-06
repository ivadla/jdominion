package org.jdominion.cards.seaside;

import org.jdominion.Card;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.seaside.TrashForCoin;

public class Salvager extends Card {

	public Salvager() {
		super("Salvager", 4);
		this.addCardEffect(new AddBuys(1));
		this.addCardEffect(new TrashForCoin());
	}

}
