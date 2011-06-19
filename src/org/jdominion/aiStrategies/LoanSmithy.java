package org.jdominion.aiStrategies;

import java.util.ArrayList;
import java.util.List;

import org.jdominion.Card;
import org.jdominion.Hand;
import org.jdominion.Supply;
import org.jdominion.Turn;
import org.jdominion.cards.Gold;
import org.jdominion.cards.Loan;
import org.jdominion.cards.Province;
import org.jdominion.cards.Silver;
import org.jdominion.cards.Smithy;
import org.jdominion.decisions.ChooseActionCardToPlay;
import org.jdominion.decisions.ChooseCardToBuy;
import org.jdominion.effects.CardEffect;

public class LoanSmithy extends GenericAI {

	private int numOfLoansToBuy;
	private int numOfSmithiesToBuy;
	private int loansBought = 0;
	private int smithiesBought = 0;

	public LoanSmithy() {
		this(2, 1);
	}

	public LoanSmithy(int numOfLoansToBuy, int numOfSmithiesToBuy) {
		this.numOfLoansToBuy = numOfLoansToBuy;
		this.numOfSmithiesToBuy = numOfSmithiesToBuy;
	}

	@Override
	public String getName() {
		return "LoanSmithy";
	}

	@Override
	public List<Class<? extends Card>> getNeededCards() {
		List<Class<? extends Card>> neededCards = new ArrayList<Class<? extends Card>>();
		neededCards.add(Loan.class);
		neededCards.add(Smithy.class);
		return neededCards;
	}

	public void decide(ChooseCardToBuy decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		List<Class<? extends Card>> cardsToBuy = new ArrayList<Class<? extends Card>>();
		cardsToBuy.add(Province.class);
		cardsToBuy.add(Gold.class);
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
		}
	}

	public void decide(ChooseActionCardToPlay decision, CardEffect effect, Hand hand, Turn currentTurn, Supply supply) {
		if (hand.contains(Smithy.class)) {
			decision.addAnswer(hand.getCardByClass(Smithy.class));
		} else {
			decision.setCanceled(true);
		}
	}

}
