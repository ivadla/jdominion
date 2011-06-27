package org.jdominion.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.EventHandler;
import org.jdominion.EventObserver;
import org.jdominion.Game;
import org.jdominion.Hand;
import org.jdominion.IPlayer;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.aiStrategies.IStrategy;
import org.jdominion.decisions.ChooseCardFromSupply;
import org.jdominion.decisions.ChooseCardsFromHand;
import org.jdominion.decisions.Decision;
import org.jdominion.effects.CardEffect;

public class RemoteStrategy extends UnicastRemoteObject implements IStrategy, IRemoteDecision {

	public RemoteStrategy() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Decision<?> unansweredDecision = null;
	private Decision<?> answeredDecision = null;
	private Supply decisionSupply;
	private CardEffect decisionEffect;
	private Player player;
	private Game game;

	@Override
	public boolean canWorkWithSupply(Supply supply) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public synchronized void decide(Decision<?> decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {

		if (decision instanceof ChooseCardFromSupply || decision instanceof ChooseCardsFromHand) {
			assert this.answeredDecision == null && this.unansweredDecision == null;
			
			this.unansweredDecision = decision;
			this.decisionEffect = effect;
			this.decisionSupply = supply;
			notifyAll();

			while (this.answeredDecision == null) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// decision.setAnswer(((ChooseCardsFromHand)
			// answeredDecision).getAnswer());
			// ((decision.getClass()) decision).setAnswer(((decision.getClass())
			// answeredDecision).getAnswer());
			if(answeredDecision.isCanceled()) {
				decision.setCanceled(answeredDecision.isCanceled());
			} else {
				decision.getClass().cast(decision).setAnswer((decision.getClass().cast(answeredDecision)).getAnswer());
			}

			this.unansweredDecision = null;
			this.answeredDecision = null;
			this.notifyAll();
		} else {
			if (decision.isCancelable()) {
				decision.setCanceled(true);
			} else {
				decision.chooseDefaultAnswer(hand, currentTurn, supply);
			}
		}
	}

	// public synchronized void decide(ChooseCardFromSupply decision, CardEffect
	// effect, Hand hand, Turn currentTurn, Supply supply) {
	//
	// assert this.answeredDecision == null && this.unansweredDecision == null;
	//
	// this.unansweredDecision = decision;
	// notifyAll();
	//
	// while (this.answeredDecision == null) {
	// try {
	// this.wait();
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// decision.setAnswer(((ChooseCardFromSupply)
	// answeredDecision).getAnswer());
	//
	// this.answeredDecision = null;
	// this.unansweredDecision = null;
	// }

	@Override
	public void gameStarted(Game game) {
		this.game = game;
		RMIHandler.initializeRMI();
		try {

			// Bind this object instance to the name "RemotePlayerServer"
			Naming.rebind("//localhost/RemoteStrategyServer", this);

			System.out.println("PeerServer bound in registry");
		} catch (Exception e) {
			System.err.println("RMI server exception:");
			e.printStackTrace();
		}

		EventObserver observer = new RemoteOutput();
		EventHandler.getInstance().addObserver(observer);
	}

	@Override
	public String getName() {
		return "RemoteStrategy";
	}

	@Override
	public List<Class<? extends Card>> getNeededCards() {
		return new ArrayList<Class<? extends Card>>();
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public synchronized Decision<?> getDecision() throws RemoteException {
		while (this.unansweredDecision == null || this.answeredDecision != null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return this.unansweredDecision;
	}

	@Override
	public synchronized void setDecision(Decision<?> decision) throws RemoteException {
		this.answeredDecision = decision;
		notifyAll();
	}

	@Override
	public Hand getHand() throws RemoteException {
		return player.getHand();
	}

	@Override
	public List<IPlayer> getPlayers() throws RemoteException {
		List<IPlayer> playerStubs = new ArrayList<IPlayer>();
		for (Player player : game.getPlayers()) {
			playerStubs.add(player);
		}
		return playerStubs;
	}

	@Override
	public Supply getSupply() throws RemoteException {
		return game.getSupply();
	}

	@Override
	public Turn getCurrentTurn() throws RemoteException {
		return game.getCurrentTurn();
	}

	@Override
	public CardEffect getDecisionEffect() throws RemoteException {
		return decisionEffect;
	}

	@Override
	public Supply getDecisionSupply() throws RemoteException {
		return decisionSupply;
	}

}
