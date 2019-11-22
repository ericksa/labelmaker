package itemLabels;

import javax.print.PrintException;

/**
 * Hello world!
 *
 */
public class MainPrintItems {
	public static void main(String[] args) throws PrintException {
		// LocationLabel locationLabel = new LocationLabel();
		ModifyInfo read = new ModifyInfo();

		read.ReadFile("items.txt");
		  System.out.println("The file was found in the home directory");
		

	}
}
