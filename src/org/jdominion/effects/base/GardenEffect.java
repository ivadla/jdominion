package org.jdominion.effects.base;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.effects.CardEffectVictory;

public class GardenEffect extends CardEffectVictory {

	@Override
	public int getVictoryPoints(Player owner, List<Card> allCardsOfPlayer) {
		return allCardsOfPlayer.size() / 10;
	}

}
