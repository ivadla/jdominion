package org.jdominion.gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class HandView extends JScrollPane implements ComponentListener {

	private static final long serialVersionUID = 1L;
	private JPanel panel = null;
	private List<CardImage> cardImages = null;

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
		}
		return panel;
	}

	public HandView() {
		super();
		this.setViewportView(getPanel());
		this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
		this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		this.addComponentListener(this);
	}

	public void displayHand(List<CardImage> cards) {
		cardImages = cards;

		getPanel().removeAll();
		for (CardImage card : cards) {
			getPanel().add(card);
		}
		getPanel().update(getGraphics());
		resizeCards();
	}

	private void resizeCards() {
		for (CardImage cardImage : this.cardImages) {
			cardImage.setHeight(this.getHeight() - this.getHorizontalScrollBar().getHeight() - 10);
		}
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
