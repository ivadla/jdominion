package org.jdominion.decisions;

public abstract class YesNoDecision extends Decision<Boolean> {

	public YesNoDecision(String userMessage) {
		super(userMessage, false);
	}

	@Override
	public boolean isValidAnswer(Boolean answer) {
		return true;
	}

}
