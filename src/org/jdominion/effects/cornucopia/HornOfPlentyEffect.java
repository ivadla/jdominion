package org.jdominion.effects.cornucopia;

import java.util.HashSet;
import java.util.Set;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.decisions.ChooseCardToGain;
import org.jdominion.effects.CardEffectTreasure;

public class HornOfPlentyEffect extends CardEffectTreasure {

	public HornOfPlentyEffect() {
		super(0);
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		Set<Class<? extends Card>> differentCardClasses = new HashSet<Class<? extends Card>>();
		for (Card cardInPlay : currentTurn.getCardsInPlay()) {
			differentCardClasses.add(cardInPlay.getClass());
		}

		Supply permittedCards = supply.createSupplyWithMaximumCost(differentCardClasses.size());

		if (permittedCards.getCardPiles().size() == 0) {
			return false;
		}

		ChooseCardToGain gainDecision = new ChooseCardToGain(permittedCards);
		activePlayer.decide(gainDecision, this);

		Class<? extends Card> choosenCard = gainDecision.getAnswer();

		assert permittedCards.isCardAvailable(choosenCard);

		Card gainedCard = supply.takeCard(choosenCard);

		activePlayer.gainCard(gainedCard, currentTurn, supply);

		if (gainedCard.isOfType(Type.VICTORY)) {
			return trashThisCard(activePlayer, currentTurn);
		}
		return true;
	}

	private boolean trashThisCard(Player activePlayer, Turn currentTurn) {
		if (currentTurn.getGame().getTrash().contains(this.getCard())) {
			return false;
		} else {
			activePlayer.trashCard(getCard(), currentTurn.getGame());
			return true;
		}
	}

}
