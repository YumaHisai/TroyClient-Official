// 
// Decompiled by Procyon v0.5.36
// 

package it.md_4.troy.ui;

import it.md_4.troy.com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;

public class Mod
{
    private String name;
    private Category category;
    public int animation;
    protected boolean state;
    public static Minecraft mc;
    
    static {
        Mod.mc = Minecraft.getMinecraft();
    }
    
    public Mod(final String name, final Category category) {
        this.name = name;
        this.category = category;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public boolean getState() {
        return this.state;
    }
    
    public void setState(final boolean state) {
        this.onToggle();
        if (state) {
            this.onEnable();
            EventManager.register(this);
            this.state = true;
            this.animation = 0;
        }
        else {
            this.onDisable();
            EventManager.unregister(this);
            this.state = false;
            this.animation = 0;
        }
    }
    
    public void toggleModule() {
        this.setState(!this.getState());
    }
    
    public void onToggle() {
        this.animation = 0;
    }
    
    public void onEnable() {
        EventManager.register(this);
        this.animation = 0;
    }
    
    public void onDisable() {
        EventManager.unregister(this);
        this.animation = 0;
    }
    
    public void onUpdate() {
        this.onDisable();
        EventManager.unregister(this);
        this.state = false;
    }
    
    public final boolean isCategory(final Category s) {
        return s == this.category;
    }
    
    public void onReceivedMessage(final String message) {
    }
}
