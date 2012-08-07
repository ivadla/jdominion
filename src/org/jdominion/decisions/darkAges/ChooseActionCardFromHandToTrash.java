package org.jdominion.decisions.darkAges;

import org.jdominion.Hand;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;

public class ChooseActionCardFromHandToTrash extends ChooseCardsFromHandToTrash {

	public ChooseActionCardFromHandToTrash(Hand hand) {
		super("Choose an action card to trash", false, 1, 1, hand);
	}

}
