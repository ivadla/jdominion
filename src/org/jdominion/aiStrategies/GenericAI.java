package org.jdominion.aiStrategies;

import java.io.Serializable;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Game;
import org.jdominion.Hand;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseCardFromSupply;
import org.jdominion.decisions.ChooseCardsFromHand;
import org.jdominion.decisions.Decision;
import org.jdominion.effects.CardEffect;

public abstract class GenericAI implements IStrategy, Serializable {

	private Game game;

	@Override
	public void setPlayer(Player player) {
		// TODO Auto-generated method stub
	}

	@Override
	public void gameStarted(Game game) {
		this.game = game;
	}

	protected Game getGame() {
		return this.game;
	}

	@Override
	public abstract String getName();	

	@Override
	public boolean canWorkWithSupply(Supply supply) {
		for(Class<? extends Card> card: getNeededCards()) {
			if(!supply.isCardAvailable(card)) {
				return false;
			}
		}
		return true;
	}

	protected boolean isBuyPossible(Class<? extends Card> card, int availableCoins, Supply supply) {
		if (supply.isCardAvailable(card)) {
			if (supply.getCardCost(card) <= availableCoins) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void decide(Decision<?> decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		// TODO: implement a generic decision
	}

	protected void setCardFromSupplyAsAnswer(ChooseCardFromSupply decision, List<Class<? extends Card>> cardClassesOrderedByPriority, boolean cancelDecisionIfNoCardIsAvailable) {
		for(Class<? extends Card>  cardClass: cardClassesOrderedByPriority) {
			if(decision.getAvailableCards().isCardAvailable(cardClass)){
				decision.setAnswer(cardClass);
				return;
			}
		}
		if(cancelDecisionIfNoCardIsAvailable) {
			decision.setCanceled(true);
		}
	}

	protected void setCardFromHandAsAnswer(ChooseCardsFromHand decision, Hand hand, List<Class<? extends Card>> cardClassesOrderedByPriority, boolean cancelDecisionIfNoCardIsInHand) {
		for (Class<? extends Card> cardClass : cardClassesOrderedByPriority) {
			if (hand.contains(cardClass)) {
				decision.addAnswer(hand.getCardByClass(cardClass));
				return;
			}
		}
		if(cancelDecisionIfNoCardIsInHand) {
			decision.setCanceled(true);
		}
	}
}
