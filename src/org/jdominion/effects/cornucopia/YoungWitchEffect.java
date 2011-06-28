package org.jdominion.effects.cornucopia;

import java.util.Collections;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.CardFactory;
import org.jdominion.ClassFinder;
import org.jdominion.Game;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Curse;
import org.jdominion.decisions.cornucopia.RevealBaneCard;
import org.jdominion.effects.CardEffectSimpleAttack;

public class YoungWitchEffect extends CardEffectSimpleAttack {

	private static Class<? extends Card> baneCard;

	/**
	 * AI strategies can use this method to find out which card is the bane card
	 * 
	 * @return the class of the bane card
	 */
	public static Class<? extends Card> getBaneCard() {
		return baneCard;
	}

	@Override
	protected void attackPlayer(Player playerToAttack, Turn currentTurn, Supply supply) {
		if (!supply.isCardAvailable(Curse.class)) {
			return;
		}
		if (playerToAttack.getHand().contains(baneCard)) {
			RevealBaneCard decision = new RevealBaneCard(playerToAttack.getHand().getCardByClass(baneCard));
			playerToAttack.decide(decision, this, playerToAttack.getHand(), currentTurn, supply);
			if (decision.getAnswer()) {
				return;
			}
		}
		playerToAttack.gainCard(Curse.class, supply);
	}

	@Override
	public void gameStarted(Game game) {
		baneCard = chooseBaneCard(game);
		game.getSupply().addCardPile(CardFactory.createCardPile(baneCard, game.getPlayers().size()));
	}

	private Class<? extends Card> chooseBaneCard(Game game) {
		List<Class<? extends Card>> kingdomCards = ClassFinder.findAllKingdomCards();
		Collections.shuffle(kingdomCards);
		for (Class<? extends Card> cardClass : kingdomCards) {
			if (!game.getSupply().isCardAvailable(cardClass) && cardCosts2or3(cardClass)) {
				return cardClass;
			}
		}
		assert false : "no bane card found";
		return null;
	}

	private boolean cardCosts2or3(Class<? extends Card> cardClass) {
		try {
			Card card = cardClass.newInstance();
			if (card.getCost() == 2 || card.getCost() == 3) {
				return true;
			}
		} catch (InstantiationException e) {
			throw new RuntimeException("Error while creating a bane card", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Error while creating a bane card", e);
		}
		return false;
	}

}
