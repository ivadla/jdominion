package org.jdominion.cards.hinterlands;

import org.jdominion.Card;
import org.jdominion.effects.hinterlands.IllGottenGainsOnGainEffect;
import org.jdominion.effects.hinterlands.IllGottenGainsTreasureEffect;

public class IllGottenGains extends Card {

	public IllGottenGains() {
		super("Ill-Gotten Gains", 5);
		addCardEffect(new IllGottenGainsTreasureEffect());
		addCardEffect(new IllGottenGainsOnGainEffect());
	}
}
