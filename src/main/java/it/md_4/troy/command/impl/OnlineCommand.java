package it.md_4.troy.command.impl;

import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.exception.CommandException;
import it.md_4.troy.helper.ChatHelper;
import it.md_4.troy.helper.PlayerHelper;

@CommandInfo(
    alias = "online",
    description = "Get List Of Players",
    usage = "'online [method]",
    aliases = {"players", "realplayers"}
)
public class OnlineCommand extends Command {

  @Override
  public void execute(String... args) throws CommandException {

    if (args.length == 0) {
      Type type = args.length > 0 ? Type.valueOf(args[0].toUpperCase()) : Type.PLAYER_DATA;
      int onlinePlayers = -1;

      switch (type) {
        case PLAYER_DATA:
          onlinePlayers = PlayerHelper.getOnlinePlayer().size();
          break;
        case TAB_COMPLETE:
          break;
      }

      ChatHelper.printMessage("&b • &bOnline players: &3" + onlinePlayers);
    } else if (args.length > 1) {
      ChatHelper.printMessage("&b • &bParameter not found.");
    }
  }





  enum Type {
    TAB_COMPLETE, PLAYER_DATA
  }
}
