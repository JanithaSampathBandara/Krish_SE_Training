package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class FiveThousandHandler extends Handler{

	@Override
	public String dispense(Currency currency) {
		//Checking for exactly divisable amount
		double remainder;
		if((remainder = currency.getAmount() % 5000) == 0) {
			currency.setFiveThousandCount((int)currency.getAmount() / 5000);
			return "5000 Notes : "+ currency.getFiveThousandCount();
		}
		else {
			//Amount greater than handler value should dispense here
			if(currency.getAmount() >= 5000) {
				currency.setFiveThousandCount((int)currency.getAmount() / 5000);
				currency.setAmount(remainder);
				System.out.println("5000 Notes : " + currency.getFiveThousandCount());
			}
			
			//Amount lower than handler value pass to next Handler
			return successor.dispense(currency);

		}

	}

}
