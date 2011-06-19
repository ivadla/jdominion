package org.jdominion.aiStrategies;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.Chapel;
import org.jdominion.cards.Copper;
import org.jdominion.cards.Curse;
import org.jdominion.cards.Estate;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;
import org.jdominion.effects.TrashCards;

public class ChapelMoney extends BuyOneTypeOfActionCard {

	private int minimumCoinsInDeck;

	public ChapelMoney(int minimumCoinsInDeck) {
		super(Chapel.class, 1);
		this.minimumCoinsInDeck = minimumCoinsInDeck;
	}

	public ChapelMoney() {
		this(6);
	}

	public void decide(ChooseCardsFromHandToTrash decision, TrashCards effect, Hand hand, Turn currentTurn,
			Supply supply) {
		List<Class<? extends Card>> cardsToTrash = new ArrayList<Class<? extends Card>>();
		cardsToTrash.add(Curse.class);
		cardsToTrash.add(Estate.class);

		if ((currentTurn.getActivePlayer().countCoins(currentTurn) > minimumCoinsInDeck)) {
			cardsToTrash.add(Copper.class);
		}
		setCardFromHandAsAnswer(decision, hand, cardsToTrash, true);
	}
}
