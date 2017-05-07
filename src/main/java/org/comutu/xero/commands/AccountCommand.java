package org.comutu.xero.commands;

import com.xero.model.Account;
import org.comutu.xero.api.AccountsApi;
import org.comutu.xero.api.CliException;
import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.cl.Option;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandException;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;

import java.io.IOException;
import java.util.List;

/**
 * Command to retrieve all accounts data
 */
@CommandDefinition(name = "account", description = "retrieve account information")
public class AccountCommand implements Command {

   @Option(shortName = 'n', description = "Id of account to retrieve", required = true)
   private String number;


   @Override
   public CommandResult execute(CommandInvocation invocation) throws CommandException {

      Account account = null;
      try {

         account = AccountsApi.getAccountDetails(number);

         //TODO: outputter
         System.out.println(account.getBankAccountNumber());

         return CommandResult.SUCCESS;

      } catch (IOException e) {
         throw new CommandException( "Command Exception occured", e );
      } catch (CliException e) {
         System.out.println("Cli exception occured: " + e.getMessage());
         return CommandResult.FAILURE;
      }

   }

}
