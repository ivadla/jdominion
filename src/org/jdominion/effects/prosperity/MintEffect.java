package org.jdominion.effects.prosperity;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.decisions.prosperity.ChooseTreasureCardToGainACopy;
import org.jdominion.effects.CardEffectAction;

public class MintEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (!activePlayer.getHand().contains(Type.TREASURE)) {
			return false;
		}

		ChooseTreasureCardToGainACopy decision = new ChooseTreasureCardToGainACopy(activePlayer.getHand());
		activePlayer.decide(decision, this);
		if (decision.isCanceled()) {
			return false;
		}

		Card revealedCard = decision.getAnswer().getFirst();
		activePlayer.revealCardFromHand(revealedCard);
		activePlayer.gainCard(revealedCard.getClass(), supply, currentTurn);

		return true;
	}

}
