package org.jdominion.effects.darkAges;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.cards.common.Silver;
import org.jdominion.effects.CardEffectVictory;

public class FeodumEffect extends CardEffectVictory {

	@Override
	public int getVictoryPoints(Player owner, CardList allCardsOfPlayer) {
		return (int) Math.floor(allCardsOfPlayer.countCard(Silver.class) / 3.0);
	}
}
