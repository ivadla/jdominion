package org.jdominion.remote;

import java.rmi.RemoteException;

import org.jdominion.TextOutput;

public class RemoteOutput extends TextOutput {

	private TextMessageServer textMessageServer;
	
	public RemoteOutput() {
		try {
			this.textMessageServer = new TextMessageServer();
			//Thread.sleep(10000);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
			//		} catch (InterruptedException e) {
//			new RuntimeException(e);
//		}
	}
	
	@Override
	protected void printMessage(String message) {
		textMessageServer.setMessage(message);
	}

}
