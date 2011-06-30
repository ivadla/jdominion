package org.jdominion.decisions.multipleChoice;

import org.jdominion.effects.CardEffect;

public class EffectChoice extends Choice {

	private CardEffect effect;

	public CardEffect getEffect() {
		return effect;
	}

	public EffectChoice(CardEffect effect) {
		this.effect = effect;
	}

	@Override
	public String getUserMessage() {
		return effect.getText();
	}

}
