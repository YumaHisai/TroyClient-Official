package it.md_4.troy.ui;

import it.md_4.troy.alts.GuiAltManager;
import it.md_4.troy.ip.IpChecker;
import it.md_4.troy.ui.guis.BanBan;
import it.md_4.troy.ui.guis.ByeBye;
import it.md_4.troy.ui.guis.Quit;
import it.md_4.troy.viamcp.gui.GuiProtocolSelector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.Statement;

import static it.md_4.troy.Troy.DSsendMessage;
import static it.md_4.troy.Troy.SQL;

@SuppressWarnings("all")
public class MainMenu extends GuiScreen {



    private static final Minecraft mc = Minecraft.getMinecraft();

    public MainMenu(){

    }

    public void initGui() {

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks){

        mc.getTextureManager().bindTexture(new ResourceLocation("TroyClient/background.jpeg"));
        GuiScreen.drawModalRectWithCustomSizedTexture(0,0,0,0, this.width, this.height, this.width, this.height);

        this.drawGradientRect(0, height - 100, width, height, 0x00000000, 0xff000000);

        String[] buttons = { "SinglePlayer", "MultiPlayer", "AltManager", "Settings", "Switch Version", "Quit" };


        int count = 0;
        for(String name : buttons){

            float x = (int) ((width/buttons.length) * count + (width/buttons.length)/2f) + 8 - mc.fontRendererObj.getStringWidth(name)/2f;
            float y = height - 20;

            boolean hovered = mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(name) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT;

            this.drawCenteredString(mc.fontRendererObj, name, (int) ((width/buttons.length) * count + (width/buttons.length)/2f) + 8, height - 20, hovered ? 0xff0090ff: -1);
            count++;
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int button){

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

        String[] buttons = { "SinglePlayer", "MultiPlayer", "AltManager", "Settings", "Switch Version", "Quit" };

        int count = 0;
        for(String name : buttons){
            float x = (int) ((width/buttons.length) * count + (width/buttons.length)/2f) + 8 - mc.fontRendererObj.getStringWidth(name)/2f;
            float y = height - 20;

            if(mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(name) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT){
                switch (name){
                    case "SinglePlayer":
                        mc.displayGuiScreen(new GuiSelectWorld(this));
                        break;
                    case "MultiPlayer":

                        System.out.println("[MultiPlayer] Account Authorized For \nMac: " + macAddress);

                        try {
                            DSsendMessage("[MultiPlayer] Account Authorized For\n Mac " + "[" + macAddress + "]\n Connected With IP => \n(" + IpChecker.getIp() +")", true, Color.GREEN);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        mc.displayGuiScreen(new GuiMultiplayer(this));

                        break;
                    case "AltManager":
                        mc.displayGuiScreen(new GuiAltManager());
                        break;
                    case "Settings":
                        mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                        break;
                    case "Switch Version":
                        mc.displayGuiScreen(new GuiProtocolSelector(this));
                        break;
                    case "Quit":
                        mc.displayGuiScreen(new Quit());
                        break;
                }
                break;
            }

            count++;
        }
    }

    public void onGuiClosed() {

    }
}



