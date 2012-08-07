package org.jdominion.decisions;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.Hand;
import org.jdominion.NullPlayer;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.revealedCards.EmptyOption;
import org.jdominion.decisions.revealedCards.Option;
import org.jdominion.decisions.revealedCards.RevealedCard;

public abstract class ChooseOneCardFromRevealedCards extends ChooseFromRevealedCards {

	private Option optionToDisplay;

	public ChooseOneCardFromRevealedCards(String userMessage, boolean cancelable, CardList revealedCards, Option optionToDisplay) {
		super(userMessage, cancelable, createRevealedCardsList(revealedCards, optionToDisplay));
		this.optionToDisplay = optionToDisplay;
	}
	
	private static List<RevealedCard> createRevealedCardsList(CardList cards, Option option) {
		List<RevealedCard> revealedCards = new ArrayList<RevealedCard>();
		for(Card card: cards) {
			revealedCards.add(new RevealedCard(card, new NullPlayer(), getPossibleOptions(option)));
		}
		return revealedCards;
	}

	private static List<Option> getPossibleOptions(Option option) {
		List<Option> optionList = new ArrayList<Option>();
		optionList.add(EmptyOption.getInstance());
		optionList.add(option);
		return optionList;
	}

	@Override
	public void changeOption(RevealedCard card, Option newOption) {
		// Because there must be exactly one selected card, you can't unselect a card
		// Otherwise we wouldn't know which card to discard
		if (newOption == EmptyOption.getInstance()) {
			return;
		}
		// make sure that exactly one card is selected
		for (RevealedCard otherCard : this.getRevealedCards()) {
			if (otherCard != card) {
				if (otherCard.getChoosenOption() == optionToDisplay) {
					otherCard.setChoosenOption(EmptyOption.getInstance());
				}
			}
		}
		card.setChoosenOption(newOption);
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		CardList revealedCards = getCardListFromRevealedCards(getRevealedCards());
		Card defaultCard = defaultCard(currentTurn, revealedCards);
		for (RevealedCard revealedCard : getRevealedCards()) {
			if (revealedCard.getRevealedCard().equals(defaultCard)) {
				revealedCard.setChoosenOption(optionToDisplay);
			} else {
				revealedCard.setChoosenOption(EmptyOption.getInstance());
			}
		}
		this.setAnswer(new ArrayList<RevealedCard>(getRevealedCards()));
	}

	protected abstract Card defaultCard(Turn currentTurn, CardList revealedCards);

	@Override
	public boolean isValidAnswer(List<RevealedCard> answer) {
		int selectedCards = 0;
		for (RevealedCard revealedCard : answer) {
			if (revealedCard.getChoosenOption() == optionToDisplay) {
				selectedCards++;
			}
		}
		if (selectedCards != 1) {
			return false;
		}
		return super.isValidAnswer(answer);
	}
	
	public Card getChoosenCard() {
		for (RevealedCard revealedCard : getAnswer()) {
			if (revealedCard.getChoosenOption() == optionToDisplay) {
				return revealedCard.getRevealedCard();
			}
		}
		assert false : "there was no card choosen";
		return null;
	}

}
