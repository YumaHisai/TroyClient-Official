package it.md_4.troy.pvp.mods;

import it.md_4.troy.pvp.hud.HUDManager;
import it.md_4.troy.pvp.mods.impl.ModArmorStatus;

public class ModInstances {


    private static ModArmorStatus modArmorStatus;

    public static void register(HUDManager api){

        modArmorStatus = new ModArmorStatus();
        api.register(modArmorStatus);
    }

}
