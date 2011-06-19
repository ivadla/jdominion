package org.jdominion.aiStrategies;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;

public class DummyStrategy extends GenericAI {

	@Override
	public String getName() {
		return "Dummy";
	}

	@Override
	public List<Class<? extends Card>> getNeededCards() {
		return new ArrayList<Class<? extends Card>>();
	}

}
