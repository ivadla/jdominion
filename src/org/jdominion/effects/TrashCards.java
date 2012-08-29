package org.jdominion.effects;

import org.jdominion.Card;
import org.jdominion.CardList;
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
		CardList cardsToTrash = chooseCardsToTrash(activePlayer, currentTurn.getGame(), currentTurn, supply);
		activePlayer.trashCards(cardsToTrash, currentTurn.getGame());
		return cardsToTrash.size() >= minimumNumberOfCardsToTrash;
	}

	protected CardList chooseCardsToTrash(Player activePlayer, Game game, Turn currentTurn, Supply supply) {
		CardList cardsTotrash = new CardList();

		do {
			if (activePlayer.getHandSize() == 0) {
				return cardsTotrash;
			}
			ChooseCardsFromHandToTrash decision = new ChooseCardsFromHandToTrash(cardsTotrash.size() >= minimumNumberOfCardsToTrash, 1, 1,
					activePlayer.getHand());
			activePlayer.decide(decision, this);

			if (!decision.isCanceled()) {
				Card cardToTrash = decision.getAnswer().getFirst();
				activePlayer.removeCardFromHand(cardToTrash);
				cardsTotrash.add(cardToTrash);
			} else if (cardsTotrash.size() >= minimumNumberOfCardsToTrash) {
				return cardsTotrash;
			}
		} while ((cardsTotrash.size() < maximumNumberOfCardsToTrash));

		return cardsTotrash;
	}

	@Override
	public String getText() {
		return "Trash " + maximumNumberOfCardsToTrash + " Card" + (maximumNumberOfCardsToTrash > 1 ? "s" : "");
	}
}
