package org.jdominion.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import org.jdominion.Hand;
import org.jdominion.IPlayer;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.Decision;
import org.jdominion.gui.HumanStrategy;
import org.jdominion.gui.IGuiInformationSource;
import org.jdominion.gui.MainWindow;

public class RemoteClient implements IGuiInformationSource {

	private IRemoteDecision server;
	private HumanStrategy decisionHandler;
	private MainWindow mainWindow;
	
	public RemoteClient() {
		try {
			server = (IRemoteDecision) Naming.lookup("//localhost/RemoteStrategyServer");
		} catch (Exception e) {
			System.err.println("RmiClient exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void run() throws RemoteException {
		mainWindow = new MainWindow(this);
		mainWindow.setVisible(true);
		new TextMessageThread(mainWindow).start();
		decisionHandler = new HumanStrategy(mainWindow);
		handleDecision();
	}
	
	private void handleDecision() throws RemoteException {
		while(true) {
			Decision decision = server.getDecision();
			mainWindow.update();
			Player.callCorrectDecisionMethod(decision, server.getDecisionEffect(), getHand(), getCurrentTurn(), server.getDecisionSupply(), decisionHandler);
			server.setDecision(decision);
		}
	}

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		// Create and install a security manager
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new RMISecurityManager());
//		}
		
		RemoteClient client = new RemoteClient();
		
		client.run();

	}

	@Override
	public Turn getCurrentTurn() {
		try {
			return server.getCurrentTurn();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Hand getHand() {
		try {
			return server.getHand();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IPlayer> getPlayers() {
		try {
			return server.getPlayers();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Supply getSupply() {
		try {
			return server.getSupply();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

}
