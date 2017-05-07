package org.comutu.xero.commands;

import com.xero.model.Report;
import org.comutu.xero.api.BankAccountApi;
import org.comutu.xero.api.CliException;
import org.comutu.xero.output.ReportPrinter;
import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.cl.Option;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandException;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;

import java.io.IOException;

/**
 * Created by johara on 07/05/17.
 */
@CommandDefinition(name = "statement", description = "retrieve bank account statement")
public class StatementCommand implements Command {

   @Option(shortName = 'n', description = "Id of account to retrieve", required = true)
   private String number;

   @Override
   public CommandResult execute(CommandInvocation invocation) throws CommandException {

      Report report = null;
      try {


         report = BankAccountApi.getBankStatement(number);


         ReportPrinter.printReport(report);
         //TODO: outputter
//         System.out.println("Number of statement lines: " + report.getRows().getRow().size());

         return CommandResult.SUCCESS;

      } catch (IOException e) {
         throw new CommandException( "Command Exception occured", e );
      } catch (CliException e) {
         System.out.println("Cli exception occured: " + e.getMessage());
         return CommandResult.FAILURE;
      }

   }


}
