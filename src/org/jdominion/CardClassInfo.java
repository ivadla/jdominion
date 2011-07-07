package org.jdominion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdominion.Card.Type;

public class CardClassInfo {

	private static CardClassInfo instance = null;

	public static CardClassInfo getInstance() {
		if (instance == null) {
			instance = new CardClassInfo();
		}
		return instance;
	}

	private Map<Class<? extends Card>, Card> cardClassToCardMap;

	private CardClassInfo() {
		cardClassToCardMap = new HashMap<Class<? extends Card>, Card>();
	}

	private Card getCard(Class<? extends Card> cardClass) {
		if (!cardClassToCardMap.containsKey(cardClass)) {
			try {
				cardClassToCardMap.put(cardClass, cardClass.newInstance());
			} catch (InstantiationException e) {
				throw new RuntimeException("Could not create a class of type " + cardClass.getName(), e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Could not create a class of type " + cardClass.getName(), e);
			}
		}
		return cardClassToCardMap.get(cardClass);
	}

	public int getCost(Class<? extends Card> cardClass) {
		return getCard(cardClass).getCost();
	}

	public String getName(Class<? extends Card> cardClass) {
		return getCard(cardClass).getName();
	}

	public List<Type> getTypes(Class<? extends Card> cardClass) {
		return getCard(cardClass).getTypes();
	}

	public boolean isOfType(Class<? extends Card> cardClass, Type type) {
		return getCard(cardClass).isOfType(type);
	}

	public boolean isKingdomCard(Class<? extends Card> cardClass) {
		return getCard(cardClass).isKingdomCard();
	}

	public boolean isTerminalAction(Class<? extends Card> cardClass) {
		return getCard(cardClass).isTerminalAction();
	}

}
