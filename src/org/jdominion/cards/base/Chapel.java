package org.jdominion.cards.base;

import org.jdominion.Card;
import org.jdominion.effects.TrashCards;

public class Chapel extends Card {
	public Chapel() {
		super("Chapel", new TrashCards(0, 4), 2);
	}
}
