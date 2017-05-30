package org.comutu.xero.commands;

import com.xero.model.Payment;
import com.xero.model.Report;
import org.comutu.xero.ParameterTypeException;
import org.comutu.xero.api.AccountsApi;
import org.comutu.xero.api.BankAccountApi;
import org.comutu.xero.api.CliException;
import org.comutu.xero.formatter.PaymentFormatter;
import org.comutu.xero.output.Printer;
import org.comutu.xero.output.PrinterFactory;
import org.comutu.xero.output.PrinterType;
import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.cl.Option;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandException;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by johara on 07/05/17.
 */
@CommandDefinition(name = "payments", description = "Retrieve either one or many payments for invoices and credit notes")
public class PaymentsCommand implements Command {

   private static final String DEFAULT_PRINTER = "terminal";

   @Option(shortName = 'n', description = "Id of account to retrieve")
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

      List<Payment> payments = null;
      try {
         if ( number != null ) {
            payments = AccountsApi.getPayments( number, fromDate, toDate );
         } else {
            payments = AccountsApi.getPayments();
         }
         
         PaymentFormatter paymentFormatter = new PaymentFormatter();

         payments.forEach( payment -> invocation.getShell().out().println( paymentFormatter.format( payment ) ) );

         return CommandResult.SUCCESS;

      } catch (IOException e) {
         invocation.getShell().err().println( "Command Exception occured" );
         e.printStackTrace( invocation.getShell().err() );
         return CommandResult.FAILURE;
      }


   }


}
