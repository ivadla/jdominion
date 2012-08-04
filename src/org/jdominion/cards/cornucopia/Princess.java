package org.jdominion.cards.cornucopia;

import org.jdominion.effects.AddBuys;
import org.jdominion.effects.cornucopia.PrincessEffect;

public class Princess extends Prize {
	public Princess() {
		super("Princess");
		addCardEffect(new AddBuys(1));
		addCardEffect(new PrincessEffect());
	}

}
