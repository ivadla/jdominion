package org.jdominion.gui;

import org.jdominion.event.CardBought;
import org.jdominion.event.CardDiscarded;
import org.jdominion.event.CardGained;
import org.jdominion.event.CardPlayFinished;
import org.jdominion.event.CardPlayed;
import org.jdominion.event.CardTrashed;
import org.jdominion.event.CardsDrawn;
import org.jdominion.event.CardsRevealed;
import org.jdominion.event.CardsSetAside;
import org.jdominion.event.Event;
import org.jdominion.event.EventManager;
import org.jdominion.event.EventManager.Duration;
import org.jdominion.event.GameEnded;
import org.jdominion.event.IEventHandler;

public class GUIEventHandler implements IEventHandler {

	private MainWindow mainWindow;

	public GUIEventHandler(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public void registerForEvents(EventManager eventManager) {
		eventManager.addEventHandler(this, CardBought.class, Duration.FOREVER);
		eventManager.addEventHandler(this, CardDiscarded.class, Duration.FOREVER);
		eventManager.addEventHandler(this, CardsDrawn.class, Duration.FOREVER);
		eventManager.addEventHandler(this, CardGained.class, Duration.FOREVER);
		eventManager.addEventHandler(this, CardPlayed.class, Duration.FOREVER);
		eventManager.addEventHandler(this, CardPlayFinished.class, Duration.FOREVER);
		eventManager.addEventHandler(this, CardTrashed.class, Duration.FOREVER);
		eventManager.addEventHandler(this, CardsRevealed.class, Duration.FOREVER);
		eventManager.addEventHandler(this, CardsSetAside.class, Duration.FOREVER);
		eventManager.addEventHandler(this, GameEnded.class, Duration.ONCE);
	}

	@Override
	public void handleEvent(Event event) {
		printMessage(event.getDescription());
	}

	protected void printMessage(String message) {
		mainWindow.addMessageText(message);
		mainWindow.update();
	}
}
