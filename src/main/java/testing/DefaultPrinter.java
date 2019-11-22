package testing;

public class DefaultPrinter {
	   private static String printer;

	   public static String getPrinter() {
	      return printer;
	   }

	   public void setPrinter(String defaultPrinter) {
	      printer = defaultPrinter;
	   }
	}