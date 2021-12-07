package it.md_4.troy.command;

import java.util.Arrays;
import java.util.List;

import it.md_4.troy.exception.CommandException;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.Validate;



public abstract class Command {
  protected static final Minecraft mc = Minecraft.getMinecraft();
  private final String alias;
  private final String usage;
  private final List<String> aliases;

  public Command() {
    CommandInfo commandInfo = this.getClass().getDeclaredAnnotation(CommandInfo.class);
    Validate.notNull(commandInfo, "CONFUSED ANNOTATION EXCEPTION");
    this.alias = commandInfo.alias();
    this.usage = commandInfo.usage();
    this.aliases = Arrays.asList(commandInfo.aliases());
  }

  public abstract void execute(String... var1) throws CommandException;

  public boolean is(String string) {
    return string.equalsIgnoreCase(this.alias) || this.aliases.stream().anyMatch((stream) -> stream.equalsIgnoreCase(string));
  }

  public String getAlias() {
    return this.alias;
  }

  public String getUsage() {
    return this.usage;
  }
}