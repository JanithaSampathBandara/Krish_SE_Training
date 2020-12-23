package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class FiveHundredHandler extends Handler{

	@Override
	public int dispense(Currency currency) {
		
		if(currency.getAmount() >= 500) {
			currency.setFiveHundredCount((int)currency.getAmount() / 500);
			currency.setAmount(currency.getAmount() % 500);
			return currency.getFiveHundredCount();
		}
		else {
			return successor.dispense(currency);
		}
		
	}

}
