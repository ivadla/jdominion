package org.jdominion.effects;

import java.util.List;

import org.jdominion.Player;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseBetweenEffectsDecision;

public abstract class ChooseBetweenEffects extends CardEffectAction {

	private int numberOfChoices;
	private List<CardEffect> effects;

	/**
	 * @param effects
	 *            List of effects to choose from
	 * @param numberIfChoices
	 *            how many effects can be chosen
	 */
	public ChooseBetweenEffects(int numberIfChoices, List<CardEffect> effects) {
		this.effects = effects;
		this.numberOfChoices = numberIfChoices;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		ChooseBetweenEffectsDecision decision = createDecision(this.numberOfChoices, this.effects);
		activePlayer.decide(decision, this, activePlayer.getHand(), currentTurn, supply);
		List<CardEffect> chosenEffects = decision.getChosenEffects();

		// execute chosen effects, but use the order of this.effects
		for (CardEffect effect : this.effects) {
			if (chosenEffects.contains(effect)) {
				effect.execute(activePlayer, currentTurn, supply);
			}
		}
		return true;
	}

	protected abstract ChooseBetweenEffectsDecision createDecision(int numberIfChoices, List<CardEffect> effects);

}
