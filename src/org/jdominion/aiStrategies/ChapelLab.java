package org.jdominion.aiStrategies;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.*;
import org.jdominion.decisions.ChooseCardToBuy;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;
import org.jdominion.effects.CardEffect;
import org.jdominion.effects.TrashCards;

public class ChapelLab extends GenericAI {

	private int buyEstatesAfterTurn = 10;
	private int buyDuchiesAfterTurn = 10;
	private int minimumCoinsInDeck = 6;
	private int chapelsBought = 0;

	@Override
	public String getName() {
		return "ChapelLab";
	}
	
	@Override
	public List<Class<? extends Card>> getNeededCards() {
		List<Class<? extends Card>> neededCards = new ArrayList<Class<? extends Card>>();
		neededCards.add(Chapel.class);
		neededCards.add(Laboratory.class);
		return neededCards;
	}

	public void decide(ChooseCardsFromHandToTrash decision, TrashCards effect, Hand hand, Turn currentTurn,
			Supply supply) {
		List<Class<? extends Card>> cardsToTrash = new ArrayList<Class<? extends Card>>();
		cardsToTrash.add(Curse.class);
		if (currentTurn.getTurnNumber() <= buyEstatesAfterTurn) {
			cardsToTrash.add(Estate.class);
		}
		if ((currentTurn.getActivePlayer().countCoins(currentTurn) > minimumCoinsInDeck)) {
			cardsToTrash.add(Copper.class);
		}
		setCardFromHandAsAnswer(decision, hand, cardsToTrash, true);
	}

	public void decide(ChooseCardToBuy decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		List<Class<? extends Card>> cardsToBuy = new ArrayList<Class<? extends Card>>();

		cardsToBuy.add(Province.class);
		if (currentTurn.getTurnNumber() > buyDuchiesAfterTurn) {
			cardsToBuy.add(Duchy.class);
		}
		cardsToBuy.add(Gold.class);
		cardsToBuy.add(Laboratory.class);
		if (chapelsBought == 0) {
			cardsToBuy.add(Chapel.class);
		}
		cardsToBuy.add(Silver.class);
		if (currentTurn.getTurnNumber() > buyEstatesAfterTurn) {
			cardsToBuy.add(Estate.class);
		}

		setCardFromSupplyAsAnswer(decision, cardsToBuy, true);
		if (decision.getAnswer() == Chapel.class) {
			chapelsBought++;
		}
	}

}
