package org.jdominion.cards.intrigue;

import org.jdominion.Card;
import org.jdominion.effects.intrigue.PawnEffect;

public class Pawn extends Card {

	public Pawn() {
		super("Pawn", new PawnEffect(), 2);
	}
}
