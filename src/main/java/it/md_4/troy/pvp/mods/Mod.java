package it.md_4.troy.pvp.mods;

import it.md_4.troy.Troy;
import it.md_4.troy.pvp.events.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod {

    private boolean isEnabled = true;

    public static Minecraft mc;
    public static FontRenderer font;
    public static Troy troy;

    public Mod(){
        this.mc = Minecraft.getMinecraft();
        this.font = this.mc.fontRendererObj;
        this.troy = Troy.INSTANCE;

        setEnabled(isEnabled);
    }

    public void setEnabled(boolean isEnabled){
        this.isEnabled = isEnabled;

        if(isEnabled){
            EventManager.register(this);
        } else {
            EventManager.unregister(this);
        }
    }

    public boolean isEnabled(){
        return isEnabled;
    }

}
