package com.textcapitalizer;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Application {

	public static void main(String[] args) {
		
		FileDialog fd = new FileDialog((Frame)null, "Select A Text File To Read", FileDialog.LOAD);
	    fd.setVisible(true);
	    String file = fd.getDirectory() + fd.getFile();
	    System.out.println("Location :" + fd.getDirectory() + file);
	    
	    FileManager fileManager = new FileManager();
	    
	    try
	    {
	    	fileManager.readFile(file);
	    }
	    catch(IOException ioException) {
	    	ioException.printStackTrace();
	    }
	    
	    
	}

}
