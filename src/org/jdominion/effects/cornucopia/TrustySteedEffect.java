package org.jdominion.effects.cornucopia;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.decisions.ChooseBetweenEffectsDecision;
import org.jdominion.decisions.cornucopia.TrustySteedDecision;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.CardEffect;
import org.jdominion.effects.ChooseBetweenEffects;
import org.jdominion.effects.DrawCards;

public class TrustySteedEffect extends ChooseBetweenEffects {

	public TrustySteedEffect() {
		super(2, createEffects());
	}

	private static List<CardEffect> createEffects() {
		List<CardEffect> effects = new ArrayList<CardEffect>();
		effects.add(new AddActions(2));
		effects.add(new DrawCards(2));
		effects.add(new AddExtraMoney(2));
		effects.add(new TrustySteedGainSilver());
		return effects;
	}

	@Override
	protected ChooseBetweenEffectsDecision createDecision(int numberIfChoices, List<CardEffect> effects) {
		return new TrustySteedDecision(effects);
	}

}
