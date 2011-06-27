package org.jdominion.cards.cornucopia;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.HuntingPartyEffect;

public class HuntingParty extends Card {

	public HuntingParty() {
		super("Hunting Party", 5);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new HuntingPartyEffect());
	}

}
