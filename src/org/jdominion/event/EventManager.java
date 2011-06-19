package org.jdominion.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;

public class EventManager {

	private static EventManager instance = null;
	private Map<Class<? extends Event>, List<EventHandlerContainer>> eventHandlers;

	public enum Duration {
		ONCE, END_OF_TURN, FOREVER
	}

	private class EventHandlerContainer {
		public IEventHandler eventHandler;
		public EventManager.Duration duration;
		public Player player = null;

		public EventHandlerContainer(IEventHandler eventHandler, Duration duration) {
			this.eventHandler = eventHandler;
			this.duration = duration;
		}

		public EventHandlerContainer(IEventHandler eventHandler, Duration duration, Player player) {
			this(eventHandler, duration);
			this.player = player;
		}

	}

	private EventManager() {
		eventHandlers = new HashMap<Class<? extends Event>, List<EventHandlerContainer>>();
	}

	public static EventManager getInstance() {
		if (instance == null) {
			instance = new EventManager();
		}
		return instance;
	}

	public void addEventHandler(IEventHandler eventHandler, Class<? extends Event> eventType, Duration duration) {
		addEventHandler(eventHandler, eventType, duration, null);
	}

	/**
	 * Add an event handler which will only be called if the specified player
	 * triggers the event
	 * 
	 * @param eventHandler
	 * @param eventType
	 * @param duration
	 * @param player
	 */
	public void addEventHandler(IEventHandler eventHandler, Class<? extends Event> eventType, Duration duration,
			Player player) {
		if (!eventHandlers.containsKey(eventType)) {
			eventHandlers.put(eventType, new ArrayList<EventHandlerContainer>());
		}
		assert !eventHandlers.get(eventType).contains(eventHandler) : "EventHandler added twice";
		eventHandlers.get(eventType).add(new EventHandlerContainer(eventHandler, duration, player));
	}

	public void removeEventHandler(IEventHandler eventHandler, Class<? extends Event> eventType) {
		if (eventHandlers.containsKey(eventType)) {
			for (Iterator<EventHandlerContainer> iterator = eventHandlers.get(eventType).iterator(); iterator.hasNext();) {
				if (iterator.next().eventHandler == eventHandler) {
					iterator.remove();
				}
			}
		}
	}

	public void handleEvent(Event event, Player activePlayer, Turn currentTurn, Supply supply) {
		if (eventHandlers.containsKey(event.getClass())) {
			// use a copy of of the EventHandlerContainer-list to be able to
			// remove elements from the original list
			for (EventHandlerContainer eventHandlerContainer : new ArrayList<EventHandlerContainer>(eventHandlers
					.get(event.getClass()))) {
				if (eventHandlerContainer.player == activePlayer || eventHandlerContainer.player == null) {
					eventHandlerContainer.eventHandler.handleEvent(event, activePlayer, currentTurn, supply);
					if (eventHandlerContainer.duration == Duration.ONCE) {
						removeEventHandler(eventHandlerContainer.eventHandler, event.getClass());
					}
				}
			}
		}
		if (event instanceof EndOfTurn) {
			removeExpiredEventHandlers();
		}
	}

	private void removeExpiredEventHandlers() {
		for (List<EventHandlerContainer> containers : eventHandlers.values()) {
			for (Iterator<EventHandlerContainer> iterator = containers.iterator(); iterator.hasNext();) {
				if (iterator.next().duration == Duration.END_OF_TURN) {
					iterator.remove();
				}
			}
		}
	}

}
