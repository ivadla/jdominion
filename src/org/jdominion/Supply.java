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

	public boolean isCardAvailable(Class<? extends Card> card) {
		for (CardPile pile : cardPiles) {
			if (pile.getCardClass() == card) {
				if (pile.getNumberOfCardsInPile() > 0) {
					return true;
				}
			}
		}
		return false;
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

	public Supply createSupplyWithMaximumCost(int maximumCost) {
		List<CardPile> newPiles = new ArrayList<CardPile>();
		for (CardPile pile : cardPiles) {
			if ((pile.getNumberOfCardsInPile() > 0) && (pile.getCardCost() <= maximumCost)) {
				newPiles.add(pile);
			}
		}
		return new Supply(newPiles);
	}

	public int countEmptyPiles() {
		int emptyPiles = 0;
		for (CardPile pile : cardPiles) {
			if (pile.getNumberOfCardsInPile() == 0) {
				emptyPiles++;
			}
		}
		return emptyPiles;
	}

	public Class<? extends Card> findMostExpensiveCard() {
		int costOfMostExpensiveCard = -1;
		Class<? extends Card> mostExpensiveCard = null;
		for (CardPile pile : getCardPiles()) {
			if (pile.getCardCost() > costOfMostExpensiveCard) {
				costOfMostExpensiveCard = pile.getCardCost();
				mostExpensiveCard = pile.getCardClass();
			}
		}
		return mostExpensiveCard;
	}

}
