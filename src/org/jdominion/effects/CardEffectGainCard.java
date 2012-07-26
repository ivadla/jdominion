package org.jdominion.effects;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.CardPile;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseCardToGain;

public abstract class CardEffectGainCard extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		Supply permittedCards = createSupplyOfPermittedCards(supply);
		ChooseCardToGain gainDecision = new ChooseCardToGain(permittedCards);
		activePlayer.decide(gainDecision, this);

		Class<? extends Card> choosenCard = gainDecision.getAnswer();

		assert permittedCards.isCardAvailable(choosenCard);

		gainCard(choosenCard, activePlayer, currentTurn, supply);

		return true;
	}

	/**
	 * Subclasses can override this method if they want to do something special with or depending on the card
	 * 
	 * @param choosenCard
	 * @param activePlayer
	 * @param supply
	 */
	protected void gainCard(Class<? extends Card> choosenCard, Player activePlayer, Turn currentTurn, Supply supply) {
		activePlayer.gainCard(choosenCard, supply);
	}

	private Supply createSupplyOfPermittedCards(Supply originalSupply) {
		List<CardPile> newPiles = new ArrayList<CardPile>();
		for (CardPile pile : originalSupply.getCardPiles()) {
			if ((!pile.isEmpty()) && (isCardPilePermitted(pile))) {
				newPiles.add(pile);
			}
		}
		return new Supply(newPiles);
	}

	protected abstract boolean isCardPilePermitted(CardPile pile);

}
