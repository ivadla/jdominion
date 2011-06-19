package org.jdominion.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jdominion.Card;
import org.jdominion.decisions.ChooseFromRevealedCards;
import org.jdominion.decisions.revealedCards.Option;
import org.jdominion.decisions.revealedCards.RevealedCard;

public class RevealedCardsController implements MouseListener, ActionListener, WindowListener {

	private List<RevealedCard> revealedCards;
	private List<CardImage> cardImages;
	private ChooseFromRevealedCards decision;
	private boolean decisionFinished = false;
	private RevealedCardsView view;

	public RevealedCardsController(JFrame parent, String message, ChooseFromRevealedCards decision) {
		this.decision = decision;
		this.revealedCards = decision.getRevealedCards();

		assert revealedCards.size() > 0;

		view = new RevealedCardsView(parent, message, this);

		List<CardImage> cardImagesForOnePlayer = new ArrayList<CardImage>();
		String cardOwner = revealedCards.get(0).getOwner().getName();
		this.cardImages = new ArrayList<CardImage>();
		for (RevealedCard revealedCard : revealedCards) {
			if (revealedCard.getOwner().getName() != cardOwner) {
				view.addCardImages(cardOwner, cardImagesForOnePlayer);
				cardImagesForOnePlayer = new ArrayList<CardImage>();
				cardOwner = revealedCard.getOwner().getName();
			}
			CardImage cardImage = new CardImage(revealedCard.getRevealedCard());
			cardImage.setLongOverlayText(revealedCard.getChoosenOption().getName());
			cardImage.addMouseListener(this);
			cardImagesForOnePlayer.add(cardImage);
			this.cardImages.add(cardImage);
		}
		view.addCardImages(cardOwner, cardImagesForOnePlayer);
	}

	public synchronized void askUser() {

		view.setVisible();

		while (!this.decisionFinished) {
			try {
				this.wait();
			} catch (InterruptedException e) {

			}
		}
		this.view.dispose();
	}

	private void updateCardImages() {
		for (int i = 0; i < cardImages.size(); i++) {
			if (cardImages.get(i).getLongOverlayText() != revealedCards.get(i).getChoosenOption().getName()) {
				cardImages.get(i).setLongOverlayText(revealedCards.get(i).getChoosenOption().getName());
			}
		}
	}

	private RevealedCard getRevealedCardForCard(Card cardToSearch) {
		for (RevealedCard revealedCard : this.revealedCards) {
			if (revealedCard.getRevealedCard() == cardToSearch) {
				return revealedCard;
			}
		}
		throw new RuntimeException("Card " + cardToSearch.getName() + " not found in revealed Cards");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getButton() == MouseEvent.BUTTON1) && (e.getComponent() instanceof CardImage)) {
			CardImage clickedCardImage = (CardImage) e.getComponent();
			RevealedCard clickedRevealedCard = getRevealedCardForCard(clickedCardImage.getCard());
			decision.changeOption(clickedRevealedCard, findNextOption(clickedRevealedCard));
			updateCardImages();
		}

	}

	private Option findNextOption(RevealedCard revealedCard) {
		int indexOfNewOption = (revealedCard.getOptions().indexOf(revealedCard.getChoosenOption()) + 1)
				% revealedCard.getOptions().size();
		return revealedCard.getOptions().get(indexOfNewOption);
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

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "OK") {
			this.decisionFinished = true;
			this.notifyAll();
		}
	}

	@Override
	public synchronized void windowClosing(WindowEvent e) {
		this.decisionFinished = true;
		this.notifyAll();
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
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
	}

}
