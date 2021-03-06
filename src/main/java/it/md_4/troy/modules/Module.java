package it.md_4.troy.modules;

import it.md_4.troy.modules.events.Event;
import net.minecraft.client.Minecraft;

public abstract class Module {

    public String name;
    public boolean toggled;
    public int keyCode;
    public Category category;
    public Minecraft mc = Minecraft.getMinecraft();

    public Module(String name, int key, Category c){
        this.name = name;
        this.keyCode = key;
        this.category = c;
    }

    public boolean isEnabled() {
        return  toggled;
    }

    public int getKey() {
        return keyCode;
    }

    public void onEvent(Event e){

    }

    public void toggle() {
        toggled = !toggled;
        if(toggled){
            onEnable();
        } else {
            onDisable();
        }
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public abstract void onUpdate();

    public enum Category {
        CPMO("C.P.M.O"),
        EXPLOIT("Malicious"),
        PLAYER("User"),
        COSMETICS("Cosmetics"),
        EXPLOITS("Exploit");

        public String name;
        public int moduleIndex;

        Category(String name){
            this.name = name;
        }
    }


}
