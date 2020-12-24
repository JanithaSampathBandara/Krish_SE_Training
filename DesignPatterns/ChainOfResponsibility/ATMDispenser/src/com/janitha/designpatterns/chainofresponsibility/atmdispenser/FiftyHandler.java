package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class FiftyHandler extends Handler{

	@Override
	public String dispense(Currency currency) {
		
		double remainder;
		if((remainder = currency.getAmount() % 50) == 0) {
			currency.setFiftyCount((int)currency.getAmount() / 50);
			return "50 Notes" + currency.getFiftyCount();
		}
		else {
			if(currency.getAmount() >= 50) {
				currency.setFiftyCount((int)currency.getAmount() / 50);
				currency.setAmount(remainder);
				System.out.println("50 Notes : " + currency.getFiftyCount());
			}
			
			return successor.dispense(currency);

		}
		
	}

}
