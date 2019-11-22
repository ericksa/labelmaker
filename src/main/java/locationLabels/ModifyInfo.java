package locationLabels;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class ModifyInfo {

	PrintLocationLabel print = new PrintLocationLabel();
	String aisle = null;
	String row = null;
	String tier = null;
	boolean tier1 = false;
	

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
	 
		    	aisle = words[0];
		    	row = words[1];
		    	tier = words[2];

		  //   System.out.println("how big is my array?  " + words.length);
		     if (Integer.parseInt(tier) == 1) {
		    	 tier1 = true;
		     } else tier1 = false;
				   print.LocationLabel(aisle,row,tier,tier1);
		    	
		    

		  } }
	}

	
