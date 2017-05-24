package org.comutu.xero.commands;

import com.xero.model.Report;
import org.comutu.xero.ParameterTypeException;
import org.comutu.xero.api.BankAccountApi;
import org.comutu.xero.api.CliException;
import org.comutu.xero.output.Printer;
import org.comutu.xero.output.PrinterFactory;
import org.comutu.xero.output.PrinterType;
import org.comutu.xero.output.ReportPrinter;
import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.cl.Option;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandException;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by johara on 07/05/17.
 */
@CommandDefinition(name = "statement", description = "retrieve bank account statement")
public class StatementCommand implements Command {

   private static final String DEFAULT_PRINTER = "terminal";

   @Option(shortName = 'n', description = "Id of account to retrieve", required = true)
   private String number;

   @Option(shortName = 'f', name = "from", description = "Start Date of Report")
   private String fromDate;

   @Option(shortName = 't', name = "to", description = "End Date of report")
   private String toDate;

   @Option(shortName = 'o', name = "output", description = "Output file")
   private String outputFile;

   @Option(shortName = 'p', name = "printer", description = "Output printer type")
   private String printer;


   @Override
   public CommandResult execute(CommandInvocation invocation) throws CommandException {

      Report report = null;
      try {

         report = BankAccountApi.getBankStatement( number, fromDate, toDate );

         PrintStream printStream;
         if ( outputFile == null ) {
            printStream = invocation.getShell().out();
         } else {
            printStream = new PrintStream( new FileOutputStream( outputFile, false ) );
         }

         Printer outputPrinter = PrinterFactory.buildPrinter( PrinterType.valueOf( (printer != null ? printer : DEFAULT_PRINTER ).toUpperCase() ));

         outputPrinter.print( printStream, report );

         return CommandResult.SUCCESS;

      } catch (IOException e) {
         throw new CommandException( "Command Exception occured", e );
      } catch (CliException e) {
         System.out.println( "Cli exception occured: " + e.getMessage() );
      } catch (ParameterTypeException e) {
         e.printStackTrace();
      }

      return CommandResult.FAILURE;

   }


}
