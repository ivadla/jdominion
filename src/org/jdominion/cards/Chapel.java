package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.TrashCards;

public class Chapel extends Card {
	public Chapel() {
		super("Chapel", new TrashCards(0, 4), 2);
	}
}
