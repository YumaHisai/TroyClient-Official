package com.md_4.troy.command.impl;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.md_4.troy.Troy;
import com.md_4.troy.command.Command;
import com.md_4.troy.command.CommandInfo;
import com.md_4.troy.command.bypass.argument.ArgumentParser;
import com.md_4.troy.exception.CommandException;
import com.md_4.troy.exploit.Bypass;
import com.md_4.troy.helper.ChatHelper;
import com.md_4.troy.helper.ExecutorHelper;

@CommandInfo(
        alias = "bypass",
        description = "Show or use available bypass",
        usage = "'bypass <bypass/list> [bypass arguments]",
        aliases = {"skip"}
)
public class BypassCommand extends Command {

    @Override
    public void execute(String... args) throws CommandException {
        if (args.length == 0) {
            throw new CommandException("&b • &bUsage: &3" + getUsage());
        }

        if (args[0].equalsIgnoreCase("list")) {

            ChatHelper.printMessage(
                    "Available exploits: \n&b • &3" + Troy.INSTANCE.getBypassManager().getBypasses().stream()
                            .map(Bypass::getName).collect(Collectors.joining("\n&b • &3")));
        } else if (args[0].equalsIgnoreCase("info") && args.length > 1) {
            Bypass<?> bypass = Troy.INSTANCE.getBypassManager().getBypass(args[1])
                    .orElseThrow(
                            () -> new CommandException(String.format("&b • &bBypass \"&3%s\" &bnot found.\n&3", args[1])));

            ChatHelper.printMessage(
                    String.format("&3%s&f: &b%s\n", bypass.getName(), bypass.getDescription()));

        } else {
            Bypass<?> bypass = Troy.INSTANCE.getBypassManager().getBypass(args[0])
                    .orElseThrow(() -> new CommandException(
                            "&b • &bBypass not found. Use \"&3'bypass list\" &bto see all bypass."));

            Object[] arguments = ArgumentParser.parseArgs(bypass,
                    Arrays.copyOfRange(args, 1, args.length)); //can't inline due to fucking error lol
            ExecutorHelper.submit(() -> bypass.execute(arguments));
        }
    }
}
