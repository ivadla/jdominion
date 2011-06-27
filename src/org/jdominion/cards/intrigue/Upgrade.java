package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.UpgradeEffect;

public class Upgrade extends Card {

	public Upgrade() {
		super("Upgrade", 5);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new UpgradeEffect());
	}

}
