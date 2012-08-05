package org.jdominion.decisions.intrigue;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.Card.Type;
import org.jdominion.decisions.ChooseBetweenEffectsDecision;
import org.jdominion.decisions.multipleChoice.EffectChoice;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.CardEffect;
import org.jdominion.effects.DrawCards;

public class NoblesDecision extends ChooseBetweenEffectsDecision {

	public NoblesDecision(int maxAnswers, List<CardEffect> choices) {
		super(maxAnswers, choices);
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {
		List<EffectChoice> answer = new ArrayList<EffectChoice>();
		if (currentTurn.getAvailableActions() == 0 && hand.contains(Type.ACTION)) {
			answer.add(getChoice(AddActions.class));
		} else {
			answer.add(getChoice(DrawCards.class));
		}
		this.setAnswer(answer);
	}

}
