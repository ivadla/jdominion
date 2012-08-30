package org.jdominion.effects.darkAges;

import java.util.HashSet;
import java.util.Set;

import org.jdominion.Card;
import org.jdominion.Card.Type;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Trash;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectAction;

public class ForagerEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		currentTurn.addExtraMoney(countTreasureCards(currentTurn.getGame().getTrash()));
		return true;
	}

	private int countTreasureCards(Trash trash) {
		int extraMoney;
		Set<Class<? extends Card>> uniqueTreasureCards = new HashSet<Class<? extends Card>>();
		for (Card card : trash.getCardsOfType(Type.TREASURE)) {
			uniqueTreasureCards.add(card.getClass());
		}

		extraMoney = uniqueTreasureCards.size();
		return extraMoney;
	}

}
