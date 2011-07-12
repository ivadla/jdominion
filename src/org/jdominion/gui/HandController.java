package org.jdominion.gui;

import org.jdominion.CardList;

public class HandController extends CardListController {

	private IGuiInformationSource guiInformationSource;

	public HandController(IGuiInformationSource guiInformationSource) {
		super();
		this.guiInformationSource = guiInformationSource;
	}

	@Override
	protected CardList getCardList() {
		return new CardList(guiInformationSource.getHand().getCardList());
	}

}
