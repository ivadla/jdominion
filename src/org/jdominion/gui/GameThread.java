package org.jdominion.gui;

import org.jdominion.Game;

public class GameThread extends Thread {

	private Game game;

	public GameThread(Game game) {
		this.game = game;
	}

	@Override
	public void run() {
		game.runGame();
	}

}
