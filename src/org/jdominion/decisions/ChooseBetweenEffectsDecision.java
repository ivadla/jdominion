package org.jdominion.decisions;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.decisions.multipleChoice.EffectChoice;
import org.jdominion.effects.CardEffect;

public abstract class ChooseBetweenEffectsDecision extends MultipleChoice<EffectChoice> {

	public ChooseBetweenEffectsDecision(int maxAnswers, List<CardEffect> choices) {
		super("Choose " + maxAnswers + ":", false, maxAnswers, maxAnswers, createChoicesList(choices));
	}

	private static List<EffectChoice> createChoicesList(List<CardEffect> cardEffects) {
		List<EffectChoice> choices = new ArrayList<EffectChoice>();
		for (CardEffect effect : cardEffects) {
			choices.add(new EffectChoice(effect));
		}
		return choices;
	}

	public List<CardEffect> getChosenEffects() {
		List<CardEffect> chosenEffects = new ArrayList<CardEffect>();
		for (EffectChoice answeredChoice : getAnswer()) {
			chosenEffects.add(answeredChoice.getEffect());
		}
		return chosenEffects;
	}

	protected EffectChoice getChoice(Class<? extends CardEffect> type) {
		for (EffectChoice choice : getChoices()) {
			if (choice.getEffect().getClass() == type) {
				return choice;
			}
		}
		assert false;
		return null;
	}

}
