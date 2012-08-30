package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.AddActions;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.darkAges.VagrantEffect;

public class Vagrant extends Card {

	public Vagrant() {
		super("Vagrant", 2);
		addCardEffect(new DrawCards(1));
		addCardEffect(new AddActions(1));
		addCardEffect(new VagrantEffect());
	}
}
