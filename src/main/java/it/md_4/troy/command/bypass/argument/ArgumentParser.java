package it.md_4.troy.command.bypass.argument;

import it.md_4.troy.exception.CommandException;
import it.md_4.troy.command.bypass.Bypass;
import org.apache.commons.lang3.StringUtils;

public class ArgumentParser {

  /*
  Some shitty usage creator, because im forgetting to update annotation usage xd
 */
  public static String createUsage(Bypass<?> bypass) {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder
        .append("'bypass ")
        .append(bypass.getName())
        .append(" ");

    for (Argument argument : bypass.getArguments()) {
      stringBuilder
          .append("<")
          .append(argument.getName())
          .append(">")
          .append(" ");
    }
    return stringBuilder.toString();
  }

  /*
  Yeah i can make it better but who cares
 */
  public static Object[] parseArgs(Bypass<?> bypass, String[] args) throws CommandException {
    if (bypass.getArguments().length > args.length) {
      throw new CommandException("&b • &3Usage: &3" + bypass.getUsage());
    }

    Object[] parsedArgs = new Object[bypass.getArguments().length];
    for (Argument argument : bypass.getArguments()) {
      try {

        String arg = args[argument.getIndex()];
        if (argument.getType() == String[].class) {
          if (argument.getIndex() < (bypass.getArguments().length - 1)) {
            throw new CommandException("&b • &3Array argument should be last.");
          }

          parsedArgs[argument.getIndex()] = StringUtils
              .join(args, " ", argument.getIndex(), args.length);
          break;
        } else if (argument.getType() == Integer.class || argument.getType() == Character.class
            || argument.getType() == Byte.class) {
          parsedArgs[argument.getIndex()] = Integer.parseInt(arg);
        } else if (argument.getType() == Double.class) {
          parsedArgs[argument.getIndex()] = Double.parseDouble(arg);
        } else if (argument.getType() == Float.class) {
          parsedArgs[argument.getIndex()] = Float.parseFloat(arg);
        } else if (argument.getType() == Long.class) {
          parsedArgs[argument.getIndex()] = Long.parseLong(arg);
        } else if (argument.getType() == String.class) {
          parsedArgs[argument.getIndex()] = arg;
        } else {
          parsedArgs[argument.getIndex()] = Boolean.getBoolean(arg);
        }
      } catch (Exception e) {
        e.printStackTrace();
        throw new CommandException(String
            .format("&b • &3Can't parse bypass argument. &8[&7Name: &3%s&7, Index: &3%s&7, Type: &3%s&8]",
                argument.getName(), argument.getIndex(), argument.getType().getSimpleName()));
      }
    }
    return parsedArgs;
  }
}
