package org.jdominion.darkAges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.CardFactory;
import org.jdominion.CardList;
import org.jdominion.CardPile;
import org.jdominion.Game;
import org.jdominion.cards.darkAges.ruins.AbandonedMine;
import org.jdominion.cards.darkAges.ruins.RuinedLibrary;
import org.jdominion.cards.darkAges.ruins.RuinedMarket;
import org.jdominion.cards.darkAges.ruins.RuinedVillage;

public class RuinsPile extends CardPile {

	public RuinsPile(Game game) {
		super(createRuinsPile(game));
	}

	@Override
	public Class<? extends Card> getCardClass() {
		if (isEmpty()) {
			return super.getCardClass();
		} else {
			return cards.getFirst().getClass();
		}
	}

	@Override
	public String getCardName() {
		if (isEmpty()) {
			return super.getCardName();
		} else {
			return cards.getFirst().getName();
		}
	}

	private static CardList createRuinsPile(Game game) {
		int numberOfRuins = CardFactory.getNumberOfCurseCards(game.getPlayers().size());
		List<Card> ruins = new ArrayList<Card>();
		createRuins(ruins, AbandonedMine.class);
		createRuins(ruins, RuinedLibrary.class);
		createRuins(ruins, RuinedMarket.class);
		createRuins(ruins, RuinedVillage.class);

		// TODO:
		// createRuins(ruins, Survivors.class);

		Collections.shuffle(ruins);

		return new CardList(ruins.subList(0, numberOfRuins));
	}

	private static void createRuins(List<Card> ruins, Class<? extends Card> ruinClass) {
		for (int i = 0; i < 10; i++) {
			try {
				ruins.add(ruinClass.newInstance());
			} catch (InstantiationException e) {
				assert false : "Error while creating ruions pile";
				throw new RuntimeException("Error while creating ruions pile", e);
			} catch (IllegalAccessException e) {
				assert false : "Error while creating ruions pile";
				throw new RuntimeException("Error while creating ruions pile", e);
			}
		}
	}
}
