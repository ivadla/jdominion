package org.jdominion.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import org.jdominion.Card;

public abstract class CardListController implements MouseListener, ActionListener {

	private Card choosenCard;
	private Boolean cancelClicked;
	private CardListView view;
	private List<Card> currentlyDisplayedCards;

	public CardListView view() {
		return view;
	}

	public CardListController() {
		this.view = new CardListView();
	}

	public void update() {
		List<Card> newCards = getCardList();
		if(cardListChanged(newCards)) {
			List<CardImage> cardImages = new ArrayList<CardImage>();
			for (Card card : newCards) {
				CardImage cardImage = new CardImage(card);
				cardImage.addMouseListener(this);
				cardImages.add(cardImage);
			}
			view().displayCards(cardImages);
			//save a copy of the list for later reference
			currentlyDisplayedCards = new ArrayList<Card>(newCards);
		}
	}

	private boolean cardListChanged(List<Card> newCards) {
		if (newCards == null || currentlyDisplayedCards == null) {
			return true;
		}
		return !(newCards.containsAll(currentlyDisplayedCards) && currentlyDisplayedCards.containsAll(newCards));
	}
	
	protected abstract List<Card> getCardList();

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
