package org.jdominion.effects;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Game;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;

public class TrashCards extends CardEffectAction {

	private int minimumNumberOfCardsToTrash;
	private int maximumNumberOfCardsToTrash;

	public TrashCards(int minimumNumberOfCardsToTrash, int maximumNumberOfCardsToTrash) {
		super();
		this.minimumNumberOfCardsToTrash = minimumNumberOfCardsToTrash;
		this.maximumNumberOfCardsToTrash = maximumNumberOfCardsToTrash;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		List<Card> cardsToTrash = trashCards(activePlayer, currentTurn.getGame(), currentTurn, supply);
		return cardsToTrash.size() >= minimumNumberOfCardsToTrash;
	}

	private List<Card> trashCards(Player activePlayer, Game game, Turn currentTurn, Supply supply) {
		List<Card> trashedCards = new ArrayList<Card>();

		do {
			if (activePlayer.getHandSize() == 0) {
				return trashedCards;
			}
			ChooseCardsFromHandToTrash decision = new ChooseCardsFromHandToTrash(trashedCards.size() >= minimumNumberOfCardsToTrash, 1, 1,
					activePlayer.getHand());
			activePlayer.decide(decision, this);

			if (!decision.isCanceled()) {
				Card cardToTrash = decision.getAnswer().get(0);
				activePlayer.trashCard(cardToTrash, game);
				trashedCards.add(cardToTrash);
			} else if (trashedCards.size() >= minimumNumberOfCardsToTrash) {
				return trashedCards;
			}
		} while ((trashedCards.size() < maximumNumberOfCardsToTrash));

		return trashedCards;
	}
}
