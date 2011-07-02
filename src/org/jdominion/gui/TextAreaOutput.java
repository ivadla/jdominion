package org.jdominion.gui;

import org.jdominion.TextOutput;

public class TextAreaOutput extends TextOutput {

	private MainWindow mainWindow;

	public TextAreaOutput(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	protected void printMessage(String message) {
		mainWindow.addMessageText(message);
		mainWindow.update();
	}

}
