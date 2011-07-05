package org.jdominion.gui;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Game;
import org.jdominion.Hand;
import org.jdominion.IPlayer;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class GuiInformationSource implements IGuiInformationSource {

	private Game game;
	private Hand hand;
	private List<IPlayer> players;
	private Supply supply;

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

	@Override
	public List<Card> getPlayArea() {
		return new ArrayList<Card>(getCurrentTurn().getPlayedCards());
	}

}
