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
	private int expectedNumberOfAnswers;
	private JFrame parent;
	private String userMessage;

	public MultipleChoiceController(JFrame parent, String userMessage, boolean cancelable, int minAnswers,
			int maxAnswers, List<Choice> choices) {
		this.choices = choices;
		this.parent = parent;
		this.userMessage = userMessage;

		if (cancelable || minAnswers != maxAnswers) {
			throw new NotImplementedException("choosing a variable number of answers is not yet implemented");
		}
		expectedNumberOfAnswers = maxAnswers;
		this.answer = new ArrayList<Choice>();

	}

	private void createNewView() {
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
		while (this.answer.size() != this.expectedNumberOfAnswers) {
			createNewView();
			try {
				this.wait();
			} catch (InterruptedException e) {

			}
			this.view.dispose();
		}

		return this.answer;
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		Choice choice = getChoiceFromString(choices, e.getActionCommand());
		answer.add(choice);
		this.choices.remove(choice);
		this.notifyAll();
	}

	private Choice getChoiceFromString(List<Choice> list, String choiceText) {
		for (Choice choice : list) {
			if (choice.getUserMessage().equals(choiceText)) {
				return choice;
			}
		}
		throw new RuntimeException("can't find a choice with the usermessage " + choiceText);
	}

}
