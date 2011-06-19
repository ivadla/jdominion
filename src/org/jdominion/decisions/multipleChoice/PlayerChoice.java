package org.jdominion.decisions.multipleChoice;

import org.jdominion.Player;

public class PlayerChoice extends Choice {

	private Player player;

	public Player getPlayer() {
		return player;
	}

	public PlayerChoice(Player player) {
		this.player = player;
	}

	@Override
	public String getUserMessage() {
		return player.getName();
	}

}
