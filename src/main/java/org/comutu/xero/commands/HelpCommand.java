package org.comutu.xero.commands;

import org.jboss.aesh.cl.CommandDefinition;
import org.jboss.aesh.console.command.Command;
import org.jboss.aesh.console.command.CommandException;
import org.jboss.aesh.console.command.CommandResult;
import org.jboss.aesh.console.command.invocation.CommandInvocation;

/**
 * Created by johara on 27/05/17.
 */
@CommandDefinition(name = "help", description = "Display help information")
public class HelpCommand implements Command {
   @Override
   public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {

      return null;
   }
}
