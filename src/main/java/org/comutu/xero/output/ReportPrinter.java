package org.comutu.xero.output;

import com.xero.model.Report;
import com.xero.model.ReportCell;
import com.xero.model.ReportRow;
import com.xero.model.ReportRowType;
import com.xero.model.ReportRows;
import org.comutu.xero.ParameterTypeException;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

/**
 * Created by johara on 07/05/17.
 */
public class ReportPrinter implements Printer {

   @Override
   public void print(PrintStream printStream, Object... param) throws ParameterTypeException {
      if (param.length != 1 && !(param[0] instanceof Report)) {
         throw new ParameterTypeException("");
      }
      this.printReport( (Report) param[0], printStream );
   }

   private void printReport(Report report, PrintStream printStream) {

      ReportRows reportRows = report.getRows();

      Integer[] sizes = new Integer[reportRows.getRow().get( 0 ).getCells().getCell().size()];

      //Print report titles
      report.getReportTitles().getReportTitle().forEach( reportTitle -> printStream.println( reportTitle.toString() ) );

      //determine row sizes
      reportRows.getRow().forEach( row -> getRowSizes( row, sizes ) );

      //print report body
      reportRows.getRow().forEach( (ReportRow reportRow) -> printRow( reportRow, sizes, printStream ) );

   }

   public void printReports(List<Report> reports, PrintStream printStream) {



   }

   private void printRow(ReportRow reportRow, Integer[] sizes, PrintStream printStream) {

      StringBuilder rowBuilder = new StringBuilder();

      if ( reportRow.getRowType() == ReportRowType.SECTION ) {
         rowBuilder.append( String.join( "", Collections.nCopies( getTotalRowWidth( sizes ) + sizes.length * 2, "-" ) ) );
         printStream.println( rowBuilder.toString() );
         reportRow.getRows().getRow().forEach( sectionRow -> printRow( sectionRow, sizes, printStream ) );
      }
      if ( reportRow.getCells() != null ) {

         List<ReportCell> reportCells = reportRow.getCells().getCell();

         for (int counter = 0; counter < reportCells.size(); counter++) {

            rowBuilder.append( String.format( "%1$-" + sizes[counter] + "s", reportCells.get( counter ).getValue() == null ? "" : reportCells.get( counter ).getValue() ) );
            rowBuilder.append( " |" );

         }

         printStream.println( rowBuilder.toString() );
      }
   }

   private void getRowSizes(ReportRow reportRow, Integer[] sizes) {

      if ( reportRow.getRowType() == ReportRowType.SECTION ) {
         reportRow.getRows().getRow().forEach( sectionRow -> getRowSizes( sectionRow, sizes ) );
      }
      if ( reportRow.getCells() != null ) {
         List<ReportCell> reportCells = reportRow.getCells().getCell();

         for (int counter = 0; counter < reportCells.size(); counter++) {

            if ( sizes[counter] == null )
               sizes[counter] = 0;

            if ( reportCells.get( counter ).getValue() != null && sizes[counter] < reportCells.get( counter ).getValue().length() ) {
               sizes[counter] = reportCells.get( counter ).getValue().length();
            }

         }
      }
   }

   private int getTotalRowWidth(Integer[] sizes) {

      int total = 0;

      for (int i = 0; i < sizes.length; i++) {
         total = total + sizes[i];
      }

      return total;

   }

}
