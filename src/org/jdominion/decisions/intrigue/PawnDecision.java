package org.jdominion.decisions.intrigue;

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

public class PawnDecision extends ChooseBetweenEffectsDecision {

	public PawnDecision(int maxAnswers, List<CardEffect> choices) {
		super(maxAnswers, choices);
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		// TODO decide something here
		List<EffectChoice> answer = new ArrayList<EffectChoice>();
		answer.add(getChoice(AddActions.class));
		answer.add(getChoice(DrawCards.class));
		this.setAnswer(answer);
	}

}
