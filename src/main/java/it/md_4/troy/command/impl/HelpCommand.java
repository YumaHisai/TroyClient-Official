package it.md_4.troy.command.impl;

import it.md_4.troy.Troy;
import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.exception.CommandException;
import it.md_4.troy.helper.ChatHelper;

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

      return;
    }

    Troy.INSTANCE.getCommandManager().getCommands().stream()
        .filter(command -> !(command instanceof HelpCommand))
        .forEach(command -> ChatHelper.printMessage(
            String.format("&3%s &f- &b%s", command.getAlias(), command.getDescription())));
  }
}
