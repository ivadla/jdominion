package org.jdominion.effects.hinterlands;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Card.Type;
import org.jdominion.effects.CardEffectVictory;

public class SilkRoadEffect extends CardEffectVictory {
	@Override
	public int getVictoryPoints(Player owner, CardList allCardsOfPlayer) {
		return (int) Math.floor(allCardsOfPlayer.countCardsOfType(Type.VICTORY) / 4.0);
	}

}
