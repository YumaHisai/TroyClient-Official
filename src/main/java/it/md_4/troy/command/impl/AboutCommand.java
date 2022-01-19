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
    public void execute(String... args) throws Exception {
        ChatHelper.printMessage("&b • &3TroyClient v1.11.2 v2", false);
        ChatHelper.printMessage("&b • &bDeveloper: &3md_4", false);
        ChatHelper.printMessage("&b • &bDs: &3https://discord.gg/jDhQhSK9KQ", false);
        ChatHelper.printMessage("&b • &bServer PartnerShip: &3soundcraft.it ['about]", false);
    }
}

