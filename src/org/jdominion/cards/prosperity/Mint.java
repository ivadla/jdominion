package org.jdominion.cards.prosperity;

import org.jdominion.Card;
import org.jdominion.effects.prosperity.MintEffect;
import org.jdominion.effects.prosperity.MintOnBuyEffect;

public class Mint extends Card {

	public Mint() {
		super("Mint", 5);
		addCardEffect(new MintEffect());
		addCardEffect(new MintOnBuyEffect());
	}
}
