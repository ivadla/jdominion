package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.cards.common.Silver;
import org.jdominion.effects.GainCardX;
import org.jdominion.effects.TrashCards;
import org.jdominion.location.Hand;

public class TradingPost extends Card {

	public TradingPost() {
		super("Trading Post", 5);
		addCardEffect(new TrashCards(2, 2));
		addCardEffect(new GainCardX(Silver.class, new Hand()));
		setEffectsDependOnEachOther(true);
	}

}
