package com.md_4.troy.modules.player;

import com.md_4.troy.modules.Module;
import com.md_4.troy.modules.events.Event;
import com.md_4.troy.modules.events.listeners.EventUpdate;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

public class NoFall extends Module {
    public NoFall() {
        super("NoFall", Keyboard.KEY_M, Category.PLAYER);
    }

    public void onEvent(Event e){
        if(e instanceof EventUpdate && e.isPre()){
            if(mc.thePlayer.fallDistance > 2)
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        }
    }

}
