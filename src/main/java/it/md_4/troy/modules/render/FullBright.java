package it.md_4.troy.modules.render;

import it.md_4.troy.modules.Module;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class FullBright extends Module {
    public FullBright() {
        super("FullBright", Keyboard.KEY_B, Category.RENDER);
    }

    public void onEnable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 100.0F;
    }

    public void onDisable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0F;
    }

    @Override
    public void onUpdate() {

    }
}
