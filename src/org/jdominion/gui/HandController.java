package org.jdominion.gui;

import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;

public class HandController extends CardListController {

	private IGuiInformationSource guiInformationSource;
	
	public HandController(IGuiInformationSource guiInformationSource) {
		super();
		this.guiInformationSource = guiInformationSource;
	}

	@Override
	protected List<Card> getCardList() {
		Hand hand = guiInformationSource.getHand();
		List<Card> cards = hand.getCardList();
		return cards;
	}

}
