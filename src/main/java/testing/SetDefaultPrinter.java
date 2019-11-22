package testing;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;

public class SetDefaultPrinter {
	


	  public String getDefaultPrinter() {
		    ImageIcon img = new ImageIcon("MT LOGO.JPG");
		    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		    
		    String printerList[]=new String[printServices.length];

		    for (int i=0;i<printerList.length;i++) printerList[i]=printServices[i].toString();
		    
		
		    String input = (String) JOptionPane.showInputDialog(null, "Select Printer",
		        "Set Default Printer", JOptionPane.QUESTION_MESSAGE, img, printerList, printerList[1]); 
		    System.out.println(input);

	
		    new DefaultPrinter().setPrinter(input);

		    return input;
		  }
}