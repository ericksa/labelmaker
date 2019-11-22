package start;


import javax.print.PrintException;
import java.sql.Date;
import java.text.SimpleDateFormat;

class ProductLabelModel {
    DrawProductLabel print = new DrawProductLabel();
    private boolean contracted = false;

    public void ReadDB(String customer, String sales, String model, String description, String quantity, Date date, String code) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        String dateFix;
        if (date == null) dateFix = "0000-00-00";
        else {
            dateFix = dateFormat.format(date);
        }

        if (code.contains("112")) contracted = true;
        if (sales != null) try {
            print.PrintProductLabel(customer, sales, model, description, quantity, dateFix, contracted);
        } catch (PrintException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
