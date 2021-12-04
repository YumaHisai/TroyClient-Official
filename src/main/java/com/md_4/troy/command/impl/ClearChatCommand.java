package com.md_4.troy.command.impl;

import com.md_4.troy.command.Command;
import com.md_4.troy.command.CommandInfo;
import com.md_4.troy.exception.CommandException;
import com.md_4.troy.helper.ChatHelper;
import net.minecraft.client.Minecraft;

@CommandInfo(
        alias = "clearchat",
        description = "Clear Game Chat",
        usage = "'clearchat",
        aliases = {"cc"}
)
public class ClearChatCommand extends Command {

    public void execute(String... args) throws CommandException {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
        ChatHelper.printMessage("&b • &3Chat Cleared!", false);
    }
}

