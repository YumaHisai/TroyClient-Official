package it.md_4.troy.modules.movement;

import it.md_4.troy.modules.Module;
import it.md_4.troy.modules.events.Event;
import it.md_4.troy.modules.events.listeners.EventUpdate;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class Fly extends Module {
    public Fly() {
        super("Fly", Keyboard.KEY_F, Category.MOVEMENT);
    }

    public void onDisable() {
        Minecraft.getMinecraft().thePlayer.capabilities.isFlying = false;
    }

    @Override
    public void onUpdate() {

    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate && e.isPre()) {
            Minecraft.getMinecraft().thePlayer.capabilities.isFlying = true;
        }

    }
}

