package it.md_4.troy.modules.render;

import it.md_4.troy.modules.Module;
import it.md_4.troy.ui.notification.Notification;
import it.md_4.troy.ui.notification.NotificationManager;
import it.md_4.troy.ui.notification.NotificationType;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class FullBright extends Module {
    public FullBright() {
        super("FullBright", Keyboard.KEY_F, Category.CPMO);
    }

    public void onEnable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 100.0F;
        NotificationManager.show(new Notification(NotificationType.INFO, "FlullBright", "FlullBright Enabled", 2));
    }

    public void onDisable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0F;
        NotificationManager.show(new Notification(NotificationType.INFO, "FlullBright", "FlullBright Disabled", 2));
    }

    @Override
    public void onUpdate() {

    }
}
