package it.md_4.troy.command.bypass;

import it.md_4.troy.command.bypass.argument.Argument;
import it.md_4.troy.command.bypass.argument.ArgumentParser;
import it.md_4.troy.exception.BypassException;
import it.md_4.troy.helper.LazySupplier;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.Validate;

import java.util.function.Supplier;



public abstract class Bypass<T> {

  protected static final Minecraft mc = Minecraft.getMinecraft();

  private final String name;
  private final BypassType type;
  private final String usage;
  private final String description;
  private final Argument[] arguments;
  private LazySupplier<T> bypassSource;

  public Bypass(Argument... arguments) {
    BypassInfo bypassInfo = this.getClass().getAnnotation(BypassInfo.class);
    Validate.notNull(bypassInfo, "CONFUSED ANNOTATION EXCEPTION");

    this.name = bypassInfo.name();
    this.type = bypassInfo.type();
    this.description = bypassInfo.description();

    this.arguments = arguments;
    this.usage = (bypassInfo.usage().isEmpty() ?
            ArgumentParser.createUsage(Bypass.this) : bypassInfo.usage());
  }

  public Bypass(Supplier<T> bypassSource, Argument... arguments) {
    this(arguments);
    this.bypassSource = new LazySupplier<>(bypassSource);
  }

  public abstract void execute(Object... args) throws BypassException;

  public final LazySupplier<T> bypassSource() {
    return bypassSource;
  }

  public String getName() {
    return name;
  }

  public BypassType getType() {
    return type;
  }

  public String getUsage() {
    return usage;
  }

  public String getDescription() {
    return description;
  }

  public Argument[] getArguments() {
    return arguments;
  }
}
