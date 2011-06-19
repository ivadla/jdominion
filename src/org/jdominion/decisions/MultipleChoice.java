package org.jdominion.decisions;

import java.util.List;

import org.jdominion.decisions.multipleChoice.Choice;

public abstract class MultipleChoice<E extends Choice> extends Decision<List<E>> {

	private int minAnswers;
	private int maxAnswers;
	private List<E> choices;

	public List<E> getChoices() {
		return choices;
	}

	public int getMinAnswers() {
		return minAnswers;
	}

	public int getMaxAnswers() {
		return maxAnswers;
	}

	public MultipleChoice(String userMessage, boolean cancelable, int minAnswers, int maxAnswers, List<E> choices) {
		super(userMessage, cancelable);
		this.minAnswers = minAnswers;
		this.maxAnswers = maxAnswers;
		this.choices = choices;
	}

	@Override
	public boolean isValidAnswer(List<E> answer) {
		if ((answer.size() < minAnswers) || (answer.size() > this.maxAnswers)) {
			return false;
		}

		for (Choice answerChoice : answer) {
			if (!choices.contains(answerChoice)) {
				return false;
			}
		}
		return true;
	}

}
