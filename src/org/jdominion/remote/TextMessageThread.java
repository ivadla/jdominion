package org.jdominion.remote;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import org.jdominion.gui.MainWindow;

public class TextMessageThread extends Thread {

	private ITextMessage server = null;
	private MainWindow mainWindow;

	public TextMessageThread() {
		try {
			server = (ITextMessage) Naming.lookup("//localhost/TextMessageServer");
		} catch (Exception e) {
			System.err.println("RmiClient exception: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public TextMessageThread(MainWindow mainWindow) {
		this();
		this.mainWindow = mainWindow;
	}

	public void printMessages() throws InterruptedException, RemoteException {
		if (server != null) {
			while (true) {
				String message = server.getMessage();
				if (message != null) {
					mainWindow.addMessageText(message);
					mainWindow.update();
				}
			}
		}
	}

	@Override
	public void run() {
		try {
			printMessages();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException, RemoteException {
		// Create and install a security manager
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		TextMessageThread client = new TextMessageThread();
		client.printMessages();

	}

}
