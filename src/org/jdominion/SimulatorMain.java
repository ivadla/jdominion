package org.jdominion;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.aiStrategies.*;
import org.jdominion.simulation.Cursecounter;
import org.jdominion.simulation.SimulationParameter;
import org.jdominion.simulation.Simulator;

public class SimulatorMain {

	public static void main(String[] args) {
		
		Simulator simulator = new Simulator(100000);

		simulator.addPlayer(WitchStrategy.class);
		
		
		List<SimulationParameter> parameters = new ArrayList<SimulationParameter>();
		parameters.add(new SimulationParameter(1, 2));
		simulator.addPlayer(SmithyStrategy.class, parameters);
		
		parameters = new ArrayList<SimulationParameter>();
		parameters.add(new SimulationParameter(18,20));
		parameters.add(new SimulationParameter(20,22));
		parameters.add(new SimulationParameter(20,22));
		simulator.addPlayer(MoneyOnly.class,parameters);
		
		simulator.run(new Cursecounter());
	}
		
}
