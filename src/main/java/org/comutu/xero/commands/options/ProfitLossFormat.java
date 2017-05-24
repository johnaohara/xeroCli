package org.comutu.xero.commands.options;

/**
 * Created by johara on 15/05/17.
 */
public enum ProfitLossFormat {

   daily ("daily"),
   weekly ("weekly"),
   annual ("annual"),
   monthly ("monthly"),
   quarterly ("quarterly")
   ;

   private String format;

   ProfitLossFormat(String format) {
      this.format  = format;
   }

}
