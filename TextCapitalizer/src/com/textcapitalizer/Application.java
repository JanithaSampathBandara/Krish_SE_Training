package com.textcapitalizer;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Application {

	public static void main(String[] args) throws IOException {
		FileDialog fd = new FileDialog((Frame)null, "Select A Text File To Read", FileDialog.LOAD);
	    fd.setVisible(true);
	    String file = fd.getDirectory() + fd.getFile();
	    System.out.println("Location :" + fd.getDirectory() + file);
	    
	    FileReader fr = null; 
	    BufferedReader br = null;
	    
	    try {
	    	
			 fr = new FileReader(file);
			 br = new BufferedReader(fr);
			 String line = null;

			while((line = br.readLine()) != null) {

				line = line.toUpperCase();
				System.out.println(line);
				
			}		
			
		} finally {
			
			if(br!=null) {
				br.close();
			}
			if(fr!=null) {
				fr.close();
			}
		}

	    fd.dispose();

	}

}
