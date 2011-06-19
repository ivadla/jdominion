package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseCardToGain;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;

public abstract class TrashGainEffect extends CardEffectAction {

	protected abstract ChooseCardsFromHandToTrash createDecision(Hand hand);

	protected abstract Supply createSupply(Supply supply, Card cardToTrash);

	protected abstract boolean putCardInHand();

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (activePlayer.getHandSize() == 0) {
			return false;
		}
		ChooseCardsFromHandToTrash trashDecision = createDecision(activePlayer.getHand());
		activePlayer.decide(trashDecision, this, activePlayer.getHand(), currentTurn, supply);
		// TODO: decisions should make it impossible to decide something illegal
		assert trashDecision.getAnswer().size() == 1;
		Card cardToTrash = trashDecision.getAnswer().get(0);
		assert cardToTrash != null;

		activePlayer.trashCard(cardToTrash, currentTurn.getGame());

		Supply availableCards = createSupply(supply, cardToTrash);
		if (availableCards.getCardPiles().size() == 0) {
			return false;
		}

		ChooseCardToGain gainDecision = new ChooseCardToGain(availableCards);
		activePlayer.decide(gainDecision, this, activePlayer.getHand(), currentTurn, supply);

		Class<? extends Card> choosenCard = gainDecision.getAnswer();

		assert availableCards.isCardAvailable(choosenCard);
		assert supply.isCardAvailable(choosenCard);

		activePlayer.gainCard(choosenCard, supply, putCardInHand());

		return true;
	}

}
