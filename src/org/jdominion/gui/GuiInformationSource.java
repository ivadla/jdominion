package org.jdominion.gui;

import java.util.Collection;
import java.util.List;

import org.jdominion.CardList;
import org.jdominion.Game;
import org.jdominion.Hand;
import org.jdominion.IPlayer;
import org.jdominion.Supply;
import org.jdominion.Trash;
import org.jdominion.Turn;
import org.jdominion.extraGameData.ExtraGameData;

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
	public Trash getTrash() {
		return game.getTrash();
	}

	@Override
	public CardList getPlayArea() {
		return new CardList(getCurrentTurn().getPlayedCards());
	}

	@Override
	public Collection<ExtraGameData<?>> getExtraGameData() {
		return game.getAllExtraGameData();
	}

}
