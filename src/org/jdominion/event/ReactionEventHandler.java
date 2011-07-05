package org.jdominion.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jdominion.Card;
import org.jdominion.Player;
import org.jdominion.Card.Type;
import org.jdominion.decisions.ChooseReactionCardToUse;
import org.jdominion.effects.CardEffect;
import org.jdominion.effects.CardEffectReaction;
import org.jdominion.effects.NullEffect;
import org.jdominion.event.EventManager.Duration;

public class ReactionEventHandler implements IEventHandler {

	private static ReactionEventHandler instance = null;

	private Map<Player, List<Card>> playerCardMap = null;

	private ReactionEventHandler() {
		playerCardMap = new HashMap<Player, List<Card>>();
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
		if ((event instanceof CardPlayed) && ((CardPlayed) event).getPlayedCard().isOfType(Type.ATTACK)) {

			for (Entry<Player, List<Card>> playerCardMapEntry : playerCardMap.entrySet()) {
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
				Card reactionCard = decision.getAnswer().get(0);
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

	private List<Card> getCardList(Player player) {
		if (!playerCardMap.containsKey(player)) {
			playerCardMap.put(player, new ArrayList<Card>());
		}
		return playerCardMap.get(player);
	}

}
