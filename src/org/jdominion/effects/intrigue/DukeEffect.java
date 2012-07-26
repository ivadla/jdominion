package org.jdominion.effects.intrigue;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.cards.common.Duchy;
import org.jdominion.effects.CardEffectVictory;

public class DukeEffect extends CardEffectVictory {

	@Override
	public int getVictoryPoints(Player owner, CardList allCardsOfPlayer) {
		return allCardsOfPlayer.countCard(Duchy.class);
	}
}
