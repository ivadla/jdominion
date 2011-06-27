package org.jdominion.aiStrategies;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.base.Festival;
import org.jdominion.cards.base.Smithy;
import org.jdominion.cards.common.Gold;
import org.jdominion.cards.common.Province;
import org.jdominion.cards.common.Silver;
import org.jdominion.cards.prosperity.Loan;
import org.jdominion.decisions.ChooseActionCardToPlay;
import org.jdominion.decisions.ChooseCardToBuy;
import org.jdominion.effects.CardEffect;

public class FestivalSmithyLoan extends GenericAI {

	private int numOfFestivalsToBuy;
	private int numOfLoansToBuy;
	private int numOfSmithiesToBuy;
	private int festivalsBought = 0;
	private int loansBought = 0;
	private int smithiesBought = 0;

	public FestivalSmithyLoan() {
		this(5, 4, 1);
	}

	public FestivalSmithyLoan(int numOfFestivalsToBuy, int numOfSmithiesToBuy, int numOfLoansToBuy) {
		this.numOfFestivalsToBuy = numOfFestivalsToBuy;
		this.numOfLoansToBuy = numOfLoansToBuy;
		this.numOfSmithiesToBuy = numOfSmithiesToBuy;
	}

	@Override
	public String getName() {
		return "FestivalSmithy";
	}

	@Override
	public List<Class<? extends Card>> getNeededCards() {
		List<Class<? extends Card>> neededCards = new ArrayList<Class<? extends Card>>();
		neededCards.add(Loan.class);
		neededCards.add(Smithy.class);
		neededCards.add(Festival.class);
		return neededCards;
	}

	public void decide(ChooseCardToBuy decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		List<Class<? extends Card>> cardsToBuy = new ArrayList<Class<? extends Card>>();
		if ((decision.getAvailableBuys() == 1) || (decision.getAvailableCoins() < 9)
				|| ((festivalsBought == numOfFestivalsToBuy) && (smithiesBought == numOfSmithiesToBuy))) {

			cardsToBuy.add(Province.class);
			cardsToBuy.add(Gold.class);
		}
		if (festivalsBought < numOfFestivalsToBuy) {
			cardsToBuy.add(Festival.class);
		}
		if (smithiesBought < numOfSmithiesToBuy) {
			cardsToBuy.add(Smithy.class);
		}
		if (loansBought < numOfLoansToBuy) {
			cardsToBuy.add(Loan.class);
		}
		cardsToBuy.add(Silver.class);

		setCardFromSupplyAsAnswer(decision, cardsToBuy, true);
		if (decision.getAnswer() == Loan.class) {
			loansBought++;
		} else if (decision.getAnswer() == Smithy.class) {
			smithiesBought++;
		} else if (decision.getAnswer() == Festival.class) {
			festivalsBought++;
		}
	}

	public void decide(ChooseActionCardToPlay decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		if (hand.contains(Festival.class)) {
			decision.addAnswer(hand.getCardByClass(Festival.class));
		} else if (hand.contains(Smithy.class)) {
			decision.addAnswer(hand.getCardByClass(Smithy.class));
		} else {
			decision.setCanceled(true);
		}
	}

}
