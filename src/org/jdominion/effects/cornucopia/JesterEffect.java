package org.jdominion.effects.cornucopia;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.cards.common.Curse;
import org.jdominion.decisions.cornucopia.ChoosePlayerToGetCard;
import org.jdominion.effects.CardEffectSimpleAttack;

public class JesterEffect extends CardEffectSimpleAttack {

	@Override
	protected void attackPlayer(Player playerToAttack, Turn currentTurn, Supply supply) {
		Card revealedCard = playerToAttack.revealCard();
		if(revealedCard == null) {
			return;
		}
		if (revealedCard.isOfType(Type.VICTORY)) {
			playerToAttack.gainCard(Curse.class, supply);
		} else if (supply.isCardAvailable(revealedCard.getClass())) {
			List<Player> players = new ArrayList<Player>();
			players.add(currentTurn.getActivePlayer());
			players.add(playerToAttack);
			ChoosePlayerToGetCard decision = new ChoosePlayerToGetCard(players, revealedCard, currentTurn
					.getActivePlayer());
			currentTurn.getActivePlayer().decide(decision, this, currentTurn.getActivePlayer().getHand(), currentTurn,
					supply);
			decision.getAnswer().get(0).getPlayer().gainCard(revealedCard.getClass(), supply);
		}

		playerToAttack.placeOnDiscardPile(revealedCard);
	}

}
