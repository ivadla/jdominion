package org.jdominion.extraGameData;

import org.apache.commons.lang.NotImplementedException;
import org.jdominion.Card;
import org.jdominion.CardClassInfo;

public class BaneCard extends ExtraGameData<Class<? extends Card>> {

	private Class<? extends Card> baneCard;

	public BaneCard(Class<? extends Card> baneCard) {
		super("Bane card", true);
		this.baneCard = baneCard;
	}

	@Override
	public void set(Class<? extends Card> data) {
		throw new NotImplementedException();
	}

	@Override
	public Class<? extends Card> get() {
		return baneCard;
	}

	@Override
	public void add(Class<? extends Card> data) {
		throw new NotImplementedException();
	}

	@Override
	public String getContentsAsString() {
		return CardClassInfo.getInstance().getName(baneCard);
	}
}
