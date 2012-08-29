package org.jdominion.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jdominion.Card;
import org.jdominion.Card.Type;
import org.jdominion.CardList;
import org.jdominion.Player;
import org.jdominion.decisions.ChooseReactionCardToUse;
import org.jdominion.effects.CardEffect;
import org.jdominion.effects.CardEffectReaction;
import org.jdominion.effects.NullEffect;
import org.jdominion.event.EventManager.Duration;

public class ReactionEventHandler implements IEventHandler {

	private static ReactionEventHandler instance = null;

	private Map<Player, CardList> playerCardMap = null;

	private ReactionEventHandler() {
		playerCardMap = new HashMap<Player, CardList>();
		EventManager.getInstance().addEventHandler(this, CardPlayed.class, Duration.FOREVER);
	}

	public static ReactionEventHandler getInstance() {
		if (instance == null) {
			instance = new ReactionEventHandler();
		}
		return instance;
	}

	@Override
	public void handleEvent(Event event) {
		if ((event instanceof CardPlayed) && ((CardPlayed) event).getCard().isOfType(Type.ATTACK)) {

			for (Entry<Player, CardList> playerCardMapEntry : playerCardMap.entrySet()) {
				// affectedPlayer is the player who plays the attack card
				if ((playerCardMapEntry.getKey() != event.getAffectedPlayer()) && (playerCardMapEntry.getValue().size() > 0)) {
					chooseReactionCardAndHandleReaction(playerCardMapEntry.getKey(), event);
				}
			}
		}
	}

	private void chooseReactionCardAndHandleReaction(Player attackedPlayer, Event event) {
		ChooseReactionCardToUse decision;
		do {
			decision = new ChooseReactionCardToUse(attackedPlayer.getHand(), event);
			attackedPlayer.decide(decision, new NullEffect());
			if (!decision.isCanceled()) {
				Card reactionCard = decision.getAnswer().getFirst();
				attackedPlayer.revealCardFromHand(reactionCard);
				for (CardEffect effect : reactionCard.getEffects()) {
					if (effect instanceof CardEffectReaction) {
						CardEffectReaction reactionEffect = (CardEffectReaction) effect;
						reactionEffect.handleReaction(event, attackedPlayer);
					}
				}
			}
		} while (!decision.isCanceled() && attackedPlayer.getHand().contains(Type.REACTION));
	}

	public void addReactionCard(Card reactionCard, Player cardOwner) {
		getCardList(cardOwner).add(reactionCard);
	}

	public void removeReactionCard(Card reactionCard, Player cardOwner) {
		getCardList(cardOwner).remove(reactionCard);
	}

	private CardList getCardList(Player player) {
		if (!playerCardMap.containsKey(player)) {
			playerCardMap.put(player, new CardList());
		}
		return playerCardMap.get(player);
	}

}
