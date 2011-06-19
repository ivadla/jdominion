package org.jdominion.decisions;

import java.util.List;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.decisions.revealedCards.Discard;
import org.jdominion.decisions.revealedCards.Option;
import org.jdominion.decisions.revealedCards.PutOnDeck;
import org.jdominion.decisions.revealedCards.RevealedCard;

public class DiscardRevealedCard extends ChooseFromRevealedCards {

	public DiscardRevealedCard(List<RevealedCard> revealedCards) {
		super("Please choose whether to put the revealed cards back on top of the deck or to discard them", false,
				revealedCards);
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		for (RevealedCard revealedCard : getRevealedCards()) {
			if (revealedCard.getRevealedCard().isOfType(Type.ACTION)
					|| revealedCard.getRevealedCard().isOfType(Type.TREASURE)) {
				if (revealedCard.getOwner() == currentTurn.getActivePlayer()) {
					revealedCard.setChoosenOption(PutOnDeck.getInstance());
				} else {
					revealedCard.setChoosenOption(Discard.getInstance());
				}
			} else { // Victory or Curses
				if (revealedCard.getOwner() == currentTurn.getActivePlayer()) {
					revealedCard.setChoosenOption(Discard.getInstance());
				} else {
					revealedCard.setChoosenOption(PutOnDeck.getInstance());
				}
			}
		}
		setAnswer(getRevealedCards());
	}

	@Override
	public void changeOption(RevealedCard card, Option newOption) {
		card.setChoosenOption(newOption);
	}

}
