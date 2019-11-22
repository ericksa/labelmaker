package testing;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import java.util.ArrayList;
import java.util.List;

public class PrintLabels {


	public void PrintingLabels(String zplFinal, PrintService printService) {
		// TODO Auto-generated method stub
		{

            byte[] by = zplFinal.getBytes();

            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

            Doc doc = new SimpleDoc(by, flavor, null);
            System.out.println(zplFinal);
            DocPrintJob printLabel = printService.createPrintJob();
 

		      DocPrintJob job=printLabel;
		      job.addPrintJobListener(new PrintJobAdapter() {
		      public void printDataTransferCompleted(PrintJobEvent event){
		         System.out.println("data transfer complete");
		      }
		      public void printJobNoMoreEvents(PrintJobEvent event){
		            System.out.println("received no more events");
		         }
		      });

		      PrintRequestAttributeSet attrib=new HashPrintRequestAttributeSet();
		      attrib.add(new Copies(1));
		      try {
				job.print(doc, attrib);
			} catch (PrintException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List GetPrinterList() {
	    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
	    List myPrinterList = new ArrayList();
	    for (PrintService printer : printServices) {
	    	myPrinterList.add(printer);
	     }
	    
	    return myPrinterList;}
	}
