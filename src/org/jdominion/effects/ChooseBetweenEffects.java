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
	 * @param numberOfChoices
	 *            how many effects can be chosen
	 */
	public ChooseBetweenEffects(int numberOfChoices, List<CardEffect> effects) {
		this.effects = effects;
		this.numberOfChoices = numberOfChoices;
	}

	@Override
	public boolean execute(Player activePlayer, Turn currentTurn, Supply supply) {
		ChooseBetweenEffectsDecision decision = createDecision(this.numberOfChoices, this.effects);
		activePlayer.decide(decision, this);
		List<CardEffect> chosenEffects = decision.getChosenEffects();

		// execute chosen effects, but use the order of this.effects
		for (CardEffect effect : this.effects) {
			if (chosenEffects.contains(effect)) {
				effect.execute(activePlayer, currentTurn, supply);
			}
		}
		return true;
	}

	protected abstract ChooseBetweenEffectsDecision createDecision(int numberOfChoices, List<CardEffect> effects);

}
