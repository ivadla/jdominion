package org.jdominion.effects.cornucopia;

import java.util.HashSet;
import java.util.Set;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.effects.CardEffectVictory;

public class FairgroundsEffect extends CardEffectVictory {

	@Override
	public int getVictoryPoints(Player owner, CardList allCardsOfPlayer) {
		Set<Class<? extends Card>> differentCardClasses = new HashSet<Class<? extends Card>>();
		for (Card card : allCardsOfPlayer) {
			differentCardClasses.add(card.getClass());
		}
		return (differentCardClasses.size() / 5) * 2;
	}

}
