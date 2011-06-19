package org.jdominion.gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

public class RevealedCardsView extends JDialog {

	private MigLayout layoutManager = null;
	private JButton okButton = null;
	private RevealedCardsController controller;
	private int columnCounter = 0;

	public RevealedCardsView(JFrame parent, String title, RevealedCardsController controller) {
		super(parent, title, false);
		this.controller = controller;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				init();
			}
		});
	}

	private void init() {
		this.addWindowListener(controller);
		this.setLayout(this.getLayoutManager());
		getContentPane().add(getOKButton(), new CC().cell(0, 2));
	}

	private MigLayout getLayoutManager() {
		if (this.layoutManager == null) {
			this.layoutManager = new MigLayout();
		}
		return this.layoutManager;
	}

	private JButton getOKButton() {
		if (this.okButton == null) {
			this.okButton = new JButton("OK");
			this.okButton.addActionListener(controller);
		}
		return this.okButton;
	}

	public void addCardImages(final String title, final List<CardImage> cards) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				getContentPane().add(new JLabel(title), new CC().cell(columnCounter, 0));
				for (CardImage card : cards) {
					getContentPane().add(card, new CC().cell(columnCounter, 1));
				}
				columnCounter++;
			}
		});
	}
	
	public void setVisible() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				pack();
				setVisible(true);
			}
		});
	}
}
