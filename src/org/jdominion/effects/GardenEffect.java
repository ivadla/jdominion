package org.jdominion.effects;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;

public class GardenEffect extends CardEffectVictory {

	@Override
	public int getVictoryPoints(Player owner, List<Card> allCardsOfPlayer) {
		return allCardsOfPlayer.size() / 10;
	}

}
