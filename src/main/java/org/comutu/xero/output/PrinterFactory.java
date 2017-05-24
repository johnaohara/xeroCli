package org.comutu.xero.output;

/**
 * Created by johara on 15/05/17.
 */
public class PrinterFactory {

   public static Printer buildPrinter(PrinterType printerType) {
      switch (printerType) {
         case CSV:
            return new CsvReportPrinter();
         case TERMINAL:
            return new ReportPrinter();
         default:
            return null;
      }
   }

}
