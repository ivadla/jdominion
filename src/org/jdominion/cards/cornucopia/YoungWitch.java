package org.jdominion.cards.cornucopia;

import org.jdominion.Card;
import org.jdominion.effects.DiscardCards;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.YoungWitchEffect;

public class YoungWitch extends Card {

	public YoungWitch() {
		super("Young Witch", 4);
		addCardEffect(new DrawCards(2));
		addCardEffect(new DiscardCards(2));
		addCardEffect(new YoungWitchEffect());
	}

}
