package com.md_4.troy.command.impl;

import com.md_4.troy.command.Command;
import com.md_4.troy.command.CommandInfo;
import com.md_4.troy.exception.CommandException;
import com.md_4.troy.helper.ChatHelper;
import net.minecraft.client.Minecraft;

import java.util.Set;

@CommandInfo(
        alias = "threads",
        description = "Get Threads",
        usage = "'threads",
        aliases = {"thread"}
)
public class ThreadCommand extends Command {

    public void execute(String... args) throws CommandException {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        if (args.length <= 0) {
            throw new CommandException("&b • &3Correct usage: &b/&3threads &b[&3list&b/&3count&b]");
        } else {
            System.gc();
            System.runFinalization();
            if (args[0].equalsIgnoreCase("count")) {
                ChatHelper.printMessage("", false);
                ChatHelper.printMessage("&b • &bThreads count: &3" + Thread.activeCount(), true);
                ChatHelper.printMessage("&b • &bCurrent: &3" + Thread.currentThread(), true);
                ChatHelper.printMessage("", false);
            } else if (args[0].equalsIgnoreCase("list")) {
                ChatHelper.printMessage("", false);
                ChatHelper.printMessage("&b • &bAll threads in usage: &3", true);
                threadSet.forEach((thread) -> ChatHelper.printMessage("&b • &3" + thread, false));
                ChatHelper.printMessage("", false);
            }

        }
    }
}
