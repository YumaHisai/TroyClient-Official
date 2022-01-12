package it.md_4.troy.command.impl;

import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.helper.ChatHelper;

@CommandInfo(
        alias = "method",
        description = "Show avaliable Methods",
        usage = "'method",
        aliases = {"methods"}
)
public class MethodCommand extends Command {
    public void execute(String... args) throws Exception {
        ChatHelper.printMessage("&b&lGORO &3-> &bStrong Bypass Crasher", true);
        ChatHelper.printMessage("&b&lRUYU &3-> &bExploitFixer Crasher", true);
        ChatHelper.printMessage("&b&lONEPACKET &3-> &bOnePacket Crasher", true);
        ChatHelper.printMessage("&b&lSHOGUN &3-> &bAdvanced Crasher", true);
        ChatHelper.printMessage("&b&lCHUNKLOADER &3-> &bChunk Crasher", true);
        ChatHelper.printMessage("&b&lFAWE &3-> &bFawe Exploit", true);
        ChatHelper.printMessage("&b&lMV &3-> &bMultiverseCore Exploit", true);
        ChatHelper.printMessage("&b&lPEX &3-> &bPermissionsEx Bypass OP", true);
        ChatHelper.printMessage("&b&lXAUTH &3-> &bAuth Bypass", true);
    }
}
