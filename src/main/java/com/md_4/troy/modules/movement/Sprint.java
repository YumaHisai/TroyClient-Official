package com.md_4.troy.modules.movement;

import com.md_4.troy.modules.Module;
import com.md_4.troy.modules.events.Event;
import com.md_4.troy.modules.events.listeners.EventUpdate;
import org.lwjgl.input.Keyboard;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Keyboard.KEY_N, Category.MOVEMENT);
    }

    public void onEnable() {

    }

    public void onDisable() {
        mc.thePlayer.setSprinting(mc.gameSettings.keyBindSprint.isPressed());
    }

    public void onEvent(Event e){
        if(e instanceof EventUpdate){
            if(e.isPre()){

                if(mc.thePlayer.moveForward > 0 && !mc.thePlayer.isUsingItem() && !mc.thePlayer.isSneaking() && !mc.thePlayer.isCollidedHorizontally)
                    mc.thePlayer.setSprinting(true);
            }
        }
    }
}
