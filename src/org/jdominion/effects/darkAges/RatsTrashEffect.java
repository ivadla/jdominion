package org.jdominion.effects.darkAges;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Game;
import org.jdominion.Hand;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.darkAges.Rats;
import org.jdominion.decisions.darkAges.ChooseCardFromHandToTrashThatIsNotARats;
import org.jdominion.effects.CardEffectAction;

public class RatsTrashEffect extends CardEffectAction {

	@Override
	 public void gameStarted(Game game) {
		for (int i = 0; i < 10; i++) {
			game.getSupply().getPile(Rats.class).putCard(new Rats());
		}
	 }

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		if (activePlayer.getHandSize() == 0) {
			return false;
		}

		// Reveal a hand full of rats
		if (activePlayer.getHand().countCard(Rats.class) == activePlayer.getHandSize()) {
			activePlayer.revealHand();
			return true;
		}

		ChooseCardFromHandToTrashThatIsNotARats decision = new ChooseCardFromHandToTrashThatIsNotARats(createHandWithoutRats(activePlayer.getHand()));

		activePlayer.decide(decision, this);

		activePlayer.trashCard(decision.getAnswer().getFirst(), currentTurn.getGame());

		return true;
	}

	private Hand createHandWithoutRats(Hand hand) {
		CardList newHandCards = new CardList();

		for (Card cardInHand : hand) {
			if (!cardInHand.getClass().equals(Rats.class)) {
				newHandCards.add(cardInHand);
			}
		}
		return new Hand(newHandCards);
	}

}
