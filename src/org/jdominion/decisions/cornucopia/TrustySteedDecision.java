package org.jdominion.decisions.cornucopia;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseBetweenEffectsDecision;
import org.jdominion.decisions.multipleChoice.EffectChoice;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.CardEffect;
import org.jdominion.effects.DrawCards;

public class TrustySteedDecision extends ChooseBetweenEffectsDecision {

	public TrustySteedDecision(List<CardEffect> choices) {
		super(2, choices);
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		// TODO decide something here
		List<EffectChoice> answer = new ArrayList<EffectChoice>();
		answer.add(getChoice(AddActions.class));
		answer.add(getChoice(DrawCards.class));
	}

	private EffectChoice getChoice(Class<? extends CardEffect> type) {
		for (EffectChoice choice : getChoices()) {
			if (choice.getEffect().getClass() == type) {
				return choice;
			}
		}
		assert false;
		return null;
	}

}
