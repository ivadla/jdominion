package org.jdominion.effects.cornucopia;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectAction;

public class HarvestEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		Set<Class<? extends Card>> newCards = new HashSet<Class<? extends Card>>();
		List<Card> revealedCards = activePlayer.revealCards(4);
		for (Card revealedCard : revealedCards) {
			newCards.add(revealedCard.getClass());
		}

		currentTurn.addExtraMoney(newCards.size());
		activePlayer.placeOnDiscardPile(revealedCards);

		return true;
	}

}
