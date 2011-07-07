package org.jdominion.gui;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;

public class HandController extends CardListController {

	private IGuiInformationSource guiInformationSource;

	public HandController(IGuiInformationSource guiInformationSource) {
		super();
		this.guiInformationSource = guiInformationSource;
	}

	@Override
	protected List<Card> getCardList() {
		return new ArrayList<Card>(guiInformationSource.getHand().getCardList());
	}

}
