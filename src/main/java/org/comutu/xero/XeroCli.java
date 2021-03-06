package org.comutu.xero;

import org.comutu.xero.commands.AccountCommand;
import org.comutu.xero.commands.AccountsCommand;
import org.comutu.xero.commands.ExitCommand;
import org.comutu.xero.commands.KpiReportCommand;
import org.comutu.xero.commands.PaymentsCommand;
import org.comutu.xero.commands.ProfitLossReportCommand;
import org.comutu.xero.commands.StatementCommand;
import org.jboss.aesh.console.AeshConsole;
import org.jboss.aesh.console.AeshConsoleBuilder;
import org.jboss.aesh.console.Prompt;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.registry.AeshCommandRegistryBuilder;
import org.jboss.aesh.console.settings.Settings;
import org.jboss.aesh.console.settings.SettingsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Xero Cli
 * ========
 * <p>
 * provides a cli interface to the xero accounting software via the public api
 * https://api.xero.com/api.xro/2.0/
 */
public class XeroCli {

   public static final String PROMPT = "[cli@xero]$ ";

   public static String config = "";

   public static void main(String[] args) {

//      config = args[1];

      Settings settings = new SettingsBuilder().logging( true ).create();

      AeshCommandRegistryBuilder commandRegistryBuilder = new AeshCommandRegistryBuilder()
         .command( AccountCommand.class )
         .command( AccountsCommand.class )
         .command( ExitCommand.class )
         .command( KpiReportCommand.class )
         .command( PaymentsCommand.class )
         .command( ProfitLossReportCommand.class )
         .command( StatementCommand.class )
      ;

      AeshConsole aeshConsole = new AeshConsoleBuilder().settings( settings )
         .prompt( new Prompt( PROMPT ) )
         .commandRegistry( commandRegistryBuilder.create() )
         .create();

      aeshConsole.start();
   }

}
