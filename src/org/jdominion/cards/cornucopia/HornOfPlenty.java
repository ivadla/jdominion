package org.jdominion.cards.cornucopia;

import org.jdominion.Card;
import org.jdominion.effects.cornucopia.HornOfPlentyEffect;

public class HornOfPlenty extends Card {

	public HornOfPlenty() {
		super("Horn of Plenty", new HornOfPlentyEffect(), 5);
	}
}
