package org.jdominion.gui;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import org.apache.commons.lang.NotImplementedException;

public class MultipleChoiceView extends JDialog {

	private ActionListener controller;
	private MigLayout layoutManager = null;

	public MultipleChoiceView(JFrame parent, ActionListener controller, final String userMessage,
			final List<String> choices, final boolean canChooseMoreThanOne) {
		super(parent);
		this.controller = controller;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				init(userMessage, choices, canChooseMoreThanOne);
			}
		});
	}

	private void init(String userMessage, List<String> choices, boolean canChooseMoreThanOne) {
		if (canChooseMoreThanOne) {
			throw new NotImplementedException("choosing multiple answers is not yet implemented");
		}

		this.setLocationRelativeTo(getParent());
		this.setLayout(getLayoutManager());

		this.getContentPane().add(new JLabel(userMessage), new CC().wrap().spanX());

		addChoiceButtons(choices);

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.pack();
		this.setVisible(true);
	}

	private void addChoiceButtons(List<String> choices) {
		for (String choiceText : choices) {
			JButton button = new JButton(choiceText);
			button.addActionListener(controller);
			getContentPane().add(button);
		}
	}

	private MigLayout getLayoutManager() {
		if (this.layoutManager == null) {
			this.layoutManager = new MigLayout();
		}
		return this.layoutManager;
	}

}
