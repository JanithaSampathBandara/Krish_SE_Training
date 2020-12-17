package com.numberreverser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.InputMismatchException;

public class Application {

	public static void main(String[] args) {
		
		CalculationManager calculationManager = new CalculationManager();
		
		try {
			
			BufferedReader buffredReader = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter any number : ");
			
			BigInteger number = new BigInteger(buffredReader.readLine());
			
			
			
			System.out.println("Before Reverse The Number : " + number);
			
			System.out.println("Reversed Number : ");
			
			calculationManager.reverseNumber(number);
			
			
		}
		catch(InputMismatchException inputMismatchException) {
			System.out.println("Additional Info : " + inputMismatchException);
		}
		catch(ArithmeticException arithmeticException) {
			System.out.println("Additional Info : " + arithmeticException);
		}
		catch(IOException ioException) {
			System.out.println("Additional Info : " + ioException);
		}
		
	}

}
