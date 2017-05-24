package org.comutu.xero.output;

/**
 * Created by johara on 15/05/17.
 */
public enum PrinterType {
   CSV( "CSV" ),
   TERMINAL( "TERMINAL" );

   private String type;

   PrinterType(String type) {
      this.type = type;
   }

   @Override
   public String toString() {
      return type;
   }
}
