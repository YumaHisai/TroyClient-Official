package it.md_4.troy.ui;

import java.util.ArrayList;

public class Manager
{
    public static ArrayList<Mod> activeModules;
    
    static {
        Manager.activeModules = new ArrayList<Mod>();
    }
    
    public static ArrayList<Mod> getModules() {
        return Manager.activeModules;
    }
    
    public Mod getModule(final Class<? extends Mod> clazz) {
        for (final Mod mod : getModules()) {
            if (mod.getClass() == clazz) {
                return mod;
            }
        }
        return null;
    }
    
    public Mod getModbyName(final String name) {
        for (final Mod mod : getModules()) {
            if (!mod.getName().equalsIgnoreCase(name)) {
                continue;
            }
            return mod;
        }
        return null;
    }
}
