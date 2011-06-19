package org.jdominion.gui;

import java.util.List;

import org.jdominion.Hand;
import org.jdominion.Turn;
import org.jdominion.IPlayer;
import org.jdominion.Supply;

public interface IGuiInformationSource {

	public Hand getHand();

	public List<IPlayer> getPlayers();

	public Supply getSupply();

	public Turn getCurrentTurn();
}
