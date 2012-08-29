package org.jdominion.cards.darkAges;

import org.jdominion.Card;
import org.jdominion.effects.darkAges.FeodumEffect;
import org.jdominion.effects.darkAges.FeodumOnTrashEffect;

public class Feodum extends Card {

	public Feodum() {
		super("Feodum", 4);
		addCardEffect(new FeodumEffect());
		addCardEffect(new FeodumOnTrashEffect());
	}
}
