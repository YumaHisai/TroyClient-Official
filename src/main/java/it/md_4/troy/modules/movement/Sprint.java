package it.md_4.troy.modules.movement;

import it.md_4.troy.modules.Module;
import it.md_4.troy.modules.events.Event;
import it.md_4.troy.modules.events.listeners.EventUpdate;
import it.md_4.troy.ui.notification.Notification;
import it.md_4.troy.ui.notification.NotificationManager;
import it.md_4.troy.ui.notification.NotificationType;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Keyboard.KEY_LCONTROL, Category.CPMO);
    }

    public void onEnable() {
        NotificationManager.show(new Notification(NotificationType.INFO, "Sprint", "Sprint Enabled", 2));
    }

    public void onDisable() {
        Minecraft.getMinecraft().thePlayer.setSprinting(Minecraft.getMinecraft().gameSettings.keyBindSprint.isPressed());
        NotificationManager.show(new Notification(NotificationType.INFO, "Sprint", "Sprint Disabled", 2));
    }

    @Override
    public void onUpdate() {

    }

    public void onEvent(Event e) {
        if (e instanceof EventUpdate && e.isPre() && Minecraft.getMinecraft().thePlayer.moveForward > 0.0F && !Minecraft.getMinecraft().thePlayer.isUsingItem() && !Minecraft.getMinecraft().thePlayer.isSneaking() && !Minecraft.getMinecraft().thePlayer.isCollidedHorizontally) {
            Minecraft.getMinecraft().thePlayer.setSprinting(true);
        }

    }
}
