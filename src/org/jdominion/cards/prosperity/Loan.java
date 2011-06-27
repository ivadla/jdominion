package org.jdominion.cards.prosperity;

import org.jdominion.Card;
import org.jdominion.effects.CardEffectTreasure;
import org.jdominion.effects.prosperity.LoanEffect;

public class Loan extends Card {

	public Loan() {
		super("Loan", 3);
		this.addCardEffect(new CardEffectTreasure(1));
		this.addCardEffect(new LoanEffect());
	}

}
