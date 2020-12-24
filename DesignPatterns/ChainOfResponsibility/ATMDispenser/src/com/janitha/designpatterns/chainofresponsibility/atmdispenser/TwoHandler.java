package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class TwoHandler extends Handler{

	@Override
	public String dispense(Currency currency) {
		
		double remainder;
		if((remainder = currency.getAmount() % 2) == 0) {
			currency.setTwoCount((int)currency.getAmount() / 2);
			return "2 Coins : " + currency.getTwoCount();
		}
		else {
			if(currency.getAmount() >= 2) {
				currency.setTwoCount((int)currency.getAmount() / 2);
				currency.setAmount(remainder);
				System.out.println("2 Coins : " + currency.getTwoCount());
			}

			return "1 Coins : " + (int)currency.getAmount();

		}

	}
	
}
