package it.md_4.troy.modules.movement;

import it.md_4.troy.modules.Module;
import it.md_4.troy.modules.events.Event;
import it.md_4.troy.modules.events.listeners.EventUpdate;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

public class NoFall extends Module {
    public NoFall() {
        super("NoFall", Keyboard.KEY_NONE, Category.CPMO);
    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate && e.isPre() && Minecraft.getMinecraft().thePlayer.fallDistance > 2.0F) {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        }

    }

    @Override
    public void onUpdate() {

    }
}
