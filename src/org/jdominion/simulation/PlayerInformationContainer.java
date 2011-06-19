package org.jdominion.simulation;

import java.util.List;

import org.jdominion.aiStrategies.IStrategy;

public class PlayerInformationContainer {

	private int id;
	private Class<? extends IStrategy> strategy;
	private List<SimulationParameter> parameters;
	private String name;

	public PlayerInformationContainer(int id, Class<? extends IStrategy> strategy, List<SimulationParameter> parameters) {
		this.id = id;
		this.strategy = strategy;
		this.parameters = parameters;
	}

	public int getId() {
		return id;
	}

	public Class<? extends IStrategy> getStrategy() {
		return strategy;
	}

	public List<SimulationParameter> getParameters() {
		return parameters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
