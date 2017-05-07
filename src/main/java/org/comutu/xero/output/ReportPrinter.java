package org.comutu.xero.output;

import com.xero.model.Report;
import com.xero.model.ReportCell;
import com.xero.model.ReportRow;
import com.xero.model.ReportRowType;
import com.xero.model.ReportRows;

import java.util.List;

/**
 * Created by johara on 07/05/17.
 */
public class ReportPrinter {

   public static void printReport(Report report) {

      ReportRows reportRows = report.getRows();

      ReportRow headerRow = reportRows.getRow().get( 0 );
      ReportRow bodyRow = reportRows.getRow().get( 1 );

      Integer[] sizes = new Integer[headerRow.getCells().getCell().size()];

      getRowSizes( headerRow, sizes );

      bodyRow.getRows().getRow().forEach( (ReportRow reportRow) -> getRowSizes( reportRow, sizes ) );

      printHeader( reportRows.getRow().get( 0 ), sizes );


      reportRows.getRow().get( 1 ).getRows().getRow().forEach( (ReportRow reportRow) -> printHeader( reportRow, sizes ) );

//       printBody(reportRows.getRow().get( 1 ));

   }

//   private static void printBody(ReportRow reportRow) {
//
//   }

   private static void printHeader(ReportRow reportRow, Integer[] sizes) {

      StringBuilder headerBuild = new StringBuilder();

//      reportRow.getCells().getCell().forEach( (ReportCell cell) -> headerBuild.append( cell.getValue() + " | " ) );

      List<ReportCell> reportCells = reportRow.getCells().getCell();

      for (int counter = 0; counter < reportCells.size(); counter++) {
         
         headerBuild.append( String.format( "%1$-" + sizes[counter] + "s", reportCells.get( counter ).getValue() == null ? "" : reportCells.get( counter ).getValue() ) );
         headerBuild.append(" |");

      }

      System.out.println( headerBuild.toString() );
   }

   private static void getRowSizes(ReportRow reportRow, Integer[] sizes) {

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
