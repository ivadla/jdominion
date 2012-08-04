package org.jdominion.effects.cornucopia;

import java.util.ArrayList;

import org.jdominion.Card;
import org.jdominion.CardList;
import org.jdominion.CardPile;
import org.jdominion.Game;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Duchy;
import org.jdominion.cards.common.Province;
import org.jdominion.cards.cornucopia.BagOfGold;
import org.jdominion.cards.cornucopia.Diadem;
import org.jdominion.cards.cornucopia.Followers;
import org.jdominion.cards.cornucopia.Princess;
import org.jdominion.cards.cornucopia.TrustySteed;
import org.jdominion.decisions.ChooseCardToGain;
import org.jdominion.decisions.cornucopia.RevealProvince;
import org.jdominion.effects.CardEffectAction;
import org.jdominion.extraGameData.Prizes;
import org.jdominion.location.TopOfDeck;

public class TournamentEffect extends CardEffectAction {

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		boolean otherPlayerRevealedProvince = false;
		boolean activePlayerRevealedProvince = false;

		if (revealsProvince(activePlayer, currentTurn, supply)) {
			activePlayerRevealedProvince = true;
		}

		for (Player otherPlayer : currentTurn.getOtherPlayers()) {
			if (revealsProvince(otherPlayer, currentTurn, supply)) {
				otherPlayerRevealedProvince = true;
			}
		}

		if (activePlayerRevealedProvince && ((getAvailablePrizes(currentTurn.getGame()).size() > 0) || supply.isCardAvailable(Duchy.class))) {

			discardProvince(activePlayer);

			Card choosenCard = choosePrizeOrDuchy(activePlayer, currentTurn, supply);
			if (!(choosenCard instanceof Duchy)) {
				getAvailablePrizes(currentTurn.getGame()).remove(choosenCard);
			}

			activePlayer.gainCard(choosenCard, new TopOfDeck(), currentTurn, supply);

		}

		if (!otherPlayerRevealedProvince) {
			activePlayer.drawCardsIntoHand(1);
			currentTurn.addExtraMoney(1);
		}

		return true;
	}

	private Card choosePrizeOrDuchy(Player activePlayer, Turn currentTurn, Supply supply) {
		ArrayList<CardPile> availablePrizesAndDuchy = new ArrayList<CardPile>();

		boolean chancelable = false;

		if (supply.isCardAvailable(Duchy.class)) {
			availablePrizesAndDuchy.add(supply.getPile(Duchy.class));
		} else {
			chancelable = true;
		}
		if (getAvailablePrizes(currentTurn.getGame()).size() > 0) {
			for (Card prize : getAvailablePrizes(currentTurn.getGame())) {
				availablePrizesAndDuchy.add(new CardPile(new CardList(prize)));
			}
		} else {
			chancelable = true;
		}

		Supply prizesAndDuchySupply = new Supply(availablePrizesAndDuchy);
		ChooseCardToGain decision = new ChooseCardToGain(prizesAndDuchySupply, chancelable);
		activePlayer.decide(decision, this);

		return prizesAndDuchySupply.takeCard((Class<? extends Card>) decision.getAnswer());
	}

	private void discardProvince(Player activePlayer) {
		activePlayer.discardCardsFromHand(new CardList(activePlayer.getHand().getCardByClass(Province.class)));
	}

	@SuppressWarnings("unchecked")
	private CardList getAvailablePrizes(Game game) {
		return (CardList) game.getExtraGameData(Prizes.class).get();
	}

	@Override
	public void gameStarted(Game game) {
		Prizes prizes = new Prizes();
		prizes.add(new CardList(new Card[] { new BagOfGold(), new Diadem(), new Followers(), new Princess(), new TrustySteed() }));
		game.addExtraGameData(prizes);

	}

	private boolean revealsProvince(Player player, Turn currentTurn, Supply supply) {
		if (player.getHand().contains(Province.class)) {
			RevealProvince decision = new RevealProvince();
			player.decide(decision, this);
			if (decision.getAnswer()) {
				player.revealCardFromHand(player.getHand().getCardByClass(Province.class));
				return true;
			}
		}
		return false;
	}

}
