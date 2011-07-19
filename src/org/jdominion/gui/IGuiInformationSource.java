package org.jdominion.gui;

import java.util.Collection;
import java.util.List;

import org.jdominion.CardList;
import org.jdominion.Hand;
import org.jdominion.IPlayer;
import org.jdominion.Supply;
import org.jdominion.Trash;
import org.jdominion.Turn;
import org.jdominion.extraGameData.ExtraGameData;

public interface IGuiInformationSource {

	public Hand getHand();

	public List<IPlayer> getPlayers();

	public Supply getSupply();

	public Trash getTrash();

	public Turn getCurrentTurn();

	public CardList getPlayArea();

	public Collection<ExtraGameData<?>> getExtraGameData();
}
