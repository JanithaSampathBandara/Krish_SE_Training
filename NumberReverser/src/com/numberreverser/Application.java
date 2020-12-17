package com.numberreverser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Application {

	public static void main(String[] args) {
		
		
		try {
			
			BufferedReader buffredReader = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter any number which is not ended with Zero : ");
			
			BigInteger number = new BigInteger(buffredReader.readLine());
			BigInteger remainder = null;
			
			
			System.out.println("Before Reverse The Number : " + number);
		//	int remainder = 0;
			
			System.out.println("Reversed Number : ");
			while((number.compareTo(new BigInteger("0"))==1)) {
			//	System.out.println("xx");
				remainder = (number.mod(new BigInteger("10")));
				number = number.divide(new BigInteger("10"));
			//	System.out.println(number);
				System.out.print(remainder);
		
			}
		//	System.out.print(number);

			
		}
		catch(ArithmeticException arithmeticException) {
			System.out.println("Additional Info : " + arithmeticException);
		}
		catch(FileNotFoundException fileNotFoundException) {
			System.out.println("Additional Info : " + fileNotFoundException);
		}
		catch(IOException ioException) {
			System.out.println("Additional Info : " + ioException);
		}
		
	}

}
