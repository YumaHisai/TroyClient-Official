package com.md_4.troy.modules.movement;

import com.md_4.troy.modules.Module;
import com.md_4.troy.modules.events.Event;
import com.md_4.troy.modules.events.listeners.EventUpdate;
import org.lwjgl.input.Keyboard;

public class Fly extends Module {
    public Fly() {
        super("Fly", Keyboard.KEY_G, Category.MOVEMENT);
    }

    public void onDisable() {
        mc.thePlayer.capabilities.isFlying = false;
    }

    public void onEvent(Event e){
        if(e instanceof EventUpdate){
            if(e.isPre()){
                mc.thePlayer.capabilities.isFlying = true;
            }
        }
    }
}
