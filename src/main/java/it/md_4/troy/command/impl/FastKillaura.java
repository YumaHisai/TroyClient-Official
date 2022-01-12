package it.md_4.troy.command.impl;

import it.md_4.troy.command.Command;
import it.md_4.troy.command.CommandInfo;
import it.md_4.troy.helper.ChatHelper;
import java.util.ArrayList;

@CommandInfo(
        alias = "fastkillaura",
        description = "Enable FastKillAura - Automatically Rotate To Entity",
        usage = "'fastkillaura",
        aliases = {"fka"}
)
public class FastKillaura extends Command {

    public static ArrayList<String> FastKillaura = new ArrayList<String>();

    @Override
    public void execute(String... args) throws Exception {

        if(FastKillaura.contains("true")){
            FastKillaura.clear();
            FastKillaura.add("false");
            ChatHelper.printMessage("&b • &3FastKillAura Disabled", false);
        } else {
            FastKillaura.clear();
            FastKillaura.add("true");
            ChatHelper.printMessage("&b • &3FastKillAura Enabled", false);
        }
    }
}
