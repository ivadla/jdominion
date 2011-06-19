package org.jdominion.gui;

import java.util.List;

import org.jdominion.Game;
import org.jdominion.Hand;
import org.jdominion.IPlayer;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class GuiInformationSource implements IGuiInformationSource {

	public Game game;
	public Hand hand;
	public List<IPlayer> players;
	public Supply supply;

	public GuiInformationSource(Game game, Hand hand, List<IPlayer> players, Supply supply) {
		this.game = game;
		this.hand = hand;
		this.players = players;
		this.supply = supply;
	}

	@Override
	public Turn getCurrentTurn() {
		return game.getCurrentTurn();
	}

	@Override
	public Hand getHand() {
		return hand;
	}

	@Override
	public List<IPlayer> getPlayers() {
		return players;
	}

	@Override
	public Supply getSupply() {
		return supply;
	}

}
