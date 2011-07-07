package org.jdominion.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITextMessage extends Remote {

	public String getMessage() throws RemoteException;
}
