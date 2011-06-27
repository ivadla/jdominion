package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.base.OtherPlayersDrawCards;

public class CouncilRoom extends Card {

	public CouncilRoom() {
		super("Council Room", 5);
		addCardEffect(new AddBuys(1));
		addCardEffect(new DrawCards(4));
		addCardEffect(new OtherPlayersDrawCards(1));
	}
}
