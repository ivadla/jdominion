package org.jdominion;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import org.jdominion.aiStrategies.IStrategy;
import org.jdominion.decisions.Decision;
import org.jdominion.effects.CardEffect;
import org.jdominion.event.CardBought;
import org.jdominion.event.CardGained;
import org.jdominion.event.CardPlayFinished;
import org.jdominion.event.CardPlayed;
import org.jdominion.event.CardsDiscarded;
import org.jdominion.event.CardsDrawn;
import org.jdominion.event.CardsRevealed;
import org.jdominion.event.CardsSetAside;
import org.jdominion.event.CardsTrashed;
import org.jdominion.event.EventManager;
import org.jdominion.event.GameEnded;
import org.jdominion.location.DiscardPile;
import org.jdominion.location.Location;

public class Player implements Serializable, IPlayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; // used by the Statistics-class to identify players over
					// several simulated games
	private String name;
	private Hand hand;
	private Game game;
	private Deque<Card> deck;
	private List<Card> discardPile;
	private List<Card> cardsSetAside;
	private int turnCounter = 0;

	private final IStrategy strategy;

	// used by SerializedPlayer
	protected Player(String name) {
		this.name = name;
		strategy = null;
	}

	public Player(String name, Deque<Card> deck, IStrategy strategy) {
		this.name = name;
		this.deck = deck;
		this.strategy = strategy;
		this.strategy.setPlayer(this);
		discardPile = new ArrayList<Card>();
		this.hand = new Hand();
		this.cardsSetAside = new ArrayList<Card>();
		drawNewHand();
	}

	public Player(int id, String name, Deque<Card> deck, IStrategy strategy) {
		this(name, deck, strategy);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public Hand getHand() {
		return hand;
	}

	@Override
	public int getHandSize() {
		return hand.size();
	}

	@Override
	public int getDeckSize() {
		return deck.size();
	}

	@Override
	public int getDiscardPileSize() {
		return discardPile.size();
	}

	public List<Card> getCardsSetAside() {
		return cardsSetAside;
	}

	public void incrementTurnCounter() {
		turnCounter++;
	}

	public int getTurnCounter() {
		return turnCounter;
	}

	public boolean hasActionCardInHand() {
		return hand.contains(Card.Type.ACTION);
	}

	public List<Card> getCardsFromDeck(int numberOfCardsToGet) {
		return drawCards(numberOfCardsToGet);
	}

	public Card revealCard() {
		List<Card> cardsToReveal = revealCards(1);
		if (cardsToReveal.size() == 1) {
			return cardsToReveal.get(0);
		}
		return null;
	}

	public List<Card> revealCards(int numberOfCardsToReveal) {
		List<Card> cardsToReveal = drawCards(numberOfCardsToReveal);
		EventManager.getInstance().handleEvent(new CardsRevealed(this, cardsToReveal));
		return cardsToReveal;
	}

	public void revealCardFromHand(Card card) {
		List<Card> cardList = new ArrayList<Card>();
		cardList.add(card);
		revealCardsFromHand(cardList);
	}

	public void revealCardsFromHand(List<Card> cardsToReveal) {
		for (Card card : cardsToReveal) {
			assert hand.contains(card);
		}
		EventManager.getInstance().handleEvent(new CardsRevealed(this, cardsToReveal));
	}

	public void revealHand() {
		EventManager.getInstance().handleEvent(new CardsRevealed(this, hand.getCardList()));
	}

	public void drawCardsIntoHand(int numberOfCardsToDraw) {
		List<Card> drawnCards = drawCards(numberOfCardsToDraw);
		addCardsToHand(drawnCards);
		EventManager.getInstance().handleEvent(new CardsDrawn(this, drawnCards.size()));
	}

	private List<Card> drawCards(int numberOfCardsToDraw) {
		List<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < numberOfCardsToDraw; i++) {
			Card card = drawCard();
			if (card != null) {
				cards.add(card);
			}
		}
		return cards;
	}

	private Card drawCard() {
		if (deck.size() == 0) {
			shuffleDeck();
		}
		return deck.pollFirst();
	}

	public void drawNewHand() {
		assert hand.size() == 0;
		drawCardsIntoHand(Game.NUMBER_OF_CARDS_IN_HAND);
	}

	public void addCardToHand(Card card) {
		List<Card> cards = new ArrayList<Card>();
		cards.add(card);
		addCardsToHand(cards);
	}

	// TODO: make an event for this
	public void addCardsToHand(List<Card> cards) {
		removeCardsFromOtherPlaces(cards);
		hand.addAll(cards);
		for (Card card : cards) {
			card.isAddedToHand(this);
		}
	}

	public void removeCardFromHand(Card card) {
		hand.remove(card);
		card.getsRemovedFromHand(this);
	}

	private void shuffleDeck() {

		Collections.shuffle(discardPile);
		deck = new ArrayDeque<Card>(discardPile);
		discardPile = new ArrayList<Card>();

	}

	public void discardCardsFromHand(List<Card> cardsToDiscard) {
		for (Card card : cardsToDiscard) {
			assert hand.contains(card);
			removeCardFromHand(card);
			placeOnDiscardPile(card);
		}
		EventManager.getInstance().handleEvent(new CardsDiscarded(this, cardsToDiscard));
	}

	public void placeOnDiscardPile(Card card) {
		discardPile.add(card);
		// List<Card> discardedCards = new ArrayList<Card>();
		// discardedCards.add(card);
		// getEventObserver().discardsCards(this, discardedCards);
	}

	public void placeOnDiscardPile(List<Card> cards) {
		discardPile.addAll(cards);
		// getEventObserver().discardsCards(this, cards);
	}

	public void placeOnDeck(Card card) {
		if (hand.contains(card)) {
			removeCardFromHand(card);
		}
		deck.push(card);
	}

	// TODO: use this in more places
	private void removeCardsFromOtherPlaces(List<Card> cards) {
		for (Card card : cards) {
			if (this.cardsSetAside.contains(card)) {
				this.cardsSetAside.remove(card);
			}
			if (this.hand.contains(card)) {
				this.removeCardFromHand(card);
			}
			if (this.discardPile.contains(card)) {
				this.discardPile.remove(card);
			}
			if (this.deck.contains(card)) {
				this.deck.removeFirstOccurrence(card);
			}
		}
	}

	public void trashCard(Card cardToTrash, Game game) {
		List<Card> cardList = new ArrayList<Card>();
		cardList.add(cardToTrash);
		trashCards(cardList, game);
	}

	public void trashCards(List<Card> cardsToTrash, Game game) {
		for (Card cardToTrash : cardsToTrash) {
			if (hand.contains(cardToTrash)) {
				removeCardFromHand(cardToTrash);
			}
			if (game.getCurrentTurn().getPlayedCards().contains(cardToTrash)) {
				game.getCurrentTurn().getPlayedCards().remove(cardToTrash);
			}
		}
		game.addCardsToTrash(cardsToTrash);
		EventManager.getInstance().handleEvent(new CardsTrashed(this, cardsToTrash));
	}

	public void playCard(Card card, Turn currentTurn, Supply supply) {
		if (hand.contains(card)) {
			removeCardFromHand(card);
		}
		EventManager.getInstance().handleEvent(new CardPlayed(this, card));
		card.play(this, currentTurn, supply);
		EventManager.getInstance().handleEvent(new CardPlayFinished(this, card));
	}

	public void buyCard(Class<? extends Card> cardToBuy, Turn currentTurn, Supply supply) {
		Card boughtCard = supply.takeCard(cardToBuy);
		EventManager.getInstance().handleEvent(new CardBought(this, boughtCard));
		this.gainCard(boughtCard);
	}

	public void gainCard(Class<? extends Card> card, Supply supply) {
		gainCard(card, supply, new DiscardPile());
	}

	public void gainCard(Class<? extends Card> card, Supply supply, Location whereToPlaceCard) {
		if (supply.isCardAvailable(card)) {
			gainCard(supply.takeCard(card), whereToPlaceCard);
		}
	}
	
	public void gainCard(Card gainedCard) {
		this.gainCard(gainedCard, new DiscardPile());
	}

	public void gainCard(Card gainedCard, Location whereToPlaceCard) {
		EventManager.getInstance().handleEvent(new CardGained(this, gainedCard));
		whereToPlaceCard.putCard(this, gainedCard);
	}

	public void setCardAside(Card card) {
		EventManager.getInstance().handleEvent(new CardsSetAside(this, Util.createCardList(card)));
		this.cardsSetAside.add(card);
		removeCardsFromOtherPlaces(Util.createCardList(card));
	}

	private List<Card> getListOfAllCards(Turn currentTurn) {
		List<Card> list = new ArrayList<Card>();
		list.addAll(deck);
		list.addAll(hand.getCardList());
		list.addAll(discardPile);
		list.addAll(cardsSetAside);
		if (currentTurn != null) {
			list.addAll(currentTurn.getPlayedCards());
		}
		return list;
	}

	public int countVictoryPoints(Turn currentTurn) {
		int points = 0;
		for (Card card : getListOfAllCards(currentTurn)) {
			points += card.getVictoryPoints(this, getListOfAllCards(currentTurn));
		}
		return points;
	}

	public int countCoins(Turn currentTurn) {
		int coins = 0;
		for (Card card : getListOfAllCards(currentTurn)) {
			coins += card.getCoins();
		}

		return coins;
	}

	public int countCards(Turn currentTurn) {
		return getListOfAllCards(currentTurn).size();
	}

	public int countCardsOfClass(Class<? extends Card> cardClass, Turn currentTurn) {
		int counter = 0;
		for (Card card : getListOfAllCards(currentTurn)) {
			if (card.getClass() == cardClass) {
				counter++;
			}
		}
		return counter;
	}

	public List<Class<? extends Card>> getNeededCards() {
		return strategy.getNeededCards();
	}

	public void gameStarted(Game game) {
		this.game = game;
		strategy.gameStarted(game);
	}

	public void gameEnded(List<Player> winners, List<Player> players) {
		EventManager.getInstance().handleEvent(new GameEnded(winners, players));
	}
	
	public void decide(Decision<?> decision, CardEffect effect) {
		callCorrectDecisionMethod(decision, effect, hand, game.getCurrentTurn(), game.getSupply(), strategy);

		// TODO: maybe move it somewhere else
		if (!decision.isAnswered()) {
			decision.chooseDefaultAnswer(hand, game.getCurrentTurn(), game.getSupply());
		}
	}

	public static void callCorrectDecisionMethod(Decision<?> decision, CardEffect effect, Hand hand, Turn currentTurn,
			Supply supply, IStrategy strategy) {
		// find the right decision method in the strategy to call
		// TODO: improve this code or find a better way to solve this problem
		Class<? extends Object> decisionClass = decision.getClass();
		while (decisionClass != Object.class) {
			Class<? extends Object> effectClass = effect.getClass();
			while (effectClass != Object.class) {
				try {
					Method decideMethode = strategy.getClass().getMethod("decide", decisionClass, effectClass,
							Hand.class, Turn.class, Supply.class);
					decideMethode.invoke(strategy, decision, effect, hand, currentTurn, supply);
					break;
				} catch (NoSuchMethodException e) {
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}
				effectClass = effectClass.getSuperclass();
			}
			decisionClass = decisionClass.getSuperclass();
		}
	}

	private Object writeReplace() throws ObjectStreamException {
		return new SerializedPlayer(name, getHandSize(), getDeckSize(), getDiscardPileSize());
	}

}
