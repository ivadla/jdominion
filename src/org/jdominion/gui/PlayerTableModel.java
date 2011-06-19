package org.jdominion.gui;

import javax.swing.table.AbstractTableModel;

public class PlayerTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private IGuiInformationSource guiInformationSource;

	public PlayerTableModel(IGuiInformationSource guiInformationSource) {
		super();
		this.guiInformationSource = guiInformationSource;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return guiInformationSource.getPlayers().size() + 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex == 0) {
			switch (columnIndex) {
			case 0:
				return null;
			case 1:
				return "Hand";
			case 2:
				return "Deck";
			case 3:
				return "Discard Pile";
			}
		}
		switch (columnIndex) {
		case 0:
			return guiInformationSource.getPlayers().get(rowIndex - 1).getName();
		case 1:
			return guiInformationSource.getPlayers().get(rowIndex - 1).getHandSize();
		case 2:
			return guiInformationSource.getPlayers().get(rowIndex - 1).getDeckSize();
		case 3:
			return guiInformationSource.getPlayers().get(rowIndex - 1).getDiscardPileSize();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
