package org.jdominion.gui;

import java.util.List;

import org.jdominion.Card;

public class PlayAreaController extends CardListController {

	private IGuiInformationSource guiInformationSource;

	public PlayAreaController(IGuiInformationSource guiInformationSource) {
		this.guiInformationSource = guiInformationSource;
	}

	@Override
	protected List<Card> getCardList() {
		return this.guiInformationSource.getPlayArea();
	}

}
