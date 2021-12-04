package com.md_4.troy.command.bypass.argument;

public class Argument {

  private final String name;
  private final int index;
  private final Class<?> type;

  public Argument(String name, int index, Class<?> type) {
    this.name = name;
    this.index = index;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public int getIndex() {
    return index;
  }

  public Class<?> getType() {
    return type;
  }
}
