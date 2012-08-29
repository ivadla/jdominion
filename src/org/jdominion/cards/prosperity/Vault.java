package org.jdominion.cards.prosperity;

import org.jdominion.Card;
import org.jdominion.effects.DiscardCardsForCoinsEffect;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.prosperity.VaultEffect;

public class Vault extends Card {

	public Vault() {
		super("Vault", 5);
		addCardEffect(new DrawCards(2));
		addCardEffect(new DiscardCardsForCoinsEffect());
		addCardEffect(new VaultEffect());
	}
}
