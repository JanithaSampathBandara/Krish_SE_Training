package com.exceptionhandling;
 import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FileManager {
	
void readFile(String fileName) throws IOException, ArithmeticException{
		
		System.out.println("Reading  File");
		
		FileInputStream fileInputStream	= null;
		XSSFWorkbook workBook = null;
		XSSFSheet sheet = null;
		
		if(validateFile(fileName)) {
			
			try {
				
				fileInputStream = new FileInputStream(fileName);
				workBook = new XSSFWorkbook(fileInputStream);
				sheet = workBook.getSheetAt(0);
			 
				String eid = "";
				String name = "";
				double basicSal = 0.00;
				double incrementPerentage = 0.00;
				double netSal = 0.00;
			
				int row = 1;
				Employee employee;
				//Reading from excel sheet for Employeeid, Name, IncrementPercentage and NetSalary
				while(sheet.getRow(row) != null) {
				
					eid = sheet.getRow(row).getCell(0).toString();
					name = sheet.getRow(row).getCell(1).toString();
					basicSal = Double.parseDouble(sheet.getRow(row).getCell(2).toString());
					incrementPerentage = Double.parseDouble(sheet.getRow(row).getCell(3).toString());

					employee = new Employee(eid, name, basicSal, incrementPerentage);
						
					
					netSal = employee.validateEmployee(eid);
					if(netSal != 0.00) {
						System.out.println("Net Salary For Employee " + name + " = " + netSal);
					}
					else {
						System.out.println(employee.name + " Not An Executive Employee");
					}
				
					row++;
				}
			
			
		}
		finally {
			fileInputStream.close();
		}

	}

}
	
	
	boolean validateFile(String fname) {
		
		boolean validation  = false;
		
		if(fname != "Salary.xlsx") {
			validation = true;
		}
		else {
			validation = false;
		}
		
		// Validating whether excel file is correct or not
//		if(validation == true) {
//			
//			System.out.println("Validated");
//			
//			// Hardcoding  Employee ID and basic salary
//			String eid = "E001";
//			String name = "Kamal";
//			double basicSal = 10000.00;
//			Employee emp = new Employee(eid, name, basicSal);
//			
//			
//			emp.validateEmployee(eid);
		
		return validation;
		}

}
