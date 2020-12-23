package com.janitha.designpatterns.singleton;

public class Printer {

	public static volatile Printer printer;
	
	private Printer() {
		//Avoiding manually creation of instances by Reflaction Frameworks, Cloning, Serialization.
		if(printer != null) {
			
			throw new RuntimeException("Use getPrinter method to get the instance of Printer");
		}
	}
	
	//Getting the instance of Printer
	public static Printer getPrinter() {
		
		//Double checking implementation and blocking the instance creation segment to make sure its thread safe
		if(printer == null) {
			
			synchronized(Printer.class) {
				
				if(printer == null) {
					
					printer = new Printer();
				}
			}
			
		}
		
		return printer;
	}
	
	
	public void print(Student student, int noOfPages) {
		
		System.out.println("Student Details");
		System.out.println("Student Id : " + student.getId() + ", Student Name : " + student.getName() + ", Subject : " + student.getSubject() + ", Marks : " + student.getMarks());
		System.out.println(noOfPages + " Pages printed.");
	}
	
}
