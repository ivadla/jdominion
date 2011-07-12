package org.jdominion.effects.base;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.effects.CardEffectVictory;

public class GardenEffect extends CardEffectVictory {

	@Override
	public int getVictoryPoints(Player owner, CardList allCardsOfPlayer) {
		return allCardsOfPlayer.size() / 10;
	}

}
