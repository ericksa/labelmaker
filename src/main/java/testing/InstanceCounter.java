package testing;

public class InstanceCounter {

   private static String printer;

   protected static String getPrinter() {
      return printer;
   }

   static void setPrinter(String defaultPrinter) {
      printer = defaultPrinter;
   }
}