package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class HundredHandler extends Handler{

	@Override
	public int dispense(Currency currency) {
		
		if(currency.getAmount() >= 100) {
			currency.setHundredCount((int)currency.getAmount() / 100);
			currency.setAmount(currency.getAmount() % 100);
			return currency.getHundredCount();
		}
		else {
			return successor.dispense(currency);
		}
		
	}

}
