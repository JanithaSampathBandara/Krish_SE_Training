package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class HundredHandler extends Handler{

	@Override
	public String dispense(Currency currency) {
		
		double remainder;
		if((remainder = currency.getAmount() % 100) == 0) {
			currency.setHundredCount((int)currency.getAmount() / 100);
			return "100 Notes : " + currency.getHundredCount();
		}
		else {
			if(currency.getAmount() >= 100) {
				currency.setHundredCount((int)currency.getAmount() / 100);
				currency.setAmount(remainder);
				System.out.println("100 Notes : " + currency.getHundredCount());
			}
			
			return successor.dispense(currency);

		}

	}

}
