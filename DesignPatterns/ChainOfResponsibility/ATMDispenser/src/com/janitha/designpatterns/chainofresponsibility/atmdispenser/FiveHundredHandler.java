package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class FiveHundredHandler extends Handler{

	@Override
	public String dispense(Currency currency) {
		
		double remainder;
		if((remainder = currency.getAmount() % 500) == 0) {
			currency.setFiveHundredCount((int)currency.getAmount() / 500);
			return "500 Notes : " + currency.getFiveHundredCount();
		}
		else {
			if(currency.getAmount() >= 500) {
				currency.setFiveHundredCount((int)currency.getAmount() / 500);
				currency.setAmount(remainder);
				System.out.println("500 Notes : " + currency.getFiveHundredCount());
			}
			
			return successor.dispense(currency);

		}
		
	}

}
