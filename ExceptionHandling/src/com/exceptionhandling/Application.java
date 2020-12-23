package com.exceptionhandling;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Application {

	public static void main(String[] args) {
		//Excel file should open is in the file folder (Salary.xs)
				FileDialog fileDialog = new FileDialog((Frame)null, "Select A Text File To Read", FileDialog.LOAD);
				fileDialog.setVisible(true);
			    String file = fileDialog.getDirectory() + fileDialog.getFile();
			    System.out.println("Location :" + fileDialog.getDirectory() + file);
				
			    FileManager fileManager = new FileManager();
			    
			    try {
			    	fileManager.readFile(file);
				}
			    catch(ArithmeticException arithmeticException) {
					
			    	arithmeticException.printStackTrace();
				}
			    
			    catch (FileNotFoundException fileNotFoundException) {
			    	
			    	fileNotFoundException.printStackTrace();
				}
			    catch(IOException ioException) {
			    	
			    	ioException.printStackTrace();
			    }
			    
			    finally
			    {
			    	fileDialog.dispose();
			    }

	}

}
