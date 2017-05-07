package org.comutu.xero.commands;

import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;

/**
 * Comnmand to exit the shell
 */
@CommandDefinition(name = "exit", description = "exit the program")
public class ExitCommand implements Command {

   @Override
   public CommandResult execute(CommandInvocation invocation) {
      invocation.stop();
      return CommandResult.SUCCESS;
   }

}
