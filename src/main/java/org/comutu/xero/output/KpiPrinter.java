package org.comutu.xero.output;

import com.xero.model.Report;
import com.xero.model.ReportCell;
import com.xero.model.ReportRow;
import com.xero.model.ReportRowType;
import com.xero.model.ReportRows;
import org.comutu.xero.api.CliException;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by johara on 07/05/17.
 */
public class KpiPrinter {

   public static void printKpis(Map<String, String> kpis, PrintStream printStream) throws CliException {
      printStream.println ("TODO: Correct logic for printing KPI's.");
      printStream.println ("They should be here.");

      throw new CliException( "Not yet implemented" );
   }

}
