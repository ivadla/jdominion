package org.jdominion.aiStrategies;

import org.jdominion.cards.prosperity.Loan;

public class LoanStrategy extends BuyOneTypeOfActionCard {

	public LoanStrategy() {
		this(2);
	}

	public LoanStrategy(int numOfLoansToBuy) {
		super(Loan.class, numOfLoansToBuy);
	}

}
