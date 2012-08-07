package org.jdominion.effects.darkAges;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.decisions.ChooseBetweenEffectsDecision;
import org.jdominion.decisions.darkAges.GraverobberDecision;
import org.jdominion.effects.CardEffect;
import org.jdominion.effects.ChooseBetweenEffects;

public class GraverobberEffect extends ChooseBetweenEffects {

	public GraverobberEffect() {
		super(1, createEffectList());
	}

	private static List<CardEffect> createEffectList() {
		List<CardEffect> cardEffects = new ArrayList<CardEffect>();
		cardEffects.add(new GainCardFromTrash());
		cardEffects.add(new RemodelActionCard(3));
		return cardEffects;
	}
	@Override
	protected ChooseBetweenEffectsDecision createDecision(int numberOfChoices, List<CardEffect> effects) {
		return new GraverobberDecision(effects);
	}

}
