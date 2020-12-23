package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class FiveThousandHandler extends Handler{

	@Override
	public int dispense(Currency currency) {
		
		if(currency.getAmount() >= 5000) {
			currency.setFiveThousandCount((int)currency.getAmount() / 5000);
			currency.setAmount(currency.getAmount() % 5000);
			return currency.getFiveThousandCount();
		}
		else {
			return successor.dispense(currency);
		}
		
			
//		atm.setFiveThousandCount((int) atm.getAmount() / 5000);
//		if(atm.getAmount() >= 1) {
//			return atm.getFiveThousandCount();
//		}
//		else {
//			return successor.dispense(atm);
//		}
	}

}
