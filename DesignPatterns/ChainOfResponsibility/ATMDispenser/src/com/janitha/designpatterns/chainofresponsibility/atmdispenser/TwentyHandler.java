package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class TwentyHandler extends Handler{

	@Override
	public int dispense(Currency currency) {
		
		if(currency.getAmount() >= 20) {
			currency.setTwentyCount((int)currency.getAmount() / 20);
			currency.setAmount(currency.getAmount() % 20);
			return currency.getTwentyCount();
		}
		else {
			return successor.dispense(currency);
		}
		
	}

}
