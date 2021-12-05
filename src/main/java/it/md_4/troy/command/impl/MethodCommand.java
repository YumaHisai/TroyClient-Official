package it.md_4.troy.command.impl;

import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.exception.CommandException;
import it.md_4.troy.helper.ChatHelper;

@CommandInfo(
        alias = "method",
        description = "Show avaliable Methods",
        usage = "'method",
        aliases = {"methods"}
)
public class MethodCommand extends Command {
    public void execute(String... args) throws CommandException {
        ChatHelper.printMessage("", false);
        ChatHelper.printMessage("&lGORO &8-> &fSimple Bypass Crasher", true);
        ChatHelper.printMessage("&lRUYU &8-> &fExploitFixer Crasher", true);
        ChatHelper.printMessage("&lONEPACKET &8-> &fOnePacket Crasher", true);
        ChatHelper.printMessage("&lSHOGUN &8-> &fAdvanced Crasher", true);
        ChatHelper.printMessage("&lCHUNKLOADER &8-> &fChunk Crasher", true);
        ChatHelper.printMessage("&lFAWE &8-> &fFawe Exploit", true);
        ChatHelper.printMessage("&lMV &8-> &fMultiverseCore Exploit", true);
        ChatHelper.printMessage("&lPEX &8-> &fPermissionsEx Bypass OP", true);
        ChatHelper.printMessage("", false);
    }
}
