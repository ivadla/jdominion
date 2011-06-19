package org.jdominion.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.apache.commons.lang.NotImplementedException;
import org.jdominion.decisions.multipleChoice.Choice;

public class MultipleChoiceController implements ActionListener {

	private MultipleChoiceView view;
	private List<Choice> choices;
	private List<Choice> answer = null;

	public MultipleChoiceController(JFrame parent, String userMessage, boolean cancelable, int minAnswers,
			int maxAnswers, List<Choice> choices) {
		this.choices = choices;

		if (cancelable || minAnswers != 1 || maxAnswers != 1) {
			throw new NotImplementedException("choosing multiple answers is not yet implemented");
		}

		this.view = new MultipleChoiceView(parent, this, userMessage, convertChoicesToText(choices), false);
	}

	private List<String> convertChoicesToText(List<Choice> choices) {
		List<String> textChoices = new ArrayList<String>();

		for (Choice choice : choices) {
			textChoices.add(choice.getUserMessage());
		}

		return textChoices;
	}

	public synchronized List<Choice> askUser() {

		view.setVisible(true);

		while (this.answer == null) {
			try {
				this.wait();
			} catch (InterruptedException e) {

			}
		}
		this.view.dispose();
		return this.answer;
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		List<Choice> answer = new ArrayList<Choice>();
		answer.add(getChoiceFromString(choices, e.getActionCommand()));
		this.answer = answer;
		this.notifyAll();
	}

	private Choice getChoiceFromString(List<Choice> list, String choiceText) {
		for (Choice choice : list) {
			if (choice.getUserMessage() == choiceText) {
				return choice;
			}
		}
		throw new RuntimeException("can't find a choice with the usermessage " + choiceText);
	}

}
