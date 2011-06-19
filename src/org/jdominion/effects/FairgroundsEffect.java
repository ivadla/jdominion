package org.jdominion.effects;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdominion.Card;
import org.jdominion.Player;

public class FairgroundsEffect extends CardEffectVictory {

	@Override
	public int getVictoryPoints(Player owner, List<Card> allCardsOfPlayer) {
		Set<Class<? extends Card>> differentCardClasses = new HashSet<Class<? extends Card>>();
		for (Card card : allCardsOfPlayer) {
			differentCardClasses.add(card.getClass());
		}
		return (differentCardClasses.size() / 5) * 2;
	}

}
