package org.jdominion.gui;

import javax.swing.JComponent;
import javax.swing.JTextArea;

import org.jdominion.Turn;

public class TurnController {

	private IGuiInformationSource guiInformationSource;
	private JTextArea view;

	public JComponent getView() {
		return view;
	}

	public TurnController(IGuiInformationSource guiInformationSource) {
		this.guiInformationSource = guiInformationSource;
		this.view = new JTextArea();
		this.view.setEditable(false);
		this.update();
	}

	public void update() {
		Turn currentTurn = guiInformationSource.getCurrentTurn();
		if (currentTurn == null) {
			return;
		}
		String turnInfoText = "Turn " + currentTurn.getTurnNumber() + "\n";
		turnInfoText += "Active Player:\t" + currentTurn.getActivePlayer().getName() + "\n";
		turnInfoText += "Actions:\t" + currentTurn.getAvailableActions() + "\n";
		turnInfoText += "Buys:\t" + currentTurn.getAvailableBuys() + "\n";
		turnInfoText += "Coins:\t" + currentTurn.getExtraMoney();
		this.view.setText(turnInfoText);
	}
}
