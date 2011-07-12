package org.jdominion.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jdominion.Card;
import org.jdominion.CardList;

public abstract class CardListController implements MouseListener, ActionListener {

	private Card choosenCard;
	private Boolean cancelClicked;
	private CardListView view;
	private Map<Card, CardImage> currentlyDisplayedCards;

	public CardListView view() {
		return view;
	}

	public CardListController() {
		this.view = new CardListView();
		currentlyDisplayedCards = new HashMap<Card, CardImage>();
	}

	public void update() {
		CardList newCards = getCardList();

		if (newCards.disjoint(currentlyDisplayedCards.keySet())) {
			replaceCardsInView(newCards);
		} else {
			removeOldCards(newCards);
			addNewCards(newCards);
		}
	}

	/**
	 * add the cards from newCards which are not already in the view
	 * 
	 * @param newCards
	 */
	private void addNewCards(CardList newCards) {
		for (Card newCard : newCards) {
			if (!currentlyDisplayedCards.containsKey(newCard)) {
				addCardToView(newCard);
			}
		}
	}

	/**
	 * removes the cards from the view which are not in the List newCards
	 * 
	 * @param newCards
	 */
	private void removeOldCards(CardList newCards) {
		Entry<Card, CardImage> entry;
		for (Iterator<Entry<Card, CardImage>> iterator = currentlyDisplayedCards.entrySet().iterator(); iterator.hasNext();) {
			entry = iterator.next();
			if (!newCards.contains(entry.getKey())) {
				view.removeCard(entry.getValue());
				iterator.remove();
			}
		}
	}

	/**
	 * removes all cards from the view and replaces them with the newCards
	 * 
	 * @param newCards
	 */
	private void replaceCardsInView(CardList newCards) {
		currentlyDisplayedCards = new HashMap<Card, CardImage>();
		List<CardImage> newCardimages = new ArrayList<CardImage>();
		for (Card card : newCards) {
			CardImage cardImage = createCardImage(card);
			currentlyDisplayedCards.put(card, cardImage);
			newCardimages.add(cardImage);
		}
		view.displayCards(newCardimages);
	}

	private void addCardToView(Card newCard) {
		CardImage cardImage = createCardImage(newCard);
		view.addCard(cardImage);
		currentlyDisplayedCards.put(newCard, cardImage);
	}

	private CardImage createCardImage(Card card) {
		CardImage cardImage = new CardImage(card);
		cardImage.addMouseListener(this);
		return cardImage;
	}

	protected abstract CardList getCardList();

	public synchronized Card chooseCard() {
		this.choosenCard = null;
		this.cancelClicked = false;
		while ((this.choosenCard == null) && (!cancelClicked)) {
			try {
				this.wait();
			} catch (InterruptedException e) {

			}
		}
		return this.choosenCard;
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		this.cancelClicked = true;
		this.notifyAll();
	}

	@Override
	public synchronized void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.choosenCard = ((CardImage) e.getComponent()).getCard();
			this.notifyAll();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
