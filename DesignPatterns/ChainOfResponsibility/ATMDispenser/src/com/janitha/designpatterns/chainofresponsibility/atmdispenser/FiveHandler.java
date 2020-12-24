package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class FiveHandler extends Handler{

	@Override
	public String dispense(Currency currency) {
		
		double remainder;
		if((remainder = currency.getAmount() % 5) == 0) {
			currency.setFiveCount((int)currency.getAmount() / 5);
			return "5 Coins : " + currency.getFiveCount();
		}
		else {
			if(currency.getAmount() >= 5) {
				currency.setFiveCount((int)currency.getAmount() / 5);
				currency.setAmount(remainder);
				System.out.println("5 Coins : " + currency.getFiveCount());
			}
			
			return successor.dispense(currency);

		}
		
	}

}
