package itemLabels;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ModifyInfo {

	PrintItemLabel print = new PrintItemLabel();
	String item = null;
	String desc = null;
	String date = null;

	

	public void ReadFile(String theFile) {

	
		    // pass the path to the file as a parameter 
		    File file = 
		      new File(theFile); 
		    Scanner sc = null;
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} 
			sc.nextLine();
		    while (sc.hasNextLine()) {
		    String s = sc.nextLine();
		    String[] words=s.split("\t"); 
	 
		    	item = words[0];
		    	desc = words[1];
		    	date = words[2];

		  //   System.out.println("how big is my array?  " + words.length);


		    	
		    

		  } }
	}

	
