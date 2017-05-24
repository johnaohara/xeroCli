package org.comutu.xero.commands;

import com.xero.model.Report;
import org.comutu.xero.api.CliException;
import org.comutu.xero.api.report.ProfitLossReportAPI;
import org.comutu.xero.output.KpiPrinter;
import org.comutu.xero.output.ReportPrinter;
import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.cl.Option;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandException;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by johara on 07/05/17.
 */
@CommandDefinition(name = "kpi", description = "KPI report")
public class KpiReportCommand implements Command {

   @Option(shortName = 'f', name = "from", description = "Start Date of KPI Report")
   private String fromDate;

   @Option(shortName = 't', name = "to", description = "End Date of KPI report")
   private String toDate;

   @Override
   public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {

      Report report = null;
      try {

         report = ProfitLossReportAPI.getProfitLoss(fromDate, toDate);

         Map<String, String> kpis = calcKpis(report);
         //TODO: dynamic outputter
         KpiPrinter.printKpis( kpis, commandInvocation.getShell().out() );

         return CommandResult.SUCCESS;

      } catch (IOException e) {
         throw new CommandException( "Command Exception occured", e );
      } catch (CliException e) {
         throw new CommandException( "Command Exception occured", e );
      }

   }

   private Map<String, String> calcKpis(Report report) throws CliException {

      if (report.getReportType() != "ProfitAndLoss")
         throw new CliException( "Wrong report type to calc kpi's: " + report.getReportType());


      Map<String, String> result = new TreeMap<>(  );


      return result;
   }



}
