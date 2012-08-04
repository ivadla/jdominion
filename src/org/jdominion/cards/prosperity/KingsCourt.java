package org.jdominion.cards.prosperity;

import org.jdominion.Card;
import org.jdominion.effects.base.ThroneRoomEffect;

public class KingsCourt extends Card {

	public KingsCourt() {
		super("King's Court", new ThroneRoomEffect(3, true), 7);
	}
}
