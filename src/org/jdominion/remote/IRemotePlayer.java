package org.jdominion.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemotePlayer extends Remote {

	public String getRemoteName() throws RemoteException;

	public int getRemoteHandSize() throws RemoteException;

	public int getRemoteDeckSize() throws RemoteException;

	public int getRemoteDiscardPileSize() throws RemoteException;

}
