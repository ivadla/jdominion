package org.jdominion.decisions.cornucopia;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.MultipleChoice;
import org.jdominion.decisions.multipleChoice.PlayerChoice;

public class ChoosePlayerToGetCard extends MultipleChoice<PlayerChoice> {

	private Card card;
	private Player playerWhoCanChoose;

	public Card getCard() {
		return card;
	}

	public ChoosePlayerToGetCard(List<Player> players, Card card, Player playerWhoCanChoose) {
		super("Choose a player who should get a " + card.getName(), false, 1, 1, createChoices(players));
		this.card = card;
		this.playerWhoCanChoose = playerWhoCanChoose;
	}

	private static List<PlayerChoice> createChoices(List<Player> players) {
		List<PlayerChoice> choices = new ArrayList<PlayerChoice>();
		for (Player player : players) {
			choices.add(new PlayerChoice(player));
		}
		return choices;
	}

	public Player getChoosenPlayer() {
		if (getAnswer() == null) {
			return null;
		} else {
			return getAnswer().get(0).getPlayer();
		}
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		List<PlayerChoice> answer = new ArrayList<PlayerChoice>();
		for (PlayerChoice choice : getChoices()) {
			if (choice.getPlayer() == playerWhoCanChoose && card.getCost() > 0) {
				answer.add(choice);
				setAnswer(answer);
				return;
			} else if (choice.getPlayer() != playerWhoCanChoose && card.getCost() == 0) {
				answer.add(choice);
				setAnswer(answer);
				return;
			}
		}
		throw new RuntimeException("No answer found!");
	}

}
