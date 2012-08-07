package org.jdominion.decisions.darkAges;

import java.util.List;

import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.decisions.ChooseBetweenEffectsDecision;
import org.jdominion.effects.CardEffect;

public class GraverobberDecision extends ChooseBetweenEffectsDecision {

	public GraverobberDecision(List<CardEffect> choices) {
		super(1, choices);
	}

	@Override
	public void chooseDefaultAnswer(Hand hand, Turn currentTurn, Supply supply) {

	}

}
