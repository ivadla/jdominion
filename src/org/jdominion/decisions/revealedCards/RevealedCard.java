package org.jdominion.decisions.revealedCards;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;

public class RevealedCard {

	private Card revealedCard;
	private Player owner;
	private List<Option> options;
	private Option choosenOption;

	public RevealedCard(Card revealedCard, Player owner, List<Option> options) {
		super();
		assert !options.isEmpty();
		this.revealedCard = revealedCard;
		this.owner = owner;
		this.options = options;
		this.choosenOption = options.get(0);
	}

	public Card getRevealedCard() {
		return this.revealedCard;
	}

	public Player getOwner() {
		return owner;
	}

	public List<Option> getOptions() {
		return options;
	}

	public Option getChoosenOption() {
		return choosenOption;
	}

	public void setChoosenOption(Option choosenOption) {
		assert options.contains(choosenOption);
		this.choosenOption = choosenOption;
	}

}
