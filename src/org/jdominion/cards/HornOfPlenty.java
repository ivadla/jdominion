package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.HornOfPlentyEffect;

public class HornOfPlenty extends Card {

	public HornOfPlenty() {
		super("Horn of Plenty", new HornOfPlentyEffect(), 5);
	}
}
