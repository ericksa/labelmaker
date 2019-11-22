package start;


import javax.print.*;

class DrawProductLabel {


    void PrintProductLabel(String custNo, String salesOrder, String model, String description, String count, String date, Boolean contracted)
            throws PrintException {
        // ZDesigner S4M-203dpi ZPL
        StringBuilder zplPrint = new StringBuilder();

        String model1;
        String model2;
        String dash;
        if (description.length() >= 40) {
            model1 = description.substring(0, 40);
            dash = "";
            model2 = description.substring(41);
        } else {
            model1 = description;
            dash = "";
            model2 = "";
        }



        int intCount = Integer.parseInt(count);
        for (int i = 1; i <= intCount; i++) {

            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
            System.out.println(printService.getName());

            zplPrint.append("^XA^FO0,200^GB700,1,3^FS^FO0,450^GB200,600,1^FS^FO0,200^GB200,900,1^FS" +
                    "^FO550,210,3^AER12,20^FH^FDSales Reference Number:^FS" +
                    "^FO551,211,3^AER12,20^FH^FDSales Reference Number:^FS" +
                    "^FO550,810,3^AOR12,30^FH^FD" + salesOrder + "^FS" +
                    "^FO500,210,3^AER12,20^FH^FDCust. Ref. Number:^FS" +
                    "^FO501,211,3^AER12,20^FH^FDCust. Ref. Number:^FS" +
                    "^FO500,810,3^AOR12,30^FH^FD" + custNo + "^FS" +
                    "^FO425,210,3^AER12,20^FH^FDModel #:^FS" +
                    "^FO426,211,3^AER12,20^FH^FDModel #:^FS" +
                    "^FO375,250,3^AOR12,30^FH^FD" + model + "^FS" +
                    "^FO300,210,3^AER12,20^FH^FDModel Description:^FS" +
                    "^FO301,211,3^AER12,20^FH^FDModel Description:^FS" +
                    "^FO250,250,3^AOR12,30^FH^FD" + model1 + dash + "^FS" +
                    "^FO220,250,3^AOR12,30^FH^FD" + model2 + "^FS" +
                    "^FO130,210,3^AER12,20^FH^FDMFD Date:^FS" +
                    "^FO80,210,3^AOR12,25^FH^FD" + date + "^FS" +
                    "^FO160,460^AOR12,30^FH^FDAddress: " + Settings.COMPANY + "^FS" +
                    "^FO161,461^AOR12,30^FH^FDAddress: " + Settings.COMPANY + "^FS" +
                    "^FO120,580^AOR12,30^FH^FD" + Settings.ADDRESS + "^FS\r\n" +
                    "^FO80,580^AOR12,30^FH^FD" + Settings.CITY_STATE_ZIP + "^FS" +
                    "^FO40,460^AOR12,30^FDPh: " + Settings.PHONE + "^FS" +
                    "\r\n" +
                    "^FO10,460^AOR12,25^FD" + Settings.EMAIL + "^FS");

            if (contracted) {
                zplPrint.append("^FX Add Logo" + Settings.PRODUCT_CONTRACT + "^XZ");
            } else {
                zplPrint.append("^FX Add Logo" + Settings.PRODUCT_LOGO + "^XZ");
            }

            String zplFinal = zplPrint.toString();

            zplPrint.setLength(0);
            byte[] by = zplFinal.getBytes();

            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(by, flavor, null);
            System.out.println(zplFinal);
            DocPrintJob printLabel = printService.createPrintJob();
            printLabel.print(doc, null);
        }
    }

}




