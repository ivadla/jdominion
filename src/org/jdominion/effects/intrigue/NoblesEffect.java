package org.jdominion.effects.intrigue;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.decisions.ChooseBetweenEffectsDecision;
import org.jdominion.decisions.intrigue.NoblesDecision;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.CardEffect;
import org.jdominion.effects.ChooseBetweenEffects;
import org.jdominion.effects.DrawCards;

public class NoblesEffect extends ChooseBetweenEffects {

	public NoblesEffect() {
		super(1, createEffectList());
	}

	private static List<CardEffect> createEffectList() {
		List<CardEffect> cardEffects = new ArrayList<CardEffect>();
		cardEffects.add(new DrawCards(3));
		cardEffects.add(new AddActions(2));
		return cardEffects;
	}

	@Override
	protected ChooseBetweenEffectsDecision createDecision(int numberOfChoices, List<CardEffect> effects) {
		return new NoblesDecision(numberOfChoices, effects);
	}
}
