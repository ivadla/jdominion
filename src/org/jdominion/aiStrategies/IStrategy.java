package org.jdominion.aiStrategies;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.Game;
import org.jdominion.Hand;
import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.Decision;
import org.jdominion.effects.CardEffect;

public interface IStrategy {

	public void setPlayer(Player player);

	public String getName();
	
	public List<Class<? extends Card>> getNeededCards();
	
	public boolean canWorkWithSupply(Supply supply);

	public void gameStarted(Game game);

	public void decide(Decision<?> decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply);
}
