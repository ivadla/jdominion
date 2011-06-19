package org.jdominion.cards;

import org.jdominion.Card;
import org.jdominion.effects.DrawUpToXCards;

public class Library extends Card {

	public Library() {
		super("Library", new DrawUpToXCards(7, true), 5);
	}

}
