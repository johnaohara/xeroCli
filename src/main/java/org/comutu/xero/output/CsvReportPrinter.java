package org.comutu.xero.output;

import com.xero.model.Report;
import com.xero.model.ReportCell;
import com.xero.model.ReportRow;
import com.xero.model.ReportRowType;
import com.xero.model.ReportRows;
import org.comutu.xero.ParameterTypeException;

import java.io.PrintStream;
import java.util.List;

/**
 * Created by johara on 15/05/17.
 */
public class CsvReportPrinter implements Printer {
   @Override
   public void print(PrintStream printStream, Object... param) throws ParameterTypeException {
      if ( param.length != 1 && !(param[0] instanceof Report) ) {
         throw new ParameterTypeException( "" );
      }

      this.printReportCsv( (Report) param[0], printStream );

   }

   private void printReportCsv(Report report, PrintStream printStream) {

      ReportRows reportRows = report.getRows();

      //Print report titles
      report.getReportTitles().getReportTitle().forEach( reportTitle -> printStream.println( reportTitle.toString() ) );

      //print report body
      reportRows.getRow().forEach( (ReportRow reportRow) -> printRow( reportRow, printStream ) );

   }

   private void printRow(ReportRow reportRow, PrintStream printStream) {

      StringBuilder rowBuilder = new StringBuilder();

      if ( reportRow.getRowType() == ReportRowType.SECTION ) {
         reportRow.getRows().getRow().forEach( sectionRow -> printRow( sectionRow, printStream ) );
      }
      if ( reportRow.getCells() != null ) {

         List<ReportCell> reportCells = reportRow.getCells().getCell();

         for (int counter = 0; counter < reportCells.size(); counter++) {

            rowBuilder.append( formatOutput( reportCells.get( counter ).getValue() ) );
            if ( (counter + 1) < reportCells.size() )
               rowBuilder.append( "," );
         }

         printStream.println( rowBuilder.toString() );
      }
   }

   private String formatOutput(String value) {

      return (value == null ? "" : escapeSequence( value.trim() ));
   }

   private String escapeSequence(String trim) {

      if ( trim.matches( ".*,.*" ) )
         return "\"" + trim + "\"";

      return trim;
   }
}
