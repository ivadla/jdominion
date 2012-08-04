package org.jdominion.cards.hinterlands;

import org.jdominion.Card;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.OtherPlayersDiscardCardsDownToX;
import org.jdominion.effects.OtherPlayersDrawCards;

public class Margrave extends Card {

	public Margrave() {
		super("Margrave", 5);
		addCardEffect(new DrawCards(3));
		addCardEffect(new AddBuys(1));
		addCardEffect(new OtherPlayersDrawCards(1, true));
		addCardEffect(new OtherPlayersDiscardCardsDownToX(3));
	}
}
