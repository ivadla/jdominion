package org.jdominion.effects.intrigue;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.decisions.ChooseBetweenEffectsDecision;
import org.jdominion.decisions.intrigue.StewardDecision;
import org.jdominion.effects.AddExtraMoney;
import org.jdominion.effects.CardEffect;
import org.jdominion.effects.ChooseBetweenEffects;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.TrashCards;

public class StewardEffect extends ChooseBetweenEffects {

	public StewardEffect() {
		super(1, createEffectList());
	}

	private static List<CardEffect> createEffectList() {
		List<CardEffect> cardEffects = new ArrayList<CardEffect>();
		cardEffects.add(new DrawCards(2));
		cardEffects.add(new AddExtraMoney(2));
		cardEffects.add(new TrashCards(2, 2));
		return cardEffects;
	}

	@Override
	protected ChooseBetweenEffectsDecision createDecision(int numberIfChoices, List<CardEffect> effects) {
		return new StewardDecision(effects);
	}

}
