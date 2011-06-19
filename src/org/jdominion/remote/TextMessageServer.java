package org.jdominion.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TextMessageServer extends UnicastRemoteObject implements ITextMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message = null;

	public TextMessageServer() throws RemoteException {
		try {

			// Bind this object instance to the name "TextMessageServer"
			Naming.rebind("//localhost/TextMessageServer", this);

			System.out.println("PeerServer bound in registry");
		} catch (Exception e) {
			System.err.println("RMI server exception:");
			e.printStackTrace();
		}

	}

	public synchronized void setMessage(String message) {
		while (this.message != null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		this.message = message;
		this.notifyAll();
	}

	@Override
	public synchronized String getMessage() {
		while (this.message == null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
			}
		}
		String message = this.message;
		this.message = null;
		this.notifyAll();
		return message;

	}

}
