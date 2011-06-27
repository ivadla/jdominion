package org.jdominion.cards.cornucopia;

import org.jdominion.cards.common.Estate;
import org.jdominion.effects.DrawCards;
import org.jdominion.effects.GainCardX;
import org.jdominion.effects.GiveCurse;
import org.jdominion.effects.OtherPlayersDiscardCardsDownToX;

public class Followers extends Prize {

	public Followers() {
		super("Followers");
		addCardEffect(new DrawCards(2));
		addCardEffect(new GainCardX(Estate.class));
		addCardEffect(new GiveCurse());
		addCardEffect(new OtherPlayersDiscardCardsDownToX(3));
	}

}
