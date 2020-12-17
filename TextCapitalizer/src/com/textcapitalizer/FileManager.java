package com.textcapitalizer;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {
	
	void readFile(String fileName) throws IOException {

	    
	    FileReader fr = null; 
	    BufferedReader br = null;
	    
	    try {
	    	
			 fr = new FileReader(fileName);
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

	}

}
