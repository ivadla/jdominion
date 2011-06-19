package org.jdominion.aiStrategies;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.Gold;
import org.jdominion.cards.Province;
import org.jdominion.cards.Silver;
import org.jdominion.decisions.ChooseActionCardToPlay;
import org.jdominion.decisions.ChooseCardToBuy;
import org.jdominion.effects.CardEffect;

public abstract class BuyOneTypeOfActionCard extends GenericAI {

	private Class<? extends Card> cardTypeToBuy;
	private int numberOfCardsToBuy;
	private int numberOfCardsBought = 0;

	public BuyOneTypeOfActionCard(Class<? extends Card> cardTypeToBuy, int numberOfCardsToBuy) {
		this.cardTypeToBuy = cardTypeToBuy;
		this.numberOfCardsToBuy = numberOfCardsToBuy;
	}

	@Override
	public String getName() {
		return "BuyOnly" + cardTypeToBuy.getSimpleName();
	}

	@Override
	public List<Class<? extends Card>> getNeededCards() {
		List<Class<? extends Card>> neededCards = new ArrayList<Class<? extends Card>>();
		neededCards.add(cardTypeToBuy);
		return neededCards;
	}

	public void decide(ChooseCardToBuy decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		List<Class<? extends Card>> cardsToBuy = new ArrayList<Class<? extends Card>>();
		cardsToBuy.add(Province.class);
		cardsToBuy.add(Gold.class);
		if (numberOfCardsBought < numberOfCardsToBuy) {
			cardsToBuy.add(cardTypeToBuy);
		}
		cardsToBuy.add(Silver.class);

		setCardFromSupplyAsAnswer(decision, cardsToBuy, true);

		if (decision.getAnswer() == cardTypeToBuy) {
			numberOfCardsBought++;
		}
	}

	public void decide(ChooseActionCardToPlay decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		if (hand.contains(cardTypeToBuy)) {
			decision.addAnswer(hand.getCardByClass(cardTypeToBuy));
		} else {
			decision.setCanceled(true);
		}
	}

}
