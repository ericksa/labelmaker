
package locationLabels;

import javax.print.*;



public class PrintLocationLabel {

	public void LocationLabel(String aisle, String row, String tier, boolean tier1) {

		// ZDesigner S4M-203dpi ZPL
		StringBuilder zplPrint = new StringBuilder();
	PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

			if (tier1 == true) {
				zplPrint.append("^XA" + '\r' + '\r' + "^FO440,50^ATR,200,150^FH^FD" + aisle + "-" + row + "-" + tier + "^FS" + '\r' + '\r' + "^FO180,80^BY6^BCR,250,N,N,N^FD" + aisle + "-" + row + "-" + tier
			+'\r'+ '\r' 
			+ "^FS^FO50,150^GFA,923,923,13,,:O0F,N03FC,N07FE,N0FFE,M01FFE,M03IF,M07FFE,M0IFE,L01IFE,L03IFC,L07IF8,L0JF,K01IFE,K03IFC,K07IF8,K0JF,J01IFE,J03IFC,J07IF8,J0JF,I01IFE,I03IFC,I07IF8,I0JF,001IFE,003IFC,007IF8,00JF,01JF,03gHFE,07gIF,0gJF,1gJF83gJF8:1gJF80gJF,07gIF,03gHFE,01gHF,00JF,007IF8,003IFC,001IFE,I0JF,I07IF8,I03IFC,I01IFE,J0JF,J07IF8,J03IFC,J01IFE,K0JF,K07IF8,K03IFC,K01IFE,L0JF,L07IF8,L03IFC,L01IFE,M0IFE,M07FFE,M03FFE,M01FFE,N0FFE,N07FE,N03FC,O0F,,^FS"
			+'\r'+ '\r' 
			+ "^FS^FO50,750^GFA,923,923,13,,:O0F,N03FC,N07FE,N0FFE,M01FFE,M03IF,M07FFE,M0IFE,L01IFE,L03IFC,L07IF8,L0JF,K01IFE,K03IFC,K07IF8,K0JF,J01IFE,J03IFC,J07IF8,J0JF,I01IFE,I03IFC,I07IF8,I0JF,001IFE,003IFC,007IF8,00JF,01JF,03gHFE,07gIF,0gJF,1gJF83gJF8:1gJF80gJF,07gIF,03gHFE,01gHF,00JF,007IF8,003IFC,001IFE,I0JF,I07IF8,I03IFC,I01IFE,J0JF,J07IF8,J03IFC,J01IFE,K0JF,K07IF8,K03IFC,K01IFE,L0JF,L07IF8,L03IFC,L01IFE,M0IFE,M07FFE,M03FFE,M01FFE,N0FFE,N07FE,N03FC,O0F,,^FS"
			+'\r'+'\r' 
			+ "^FS^FO51,151^GFA,923,923,13,,:O0F,N03FC,N07FE,N0FFE,M01FFE,M03IF,M07FFE,M0IFE,L01IFE,L03IFC,L07IF8,L0JF,K01IFE,K03IFC,K07IF8,K0JF,J01IFE,J03IFC,J07IF8,J0JF,I01IFE,I03IFC,I07IF8,I0JF,001IFE,003IFC,007IF8,00JF,01JF,03gHFE,07gIF,0gJF,1gJF83gJF8:1gJF80gJF,07gIF,03gHFE,01gHF,00JF,007IF8,003IFC,001IFE,I0JF,I07IF8,I03IFC,I01IFE,J0JF,J07IF8,J03IFC,J01IFE,K0JF,K07IF8,K03IFC,K01IFE,L0JF,L07IF8,L03IFC,L01IFE,M0IFE,M07FFE,M03FFE,M01FFE,N0FFE,N07FE,N03FC,O0F,,^FS"
			+'\r'+'\r' 
			+ "^FS^FO51,751^GFA,923,923,13,,:O0F,N03FC,N07FE,N0FFE,M01FFE,M03IF,M07FFE,M0IFE,L01IFE,L03IFC,L07IF8,L0JF,K01IFE,K03IFC,K07IF8,K0JF,J01IFE,J03IFC,J07IF8,J0JF,I01IFE,I03IFC,I07IF8,I0JF,001IFE,003IFC,007IF8,00JF,01JF,03gHFE,07gIF,0gJF,1gJF83gJF8:1gJF80gJF,07gIF,03gHFE,01gHF,00JF,007IF8,003IFC,001IFE,I0JF,I07IF8,I03IFC,I01IFE,J0JF,J07IF8,J03IFC,J01IFE,K0JF,K07IF8,K03IFC,K01IFE,L0JF,L07IF8,L03IFC,L01IFE,M0IFE,M07FFE,M03FFE,M01FFE,N0FFE,N07FE,N03FC,O0F,,^FS"
			+ "^XZ");
				} else {
				zplPrint.append("^XA" + '\r' + '\r' + "^FO420,50^ATR,200,150^FH^FD" + aisle + "-" + row + "-" + tier + "^FS" + '\r' + '\r' + "^FO150,80^BY6^BCR,250,N,N,N^FD" + aisle + "-" + row + "-" + tier + "^FS" + '\r' + '\r' + "^XZ");
			}
			
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
