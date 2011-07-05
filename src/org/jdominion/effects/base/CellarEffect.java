package org.jdominion.effects.base;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseCardFromHandToDiscard;
import org.jdominion.effects.CardEffectAction;

public class CellarEffect extends CardEffectAction {
	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		int numberOfCardsToDraw = 0;
		ChooseCardFromHandToDiscard decision = new ChooseCardFromHandToDiscard(true, activePlayer.getHand());
		while(activePlayer.getHandSize() > 0){
			activePlayer.decide(decision, this);
			if(decision.isCanceled()){
				break;
			} else {
				activePlayer.discardCardsFromHand(decision.getAnswer());
				numberOfCardsToDraw++;
			}
		}
		activePlayer.drawCardsIntoHand(numberOfCardsToDraw);
		return true;
	}
}
