package com.exceptionhandling;
 import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FileManager {
	
void readFile(String fileName) throws IOException {
		
		System.out.println("Reading  File");
		
	//	FileReader fr = new FileReader(fileName);
		
		FileInputStream fis	= null;
		XSSFWorkbook wb = null;
		XSSFSheet sheet = null;
		
		if(validateFile(fileName)) {
			try {
				
			fis = new FileInputStream(fileName);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheetAt(0);
			 
			String eid = "";
			String name = "";
			double basicSal = 0.0;
			double incrementPerentage = 0.0;
			double netSal = 0.0;
			
			int row = 1;
			
			while(sheet.getRow(row) != null) {
				
				eid = sheet.getRow(row).getCell(0).toString();
				name = sheet.getRow(row).getCell(1).toString();
				
				basicSal = Double.parseDouble(sheet.getRow(row).getCell(2).toString());
			//	System.out.println(basicSal);
			//	basicSal = Double.parseDouble(sheet.getRow(row).getCell(2).toString());
				incrementPerentage = Double.parseDouble(sheet.getRow(row).getCell(3).toString());
			//	System.out.println(incrementPerentage);
				Employee emp = new Employee(eid, name, basicSal, incrementPerentage);
						
				 
			//	System.out.println(emp.validateEmployee(eid));
				netSal = emp.validateEmployee(eid);
				System.out.println("Net Salary For Employee " + name + " = " + netSal);
				
				row++;
			}
			
		}
		finally {
			
			if(fis!=null) {
				fis.close();
			}
			
			
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
