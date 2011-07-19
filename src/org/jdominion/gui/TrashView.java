package org.jdominion.gui;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import net.miginfocom.swing.MigLayout;

public class TrashView extends JPanel {

	private JPanel fullView;
	private List<CardImage> cardsInTrash;
	private Popup fullViewPopup = null;

	public TrashView(MouseListener listener) {
		cardsInTrash = new ArrayList<CardImage>();
		fullView = new JPanel();
		fullView.addMouseListener(listener);
		fullView.setLayout(new MigLayout());
		this.add(new JLabel("empty"));
		this.setLayout(new MigLayout());
		this.setToolTipText("Click to view all");
	}

	public void setImageForSmallView(CardImage image) {
		this.removeAll();
		this.add(image);
		image.setToolTipText("Click to view all");
	}

	/**
	 * removes all CardImages from the full view
	 */
	public void resetFullView() {
		fullView.removeAll();
		cardsInTrash.clear();
	}

	public void addCardImageToFullView(CardImage image) {
		cardsInTrash.add(image);
		image.setHeight(this.getHeight());
		fullView.add(image);
		fullView.setSize(cardsInTrash.size() * image.getWidth(), image.getHeight());
	}

	public void showFullView() {
		fullViewPopup = PopupFactory.getSharedInstance().getPopup(this, fullView, (int) getLocationOnScreen().getX(), (int) getLocationOnScreen().getY());
		fullViewPopup.show();
	}

	public void hideFullView() {
		if (fullViewPopup != null) {
			fullViewPopup.hide();
			fullViewPopup = null;
		}
	}

}
