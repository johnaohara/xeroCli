package org.comutu.xero.output;


import org.comutu.xero.ParameterTypeException;

import java.io.PrintStream;

/**
 * Created by johara on 15/05/17.
 */
public interface Printer {
   void print(PrintStream printStream, Object... param) throws ParameterTypeException;
}
