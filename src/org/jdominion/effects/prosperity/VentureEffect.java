package org.jdominion.effects.prosperity;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.effects.CardEffectTreasure;

public class VentureEffect extends CardEffectTreasure {

	public VentureEffect() {
		super(0); // we have a normal treasure effect for the coins that venture provides
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		CardList revealedCards = new CardList();
		Card revealedCard;
		while ((revealedCard = activePlayer.revealCard()) != null) {

			if (revealedCard.isOfType(Type.TREASURE)) {
				activePlayer.placeOnDiscardPile(revealedCards);
				currentTurn.playCard(activePlayer, supply, revealedCard);
				return true;
			} else {
				revealedCards.add(revealedCard);
			}
		}
		activePlayer.placeOnDiscardPile(revealedCards);
		return false;
	}

}
