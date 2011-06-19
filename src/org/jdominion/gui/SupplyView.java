package org.jdominion.gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

public class SupplyView extends JPanel {

	private static final long serialVersionUID = 1L;

	public SupplyView() {
		super(new GridLayout(1, 0));
	}

	public void update(List<CardImage> availableCards) {
		this.removeAll();
		for (CardImage card : availableCards) {
			this.add(card);
		}
		
		// TODO: find out what is the best way to cause an actualization of the gui,
		// 		 because update(getGraphics()) doesnt always cause an update.
		//		 The error happens if the player has to choose a card to gain (e.g. from workshop, remodel, mine,...) and chooses a card which is too expansive.
		//		 In this case the player should choose again, but the supply is blank. updateUI() seems to work though.
		//this.update(getGraphics());
		this.updateUI();
	}

}
