package org.comutu.xero.commands;

import com.xero.model.Report;
import org.comutu.xero.ParameterTypeException;
import org.comutu.xero.api.report.ProfitLossReportAPI;
import org.comutu.xero.commands.options.ProfitLossFormat;
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
@CommandDefinition(name = "pl", description = "Profit Loss report")
public class ProfitLossReportCommand implements Command {

   @Option(shortName = 's', name = "start", description = "Start Date of Report")
   private String startDate;

   @Option(shortName = 'e', name = "end", description = "End Date of report")
   private String endDate;

   @Option(shortName = 'f', name = "format", description = "Reporting format")
   private ProfitLossFormat format;

   @Override
   public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {

      Report report = null;
      try {

         report = ProfitLossReportAPI.getProfitLoss( startDate, endDate );

         //TODO: dynamic outputter
         new ReportPrinter().print(commandInvocation.getShell().out(), report);

         return CommandResult.SUCCESS;

      } catch (IOException e) {
         throw new CommandException( "Command Exception occured", e );
      } catch (ParameterTypeException e) {
         e.printStackTrace();
      }

      return CommandResult.FAILURE;
   }
}
