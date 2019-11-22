package start;

import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class SetPrinter {
	   {
	        PrinterJob pjob = PrinterJob.getPrinterJob();
	        PageFormat pf = pjob.defaultPage();
	        pjob.setPrintable(null, pf);

	        if (pjob.printDialog()) {
	          try {
				pjob.print();
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
	    }
	        }

