package com.md_4.troy.command.impl;

import com.md_4.troy.Troy;
import com.md_4.troy.command.Command;
import com.md_4.troy.command.CommandInfo;
import com.md_4.troy.exception.CommandException;
import com.md_4.troy.helper.ChatHelper;

@CommandInfo(
    alias = "help"
)
public class HelpCommand extends Command {

  @Override
  public void execute(String... args) throws CommandException {
    if (args.length > 0) {
      ChatHelper.printMessage("\n", false);
      Command command = Troy.INSTANCE.getCommandManager().getCommand(args[0])
          .orElseThrow(
              () -> new CommandException(String.format("&b • &bCommand \"&3%s\" &bnot found.\n&3", args[0])));

      ChatHelper
          .printMessage(String.format("&3%s&f: &d%s\n", command.getAlias(), command.getUsage()));

      /*Niko.INSTANCE.getCommandManager().getCommand(args[0])
          .ifPresentOrElse(command -> ChatHelper.printMessage(
              String.format("&5%s&f: &d%s\n", command.getAlias(), command.getUsage())),
              () -> ChatHelper.printMessage(String.format("Command \"%s\" not found.\n", args[0])));*/
      return;
    }

    Troy.INSTANCE.getCommandManager().getCommands().stream()
        .filter(command -> !(command instanceof HelpCommand))
        .forEach(command -> ChatHelper.printMessage(
            String.format("&3%s &f- &b%s", command.getAlias(), command.getDescription())));
  }
}
