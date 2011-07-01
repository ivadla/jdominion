package org.jdominion.gui;

import java.awt.Checkbox;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

import org.jdominion.Card;

public class SelectCardsView extends JDialog implements ItemListener {

	private MigLayout layoutManager = null;
	private Map<Checkbox, Card> checkboxCardMapping;
	private SelectCardsController controller;

	public SelectCardsView(JFrame parent, SelectCardsController controller, String title, final String messageToUser,
			final List<Card> cards, final List<Card> selectedCards) {
		super(parent, title, false);
		this.controller = controller;
		this.addWindowListener(controller);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				init(messageToUser, cards, selectedCards);
			}
		});
	}

	private void init(String messageToUser, List<Card> cards, List<Card> selectedCards) {

		this.setLayout(this.getLayoutManager());
		createMessageLabel(messageToUser);
		createCheckboxes(cards, selectedCards);
		this.pack();
		this.setVisible(true);
	}

	private void createMessageLabel(String messageToUser) {
		this.getContentPane().add(new JLabel(messageToUser), new CC().wrap().spanX());
	}

	private Checkbox createCheckbox(Card card, boolean checked) {
		Checkbox newCheckbox = new Checkbox(card.getName(), checked);
		newCheckbox.addItemListener(this);
		getContentPane().add(newCheckbox);
		return newCheckbox;
	}

	private void createCheckboxes(List<Card> cards, List<Card> selectedCards) {
		checkboxCardMapping = new HashMap<Checkbox, Card>();
		for (Card card : cards) {
			checkboxCardMapping.put(createCheckbox(card, selectedCards.contains(card)), card);
		}
	}

	private MigLayout getLayoutManager() {
		if (this.layoutManager == null) {
			this.layoutManager = new MigLayout(new LC().wrapAfter(5));
		}
		return this.layoutManager;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() instanceof Checkbox) {
			Checkbox checkbox = (Checkbox) e.getSource();
			if (checkboxCardMapping.containsKey(checkbox)) {
				controller.cardChangedState(checkboxCardMapping.get(checkbox), checkbox.getState());
				return;
			}
		}
		throw new RuntimeException("Unknown Item changed: " + e.getSource());
	}

	public void enOrDisableUncheckedCheckboxes(boolean enable) {
		for (Entry<Checkbox, Card> entry : checkboxCardMapping.entrySet()) {
			if (!entry.getKey().getState()) {
				entry.getKey().setEnabled(enable);
			}
		}
	}
}
