package org.jdominion.gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CardListView extends JScrollPane implements ComponentListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel = null;
	private List<CardImage> cardImages = new ArrayList<CardImage>();

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
		}
		return panel;
	}

	public CardListView() {
		super();
		this.setViewportView(getPanel());
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		this.addComponentListener(this);
	}

	public void displayCards(List<CardImage> cards) {
		cardImages = cards;

		getPanel().removeAll();
		for (CardImage card : cards) {
			getPanel().add(card);
		}
		getPanel().update(getGraphics());
		resizeCards();
	}

	public void addCard(CardImage card) {
		getPanel().add(card);
		resizeCard(card);
	}

	public void removeCard(CardImage cardToRemove) {
		getPanel().remove(cardToRemove);
		getPanel().repaint();
	}

	private void resizeCards() {
		for (CardImage cardImage : this.cardImages) {
			resizeCard(cardImage);
		}
	}

	private void resizeCard(CardImage cardImage) {
		cardImage.setHeight(this.getHeight() - this.getHorizontalScrollBar().getHeight() - 10);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		resizeCards();
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
}
