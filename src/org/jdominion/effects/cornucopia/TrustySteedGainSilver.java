package org.jdominion.effects.cornucopia;

import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Silver;
import org.jdominion.effects.CardEffectAction;

public class TrustySteedGainSilver extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {

		// gain silver
		for (int i = 0; i < 4; i++) {
			activePlayer.gainCard(Silver.class, supply, currentTurn);
		}

		// discard deck
		CardList deck = activePlayer.getCardsFromDeck(activePlayer.getDeckSize());
		activePlayer.placeOnDiscardPile(deck);

		return true;
	}

	@Override
	public String getText() {
		return "gain 4 Silvers and put your deck into your discard pile";
	}

}
