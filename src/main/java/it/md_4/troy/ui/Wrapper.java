// 
// Decompiled by Procyon v0.5.36
// 

package it.md_4.troy.ui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.network.Packet;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;

public class Wrapper
{
    private static Minecraft mc;
    static ArrayList<Packet> packets;
    
    static {
        Wrapper.mc = Minecraft.getMinecraft();
        Wrapper.packets = new ArrayList<Packet>();
    }
    
    public static void ParrotChat(final String message) {
        Wrapper.mc.thePlayer.addChatMessage(new ChatComponentText("§a[+] " + message));
    }
    
    public static void Chat(final String message) {
        Wrapper.mc.thePlayer.addChatMessage(new ChatComponentText(message));
    }
    
    public static void sendPacket(final Packet p) {
        Wrapper.mc.getNetHandler().addToSendQueue(p);
    }
    
    public static EntityPlayerSP getPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }
    
    public static FontRenderer fr() {
        return Minecraft.fontRendererObj;
    }
    
    public static void postUpdate() {
        if (Wrapper.mc.getNetHandler() == null) {
            return;
        }
        final ArrayList<Packet> n = new ArrayList<Packet>(Wrapper.packets);
        Wrapper.packets.clear();
        for (final Packet p : n) {
            sendPacket(p);
        }
    }
}
