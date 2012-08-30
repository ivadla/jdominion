package org.jdominion.decisions.darkAges;

import org.jdominion.Hand;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;

public class ChooseActionCardFromHandToTrash extends ChooseCardsFromHandToTrash {

	public ChooseActionCardFromHandToTrash(Hand hand) {
		this(hand, false);
	}

	public ChooseActionCardFromHandToTrash(Hand hand, boolean cancelable) {
		super("Choose an action card to trash", cancelable, 1, 1, hand);
	}

}
