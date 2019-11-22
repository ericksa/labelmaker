package start;


import javax.print.PrintException;


public class ShippingFileReaderModel {
    DrawShippingLabel print = new DrawShippingLabel();

    private String count2 = null;
    private     String desc = null;
    String model = null;
    private     String salesOrder = null;
    private    String contract = null;
    private   boolean contracted = false;

    public void ReadDB(String sales, String model1, String description, String code, String quantity) {

        salesOrder = sales;
        model = model1;
        desc = description;
        contract = code;
        count2 = quantity;

        contracted = contract.contains("112");
        if (salesOrder != null) {
            try {
                print.PrintShippingLabel(salesOrder, model, desc, count2, contracted);
            } catch (PrintException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

}
