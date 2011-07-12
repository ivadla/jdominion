package org.jdominion.gui;

import org.jdominion.CardList;

public class PlayAreaController extends CardListController {

	private IGuiInformationSource guiInformationSource;

	public PlayAreaController(IGuiInformationSource guiInformationSource) {
		this.guiInformationSource = guiInformationSource;
	}

	@Override
	protected CardList getCardList() {
		return this.guiInformationSource.getPlayArea();
	}

}
