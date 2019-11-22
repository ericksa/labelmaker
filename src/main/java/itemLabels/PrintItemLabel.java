
package itemLabels;

import javax.print.*;
import java.util.Calendar;
import java.util.Date;


public class PrintItemLabel {

	public void ItemLabel(String item, String desc) {
Date date =  Calendar.getInstance().getTime();
		// ZDesigner S4M-203dpi ZPL
		StringBuilder zplPrint = new StringBuilder();
	PrintService printService = PrintServiceLookup.lookupDefaultPrintService();


				zplPrint.append("^XA" + '\r' + '\r' + "^FO420,50^ATR,200,150^FH^FD" +  +  '\r' + '\r' + "^FO150,80^BY6^BCR,250,N,N,N^FD^FS" + '\r' + '\r' + "^XZ");

			
			String zplFinal = zplPrint.toString();

			zplPrint.setLength(0);
			byte[] by = zplFinal.getBytes();

			DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

			Doc doc = new SimpleDoc(by, flavor, null);
			System.out.println(zplFinal);
			DocPrintJob printLabel = printService.createPrintJob();
			try {
				printLabel.print(doc, null);
			} catch (PrintException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}




}
