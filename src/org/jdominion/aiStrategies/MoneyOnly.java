package org.jdominion.aiStrategies;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.common.Duchy;
import org.jdominion.cards.common.Estate;
import org.jdominion.cards.common.Gold;
import org.jdominion.cards.common.Province;
import org.jdominion.cards.common.Silver;
import org.jdominion.decisions.ChooseCardToBuy;
import org.jdominion.effects.CardEffect;

public class MoneyOnly extends GenericAI {

	private int coinsToHaveBeforeFirstProvince;
	private int coinsToHaveBeforeFirstDuchy;
	private int coinsToHaveBeforeFirstEstate;

	public MoneyOnly(int coinsToHaveBeforeFirstProvince, int coinsToHaveBeforeFirstDuchy,
			int coinsToHaveBeforeFirstEstate) {
		this.coinsToHaveBeforeFirstProvince = coinsToHaveBeforeFirstProvince;
		this.coinsToHaveBeforeFirstDuchy = coinsToHaveBeforeFirstDuchy;
		this.coinsToHaveBeforeFirstEstate = coinsToHaveBeforeFirstEstate;
	}

	public MoneyOnly() {
		// TODO: find out when to buy a duchy or estate to increase the chance
		// of winning starting to buy a province when we have 19 coins results
		// in ~16.773 turns for 4 provinces. Other values for this case:
		// value avg.turns
		// 16	16,789707
		// 17	16,786658
		// 18	16,779344
		// 19	16,773033
		// 20	16,781988

		this(19, 1000, 1000);
	}

	@Override
	public String getName() {
		return "MoneyOnly";
	}

	@Override
	public List<Class<? extends Card>> getNeededCards() {
		return new ArrayList<Class<? extends Card>>();
	}

	public void decide(ChooseCardToBuy decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		List<Class<? extends Card>> cardsToBuy = new ArrayList<Class<? extends Card>>();
		if (currentTurn.getActivePlayer().countCoins(currentTurn) >= coinsToHaveBeforeFirstProvince) {
			cardsToBuy.add(Province.class);
		}
		if (currentTurn.getActivePlayer().countCoins(currentTurn) >= coinsToHaveBeforeFirstDuchy) {
			cardsToBuy.add(Duchy.class);
		}
		if (currentTurn.getActivePlayer().countCoins(currentTurn) >= coinsToHaveBeforeFirstEstate) {
			cardsToBuy.add(Estate.class);
		}
		cardsToBuy.add(Gold.class);
		cardsToBuy.add(Silver.class);

		setCardFromSupplyAsAnswer(decision, cardsToBuy, true);
	}

}
