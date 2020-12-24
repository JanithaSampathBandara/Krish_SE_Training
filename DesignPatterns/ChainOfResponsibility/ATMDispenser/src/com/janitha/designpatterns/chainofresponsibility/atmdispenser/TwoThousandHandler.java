package com.janitha.designpatterns.chainofresponsibility.atmdispenser;

public class TwoThousandHandler extends Handler{

	@Override
	public String dispense(Currency currency) {

		//Checking for exactly divisable amount
		double remainder;
		if((remainder = currency.getAmount() % 2000) == 0) {
			currency.setTwoThousandCount((int)currency.getAmount() / 2000);
			return "2000 Notes : " + currency.getTwoThousandCount();
		}
		else {
			//Amount greater than handler value should dispense here
			if(currency.getAmount() >= 2000) {
				currency.setTwoThousandCount((int)currency.getAmount() / 2000);
				currency.setAmount(remainder);
				System.out.println("2000 Notes : " + currency.getTwoThousandCount());
			}
			//Amount lower than handler value pass to next Handler
			return successor.dispense(currency);

		}

	}

}
