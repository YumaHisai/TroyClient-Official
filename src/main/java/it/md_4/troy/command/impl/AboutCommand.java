package it.md_4.troy.command.impl;

import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.helper.ChatHelper;


@CommandInfo(
        alias = "about",
        description = "Get More Information",
        usage = "'about",
        aliases = {"authors", "infoclient"}
)
public class AboutCommand extends Command {

    @Override
    public void execute(String... args) {
        ChatHelper.printMessage("&b • &3TroyClient v1.0.5 By", false);
        ChatHelper.printMessage("&b • &bDeveloper: &3md_4", false);
        ChatHelper.printMessage("&b • &bMethods: &3ItzNull_", false);
    }
}

