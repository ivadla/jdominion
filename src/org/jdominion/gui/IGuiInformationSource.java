package org.jdominion.gui;

import java.util.Collection;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Turn;
import org.jdominion.IPlayer;
import org.jdominion.Supply;
import org.jdominion.extraGameData.ExtraGameData;

public interface IGuiInformationSource {

	public Hand getHand();

	public List<IPlayer> getPlayers();

	public Supply getSupply();

	public Turn getCurrentTurn();
	
	public List<Card> getPlayArea();
	
	public Collection<ExtraGameData<?>> getExtraGameData();
}
