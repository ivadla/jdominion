package org.jdominion.aiStrategies;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.base.Remodel;
import org.jdominion.cards.common.Copper;
import org.jdominion.cards.common.Curse;
import org.jdominion.cards.common.Duchy;
import org.jdominion.cards.common.Estate;
import org.jdominion.cards.common.Gold;
import org.jdominion.cards.common.Province;
import org.jdominion.cards.common.Silver;
import org.jdominion.decisions.ChooseActionCardToPlay;
import org.jdominion.decisions.ChooseCardToBuy;
import org.jdominion.decisions.ChooseCardToGain;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;
import org.jdominion.effects.CardEffect;
import org.jdominion.effects.base.RemodelEffect;

public class RemodelStrategy extends GenericAI {

	private int numOfRemodelsToBuy = 1;
	private int remodelsBought = 0;
	private int remodelIntoVictoryPointsAfterTurn = 11;

	@Override
	public String getName() {
		return "Remodel";
	}

	@Override
	public List<Class<? extends Card>> getNeededCards() {
		List<Class<? extends Card>> neededCards = new ArrayList<Class<? extends Card>>();
		neededCards.add(Remodel.class);
		return neededCards;
	}

	public void decide(ChooseCardsFromHandToTrash decision, RemodelEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		List<Class<? extends Card>> cardsToTrash = new ArrayList<Class<? extends Card>>();
		if (getGame().getTurnCounter() <= remodelIntoVictoryPointsAfterTurn) {
			cardsToTrash.add(Estate.class);
		}
		if ((hand.countCoins() > 10) || (hand.countCoins() < 5)) {
			cardsToTrash.add(Gold.class);
		}
		cardsToTrash.add(Curse.class);
		cardsToTrash.add(Remodel.class);
		cardsToTrash.add(Gold.class);
		if (getGame().getTurnCounter() > remodelIntoVictoryPointsAfterTurn && (hand.countCoins() < 5 || hand.countCoins() >= 7)) {
			cardsToTrash.add(Silver.class);
		}
		cardsToTrash.add(Copper.class);
		cardsToTrash.add(Silver.class);

		setCardFromHandAsAnswer(decision, hand, cardsToTrash, false);
		if (!decision.isAnswered()) {
			decision.addAnswer(hand.getFirst());
		}
	}

	public void decide(ChooseCardToGain decision, RemodelEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		List<Class<? extends Card>> cardsToGain = new ArrayList<Class<? extends Card>>();
		cardsToGain.add(Province.class);

		if (getGame().getTurnCounter() > remodelIntoVictoryPointsAfterTurn) {
			cardsToGain.add(Duchy.class);
			cardsToGain.add(Estate.class);
		} else {
			cardsToGain.add(Gold.class);
			cardsToGain.add(Remodel.class);
			cardsToGain.add(Silver.class);
		}
		// this cases only happen when the above piles are empty
		cardsToGain.add(Estate.class);
		cardsToGain.add(Copper.class);
		cardsToGain.add(Curse.class);
		setCardFromSupplyAsAnswer(decision, cardsToGain, false);
	}

	public void decide(ChooseCardToBuy decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		List<Class<? extends Card>> cardsToBuy = new ArrayList<Class<? extends Card>>();
		cardsToBuy.add(Province.class);
		cardsToBuy.add(Gold.class);
		if (remodelsBought < numOfRemodelsToBuy) {
			cardsToBuy.add(Remodel.class);
		}
		cardsToBuy.add(Silver.class);

		setCardFromSupplyAsAnswer(decision, cardsToBuy, true);

		if (decision.getAnswer() == Remodel.class) {
			remodelsBought++;
		}
	}

	public void decide(ChooseActionCardToPlay decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		if (hand.contains(Remodel.class)) {
			if (getGame().getTurnCounter() > remodelIntoVictoryPointsAfterTurn) {
				decision.addAnswer(hand.getCardByClass(Remodel.class));
				return;
			}
			if ((hand.contains(Estate.class)) || (hand.countCard(Remodel.class) > 1) || (hand.countCoins() > 10)
					|| ((hand.countCoins() < 8) && (hand.contains(Gold.class)))) {
				decision.addAnswer(hand.getCardByClass(Remodel.class));
				return;
			}
		}
		decision.setCanceled(true);
	}

}
