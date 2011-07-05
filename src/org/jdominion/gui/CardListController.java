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

	public CardListView view() {
		return view;
	}

	public CardListController() {
		this.view = new CardListView();
//		this.update();
	}

	public void update() {
		List<CardImage> cardImages = new ArrayList<CardImage>();
		for (Card card : getCardList()) {
			CardImage cardImage = new CardImage(card);
			cardImage.addMouseListener(this);
			cardImages.add(cardImage);
		}
		view().displayCards(cardImages);
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
