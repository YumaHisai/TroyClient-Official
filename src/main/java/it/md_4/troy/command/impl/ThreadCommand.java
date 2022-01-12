package it.md_4.troy.command.impl;

import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.helper.ChatHelper;
import java.util.Set;

@CommandInfo(
        alias = "threads",
        description = "Get Threads",
        usage = "'threads",
        aliases = {"thread"}
)
public class ThreadCommand extends Command {

    public void execute(String... args) throws Exception {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        if (args.length <= 0) {
            ChatHelper.printMessage("&b • &bUse &3&l'&b&lthread &3<&bCount&3/&bList&3>.");
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
            } else {
                ChatHelper.printMessage("&b • &bParameter not found.");
            }
        }
    }
}
