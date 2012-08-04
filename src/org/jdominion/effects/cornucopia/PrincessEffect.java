package org.jdominion.effects.cornucopia;

import org.jdominion.effects.ReduceCostsOfCards;

/**
 * A dedicated class is needed for the princess so that the tournament can call gameStarted on it without risking that
 * it may be called twice on the same class(which would result in two instances of the same class being registered for
 * the event CalculatingCardCost)
 * 
 */
public class PrincessEffect extends ReduceCostsOfCards {

	public PrincessEffect() {
		super(2);
	}

}
