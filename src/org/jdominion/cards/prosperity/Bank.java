package org.jdominion.cards.prosperity;

import org.jdominion.Card;
import org.jdominion.effects.prosperity.BankEffect;

public class Bank extends Card {

	public Bank() {
		super("Bank", new BankEffect(), 7);
	}
}
