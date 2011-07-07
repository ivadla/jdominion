package org.jdominion.remote;

import java.rmi.RemoteException;

import org.jdominion.IPlayer;

public class PlayerStub implements IPlayer {

	private IRemotePlayer remotePlayer;

	public PlayerStub(IRemotePlayer remotePlayer) {
		this.remotePlayer = remotePlayer;
	}

	@Override
	public int getDeckSize() {
		try {
			return remotePlayer.getRemoteDeckSize();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getDiscardPileSize() {
		try {
			return remotePlayer.getRemoteDiscardPileSize();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getHandSize() {
		try {
			return remotePlayer.getRemoteHandSize();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getName() {
		try {
			return remotePlayer.getRemoteName();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

}
