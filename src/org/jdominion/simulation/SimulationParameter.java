package org.jdominion.simulation;

public class SimulationParameter {

	@Override
	public String toString() {
		return currentValue + "";
	}

	private int min;
	private int max;
	private int currentValue;

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}

	public void increaseValue() {
		currentValue++;
		if (currentValue > max) {
			currentValue = min;
		}
	}

	public SimulationParameter(int min, int max) {
		this.min = min;
		this.max = max;
		currentValue = min;
	}

}
