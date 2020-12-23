package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class FiftyHandler extends Handler{

	@Override
	public int dispense(Currency currency) {
		
		if(currency.getAmount() >= 50) {
			currency.setFiftyCount((int)currency.getAmount() / 50);
			currency.setAmount(currency.getAmount() % 50);
			return currency.getFiftyCount();
		}
		else {
			return successor.dispense(currency);
		}
	}

}
