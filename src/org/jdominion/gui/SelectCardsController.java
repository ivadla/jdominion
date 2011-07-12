package org.jdominion.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.ClassFinder;
import org.jdominion.supplyCreators.SupplyCreator;

public class SelectCardsController implements WindowListener {

	private SupplyCreator supplycreator;
	private CardList cardList = null;
	private CardList selectedCards = null;
	private SelectCardsView view;

	public SelectCardsController(JFrame parent, SupplyCreator supplycreator) {
		this.supplycreator = supplycreator;
		view = new SelectCardsView(parent, this, "Select cards",
				"Choose up to 10 cards which should be included in the game. The rest will be choosen randomly.", getCardList(), getSelectedCards());
	}

	private CardList getCardList() {
		if (cardList == null) {
			cardList = new CardList();
			for (Class<? extends Card> cardClass : ClassFinder.findAllKingdomCards()) {
				try {
					cardList.add(cardClass.newInstance());
				} catch (InstantiationException e) {
					throw new RuntimeException("Could not create Card of class " + cardClass.getName() + " for list", e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Could not create Card of class " + cardClass.getName() + " for list", e);
				}
			}
			cardList.sortCardsByName();
		}
		return cardList;
	}

	private CardList getSelectedCards() {
		if (selectedCards == null) {
			selectedCards = new CardList();
			for (Card card : getCardList()) {
				if (supplycreator.getSelectedCards().contains(card.getClass())) {
					selectedCards.add(card);
				}
			}
		}
		return selectedCards;
	}

	public void cardChangedState(Card card, boolean state) {
		if (state) {
			getSelectedCards().add(card);
		} else {
			getSelectedCards().remove(card);
		}
		enOrDisableUncheckedCheckboxes();
	}

	private void enOrDisableUncheckedCheckboxes() {
		if (getSelectedCards().size() >= 10) {
			view.enOrDisableUncheckedCheckboxes(false);
		} else {
			view.enOrDisableUncheckedCheckboxes(true);
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		supplycreator.setSelectedCards(createCardClassList(selectedCards));
		view.dispose();
	}

	private List<Class<? extends Card>> createCardClassList(CardList cards) {
		List<Class<? extends Card>> cardClasses = new ArrayList<Class<? extends Card>>();
		for (Card card : cards) {
			cardClasses.add(card.getClass());
		}
		return cardClasses;
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
		enOrDisableUncheckedCheckboxes();
	}
}
