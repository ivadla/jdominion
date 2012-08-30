package org.jdominion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supply implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CardPile> cardPiles;

	public Supply(List<CardPile> cardPiles) {
		this.cardPiles = cardPiles;
	}

	public List<CardPile> getCardPiles() {
		return this.cardPiles;
	}

	public void addCardPile(CardPile newCardPile) {
		if (!isCardAvailable(newCardPile.getCardClass())) {
			cardPiles.add(newCardPile);
		}
	}

	public CardPile getPile(Class<? extends Card> card) {
		for (CardPile pile : cardPiles) {
			if (pile.getCardClass() == card) {
				return pile;
			}
		}
		return null;
	}

	public boolean isCardAvailable(Class<? extends Card> card) {
		return getNumberOfAvailableCards(card) > 0;
	}

	public int getNumberOfAvailableCards(Class<? extends Card> card) {
		CardPile pile = getPile(card);
		if (pile == null) {
			return 0;
		} else {
			return pile.getNumberOfCardsInPile();
		}

	}

	public int getCardCost(Class<? extends Card> card) {
		assert isCardAvailable(card) : "Card " + card + " is not available";
		for (CardPile pile : cardPiles) {
			if (pile.getCardClass() == card) {
				return pile.getCardCost();
			}
		}

		return 0;
	}

	public Card takeCard(Class<? extends Card> card) {
		assert isCardAvailable(card) : "Card " + card + " is not available";
		for (CardPile pile : cardPiles) {
			if (pile.getCardClass() == card) {
				return pile.takeCard();
			}
		}
		return null;
	}

	public boolean putCard(Card card) {
		for (CardPile pile : cardPiles) {
			if (pile.getCardClass().equals(card.getClass())) {
				pile.putCard(card);
				return true;
			}
		}
		return false;
	}

	public Supply createSupplyWithMaximumCost(int maximumCost) {
		List<CardPile> newPiles = new ArrayList<CardPile>();
		for (CardPile pile : cardPiles) {
			if ((!pile.isEmpty()) && (pile.getCardCost() <= maximumCost)) {
				newPiles.add(pile);
			}
		}
		return new Supply(newPiles);
	}

	public int countEmptyPiles() {
		int emptyPiles = 0;
		for (CardPile pile : cardPiles) {
			if (pile.isEmpty()) {
				emptyPiles++;
			}
		}
		return emptyPiles;
	}

	public Class<? extends Card> findMostExpensiveCard() {
		int costOfMostExpensiveCard = -1;
		Class<? extends Card> mostExpensiveCard = null;
		for (CardPile pile : getCardPiles()) {
			if (!pile.isEmpty()) {
				if (pile.getCardCost() > costOfMostExpensiveCard) {
					costOfMostExpensiveCard = pile.getCardCost();
					mostExpensiveCard = pile.getCardClass();
				}
			}
		}
		return mostExpensiveCard;
	}

	public boolean isSubsetOf(Supply supply) {
		for (CardPile pile : getCardPiles()) {
			if (!supply.isCardAvailable(pile.getCardClass())) {
				return false;
			}
		}
		return true;
	}

}
