package com.janitha.designpatterns.singleton;

public class Application {
	
	public static void main(String[] args) {
		
		long start;
		long end;
		
		Student student1 = new Student(100, "Janitha", "Maths", 80);
		Student student2 = new Student(200, "Sampath", "Science", 85);
		Student student3 = new Student(300, "Bandara", "English", 90);
		

		start = System.currentTimeMillis();
		Printer printer1 = Printer.getPrinter();
		end = System.currentTimeMillis();
		System.out.println("Time taken : " + (end - start) +" miliseconds");
		
		System.out.println("Intance ID : " + printer1);
		printer1.print(student1, 3);
		System.out.println();
		
		
		start = System.currentTimeMillis();
		Printer printer2 = Printer.getPrinter();
		end = System.currentTimeMillis();
		System.out.println("Time taken : " + (end - start) +" miliseconds");
		
		System.out.println("Intance ID : " + printer2);
		printer2.print(student2, 10);
		System.out.println();
		
		
		start = System.currentTimeMillis();
		Printer printer3 = Printer.getPrinter();
		end = System.currentTimeMillis();
		System.out.println("Time taken : " + (end - start) +" miliseconds");
		
		System.out.println("Intance ID : " + printer3);
		printer3.print(student3, 15);
		System.out.println();
		
		
	}
	

}
