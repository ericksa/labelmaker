package locationLabels;

import javax.print.PrintException;

/**
 * Hello world!
 *
 */
public class MainPrintLocations {
	public static void main(String[] args) throws PrintException {
		// LocationLabel locationLabel = new LocationLabel();
		ModifyInfo read = new ModifyInfo();

		read.ReadFile("locations.txt");
		  System.out.println("The file was found in the home directory");
		

	}
}
