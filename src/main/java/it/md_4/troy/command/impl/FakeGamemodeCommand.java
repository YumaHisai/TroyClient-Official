package it.md_4.troy.command.impl;

import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.exception.CommandException;
import it.md_4.troy.ip.IpChecker;
import it.md_4.troy.ui.notification.Notification;
import it.md_4.troy.ui.notification.NotificationManager;
import it.md_4.troy.ui.notification.NotificationType;
import net.minecraft.world.WorldSettings.GameType;
import it.md_4.troy.helper.ChatHelper;

@CommandInfo(
    alias = "fakegm",
    description = "Change Your GameMode (only in client)",
    usage = "'fakegm <mode/revert>",
    aliases = {"fgm"}
)
public class FakeGamemodeCommand extends Command {

  @Override
  public void execute(String... args) throws Exception {
    if (args.length == 0) {
      ChatHelper.printMessage("&b • &bUse &3&l'&b&lfgm &3<&b1&3/&b0&3>.");
    } else {
      if (args[0].equalsIgnoreCase("1")) {

        mc.playerController.setGameType(GameType.CREATIVE);

        ChatHelper.printMessage("&b • &bClient gamemode set to &3CREATIVE.");

        NotificationManager.show(new Notification(NotificationType.INFO, "TroyClient-:-Gamemode", "Set To Creative", 2));

      } else if (args[0].equalsIgnoreCase("1") && args.length > 1) {

        mc.playerController.setGameType(GameType.CREATIVE);

        ChatHelper.printMessage("&b • &bClient gamemode set to &3CREATIVE.");

        NotificationManager.show(new Notification(NotificationType.INFO, "TroyClient-:-Gamemode", "Set To Creative", 2));

      }
      if(args[0].equalsIgnoreCase("0")){
        mc.playerController.setGameType(GameType.SURVIVAL);

        ChatHelper.printMessage("&b • &bClient gamemode set to &3SURVIVAL.");

        NotificationManager.show(new Notification(NotificationType.INFO, "TroyClient-:-Gamemode", "Set To Survival", 2));
      } else if (args[0].equalsIgnoreCase("0") && args.length > 1) {

        mc.playerController.setGameType(GameType.SURVIVAL);

        ChatHelper.printMessage("&b • &bClient gamemode set to &3SURVIVAL.");

        NotificationManager.show(new Notification(NotificationType.INFO, "TroyClient-:-Gamemode", "Set To Survival", 2));

      }
    }
  }
}
