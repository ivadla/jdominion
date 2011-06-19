package org.jdominion.decisions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jdominion.Hand;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.revealedCards.Discard;
import org.jdominion.decisions.revealedCards.Gain;
import org.jdominion.decisions.revealedCards.Option;
import org.jdominion.decisions.revealedCards.RevealedCard;
import org.jdominion.decisions.revealedCards.Trash;

public class StealFromRevealedCards extends ChooseFromRevealedCards {

	public StealFromRevealedCards(List<RevealedCard> revealedCards) {
		super("Please choose which card to steal or trash", false, revealedCards);
	}

	@Override
	public void changeOption(RevealedCard card, Option newOption) {

		// make sure that exactly one card per player gets trashed or stolen
		for (RevealedCard otherCard : this.getRevealedCards()) {
			if (otherCard != card) {
				if (otherCard.getOwner() == card.getOwner()) {
					if (newOption != Discard.getInstance() && otherCard.getChoosenOption() != Discard.getInstance()) {
						otherCard.setChoosenOption(Discard.getInstance());
					} else if (newOption == Discard.getInstance()
							&& otherCard.getChoosenOption() == Discard.getInstance()) {
						otherCard.setChoosenOption(Trash.getInstance());
					}
				}
			}
		}
		card.setChoosenOption(newOption);
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		Player lastPlayer = null;
		int costOfMostExpensiveCard = -1;
		for (RevealedCard revealedCard : getRevealedCards()) {
			if (revealedCard.getOwner() != lastPlayer) {
				lastPlayer = revealedCard.getOwner();
				costOfMostExpensiveCard = -1;
			}
			if (revealedCard.getRevealedCard().getCost() > costOfMostExpensiveCard) {
				if (revealedCard.getOptions().contains(Trash.getInstance())
						&& revealedCard.getOptions().contains(Gain.getInstance())) {
					if (revealedCard.getRevealedCard().getCost() == 0) {
						changeOption(revealedCard, Trash.getInstance());
					} else {
						changeOption(revealedCard, Gain.getInstance());
						costOfMostExpensiveCard = revealedCard.getRevealedCard().getCost();
					}
				}
			}
		}
		this.setAnswer(new ArrayList<RevealedCard>(getRevealedCards()));
	}

	@Override
	public boolean isValidAnswer(List<RevealedCard> answer) {
		if (super.isValidAnswer(answer)) {
			if (answer.size() == 0) {
				return true;
			}
			Map<Player, Boolean> playerHasTrashedOrStolenCard = new HashMap<Player, Boolean>();
			for (RevealedCard revealedCard : answer) {
				if (!playerHasTrashedOrStolenCard.containsKey(revealedCard.getOwner())) {
					// only add players to the list who have cards that could
					// get stolen
					if (revealedCard.getOptions().contains(Gain.getInstance())) {
						playerHasTrashedOrStolenCard.put(revealedCard.getOwner(), false);
					}
				}
				if (revealedCard.getChoosenOption() == Gain.getInstance()
						|| revealedCard.getChoosenOption() == Trash.getInstance()) {
					if (playerHasTrashedOrStolenCard.get(revealedCard.getOwner())) {
						return false;
					} else {
						playerHasTrashedOrStolenCard.put(revealedCard.getOwner(), true);
					}
				}
			}
			for (Entry<Player, Boolean> entry : playerHasTrashedOrStolenCard.entrySet()) {
				if (entry.getValue() == false) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

}
