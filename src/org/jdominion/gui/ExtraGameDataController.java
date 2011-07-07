package org.jdominion.gui;

import org.jdominion.extraGameData.ExtraGameData;

public class ExtraGameDataController {

	private IGuiInformationSource guiInformationSource;
	private ExtraGameDataView view;

	public ExtraGameDataView getView() {
		return view;
	}

	public ExtraGameDataController(IGuiInformationSource guiInformationSource) {
		this.guiInformationSource = guiInformationSource;
		this.view = new ExtraGameDataView();
	}

	public void update() {
		String text = "";
		for (ExtraGameData<?> gameData : guiInformationSource.getExtraGameData()) {
			text += gameData.getName() + ": " + gameData.getContentsAsString() + "\n";
		}
		view.setContent(text);
	}

}
