package org.jdominion.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.jdominion.Hand;
import org.jdominion.IPlayer;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.Decision;
import org.jdominion.effects.CardEffect;

public interface IRemoteDecision extends Remote {

	public Decision<?> getDecision() throws RemoteException;

	public Supply getDecisionSupply() throws RemoteException;

	public CardEffect getDecisionEffect() throws RemoteException;

	public void setDecision(Decision<?> decision) throws RemoteException;

	public Hand getHand() throws RemoteException;

	public List<IPlayer> getPlayers() throws RemoteException;

	public Supply getSupply() throws RemoteException;

	public Turn getCurrentTurn() throws RemoteException;

}
