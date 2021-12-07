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
    ChatHelper.printMessage("&b • &3&l'&b&lcrash &b• Crasher List");
    ChatHelper.printMessage("&b • &3&l'&b&lypass &b• Bypass List");
    ChatHelper.printMessage("&b • &3&l'&b&lmethod &b• List Of Methods");
    ChatHelper.printMessage("&b • &3&l'&b&lthread &b• Show Threads");
    ChatHelper.printMessage("&b • &3&l'&b&lcc &b• Clear Chat");
    ChatHelper.printMessage("&b • &3&l'&b&lfgm &b• Client GameMode");
    ChatHelper.printMessage("&b • &3&l'&b&labout &b• Client Informations");
    ChatHelper.printMessage("&b • &3&l'&b&lonline &b• Show Online Players");
  }
}