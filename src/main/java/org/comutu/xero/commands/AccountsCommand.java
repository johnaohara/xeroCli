package org.comutu.xero.commands;

import com.xero.model.Account;
import org.comutu.xero.api.AccountsApi;
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
@CommandDefinition(name = "accounts", description = "list all accounts")
public class AccountsCommand implements Command {

   @Option(shortName = 't', description = "Type of accounts to retrieve")
   private String type;

   @Override
   public CommandResult execute(CommandInvocation invocation) throws CommandException {

      List<Account> accountsList = null;
      try {

         //TODO: api execution builder to correctly determine invocation target
         if ( type == null ) {
            accountsList = AccountsApi.getAllAccountsByType();
         }
         else {
            accountsList = AccountsApi.getAllAccountsByType(type);
         }

      } catch (IOException e) {
         throw new CommandException( "Command Exception occured", e );
      }

      //TODO: dynaic outputter
      accountsList.forEach( account -> invocation.getShell().out().println( account.getCode() + ": " + account.getName() ) );

      return CommandResult.SUCCESS;
   }

}
