package org.comutu.xero.output;

import java.io.PrintStream;
import java.util.List;

import org.comutu.xero.ParameterTypeException;

/**
 * Created by johara on 07/05/17.
 */
public class ReportsPrinter implements Printer {

   @Override
   public void print(PrintStream printStream, Object... param) throws ParameterTypeException {
      if (param.length != 1 && !(param[0] instanceof List)) {
         throw new ParameterTypeException("");
      }
   }
}
