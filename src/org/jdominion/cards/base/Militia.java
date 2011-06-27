package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.OtherPlayersDiscardCardsDownToX;

public class Militia extends Card {

	public Militia() {
		super("Militia", 4);
		addCardEffect(new AddExtraMoney(2));
		addCardEffect(new OtherPlayersDiscardCardsDownToX(3));
	}

}
