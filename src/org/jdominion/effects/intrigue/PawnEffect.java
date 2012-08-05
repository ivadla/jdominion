package org.jdominion.effects.intrigue;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.decisions.ChooseBetweenEffectsDecision;
import org.jdominion.decisions.intrigue.PawnDecision;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.AddBuys;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.CardEffect;
import org.jdominion.effects.ChooseBetweenEffects;
import org.jdominion.effects.DrawCards;

public class PawnEffect extends ChooseBetweenEffects {

	public PawnEffect() {
		super(2, createEffects());
	}

	private static List<CardEffect> createEffects() {
		List<CardEffect> effects = new ArrayList<CardEffect>();
		effects.add(new DrawCards(1));
		effects.add(new AddActions(1));
		effects.add(new AddBuys(1));
		effects.add(new AddExtraMoney(1));
		return effects;
	}

	@Override
	protected ChooseBetweenEffectsDecision createDecision(int numberOfChoices, List<CardEffect> effects) {
		return new PawnDecision(numberOfChoices, effects);
	}

}
