package org.jdominion.decisions;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.CardList;
import org.jdominion.decisions.revealedCards.Option;
import org.jdominion.decisions.revealedCards.RevealedCard;

public abstract class ChooseFromRevealedCards extends Decision<List<RevealedCard>> {

	private List<RevealedCard> revealedCards;

	public ChooseFromRevealedCards(String userMessage, boolean cancelable, List<RevealedCard> revealedCards) {
		super(userMessage, cancelable);
		this.revealedCards = revealedCards;
	}

	public List<RevealedCard> getRevealedCards() {
		return new ArrayList<RevealedCard>(revealedCards);
	}

	public abstract void changeOption(RevealedCard card, Option newOption);

	@Override
	public boolean isValidAnswer(List<RevealedCard> answer) {
		CardList listOfAnswerCards = getCardListFromRevealedCards(answer);
		for (RevealedCard revealedCard : getRevealedCards()) {
			if (!listOfAnswerCards.contains(revealedCard.getRevealedCard())) {
				return false;
			}
			if (!revealedCard.getOptions().contains(revealedCard.getChoosenOption())) {
				return false;
			}

		}
		if (answer.size() != getRevealedCards().size()) {
			return false;
		}
		return true;
	}

	private CardList getCardListFromRevealedCards(List<RevealedCard> revealedCards) {
		CardList cardList = new CardList();
		for (RevealedCard revealedCard : revealedCards) {
			cardList.add(revealedCard.getRevealedCard());
		}
		return cardList;
	}

}
