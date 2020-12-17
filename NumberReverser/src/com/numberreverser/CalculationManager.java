package com.numberreverser;

import java.io.IOException;
import java.math.BigInteger;

public class CalculationManager {

	public void reverseNumber(BigInteger number) throws ArithmeticException {
		
		BigInteger remainder = null;
		
		while((number.compareTo(new BigInteger("0"))==1)) {

				remainder = (number.mod(new BigInteger("10")));
				number = number.divide(new BigInteger("10"));
				System.out.print(remainder);
		
			}

	}
	
}
