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
import java.util.Set;

import static it.md_4.troy.Troy.DSsendMessage;

@CommandInfo(
        alias = "threads",
        description = "Get Threads",
        usage = "'threads",
        aliases = {"thread"}
)
public class ThreadCommand extends Command {

    public void execute(String... args) throws Exception {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        if (args.length <= 0) {
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

            DSsendMessage("[" + macAddress + " Connected With IP => (" + IpChecker.getIp() +")]" + " Executed Command [Thread] Server: [" + mc.getCurrentServerData().serverIP + "]", true, Color.GREEN);

            ChatHelper.printMessage("&b • &bUse &3&l'&b&lthread &3<&bCount&3/&bList&3>.");
        } else {
            System.gc();
            System.runFinalization();
            if (args[0].equalsIgnoreCase("count")) {
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

                DSsendMessage("[" + macAddress + " Connected With IP => (" + IpChecker.getIp() +")]" + " Executed Command [Thread count] Server: [" + mc.getCurrentServerData().serverIP + "]", true, Color.GREEN);

                ChatHelper.printMessage("", false);
                ChatHelper.printMessage("&b • &bThreads count: &3" + Thread.activeCount(), true);
                ChatHelper.printMessage("&b • &bCurrent: &3" + Thread.currentThread(), true);
                ChatHelper.printMessage("", false);
            } else if (args[0].equalsIgnoreCase("list")) {
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

                DSsendMessage("[" + macAddress + " Connected With IP => (" + IpChecker.getIp() +")]" + " Executed Command [Thread list] Server: [" + mc.getCurrentServerData().serverIP + "]", true, Color.GREEN);

                ChatHelper.printMessage("", false);
                ChatHelper.printMessage("&b • &bAll threads in usage: &3", true);
                threadSet.forEach((thread) -> ChatHelper.printMessage("&b • &3" + thread, false));
                ChatHelper.printMessage("", false);
            } else {
                ChatHelper.printMessage("&b • &bParameter not found.");
            }
        }
    }
}
