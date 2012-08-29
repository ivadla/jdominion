package org.jdominion.decisions.darkAges;

import org.jdominion.Hand;
import org.jdominion.decisions.ChooseCardsFromHandToTrash;

public class ChooseCardFromHandToTrashThatIsNotARats extends ChooseCardsFromHandToTrash {

	public ChooseCardFromHandToTrashThatIsNotARats(Hand hand) {
		super(false, 1, 1, hand);
	}
}
