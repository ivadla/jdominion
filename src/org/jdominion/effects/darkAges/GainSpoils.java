package org.jdominion.effects.darkAges;

import org.jdominion.CardPile;
import org.jdominion.Game;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.effects.CardEffectAction;
import org.jdominion.extraGameData.SpoilsPile;

public class GainSpoils extends CardEffectAction {

	private int numberOfSpoilsToGain;

	public GainSpoils(int numberOfSpoilsToGain) {
		super();
		this.numberOfSpoilsToGain = numberOfSpoilsToGain;
	}
	
	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		CardPile spoilsPile = (CardPile) currentTurn.getGame().getExtraGameData(SpoilsPile.class).get();
		for (int i = 0; i < numberOfSpoilsToGain; i++) {
			if(spoilsPile.isEmpty()) {
				return false;
			}
			activePlayer.gainCard(spoilsPile.takeCard(), currentTurn, supply);
		}
		return true;
	}

	@Override
	public void gameStarted(Game game) {
		game.addExtraGameData(new SpoilsPile());
	}

}
