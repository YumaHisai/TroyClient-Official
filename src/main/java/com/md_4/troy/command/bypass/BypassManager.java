package com.md_4.troy.command.bypass;

import com.md_4.troy.exploit.Bypass;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BypassManager {

  private final List<Bypass<?>> bypasses;

  public BypassManager(Bypass<?>... bypasses) {
    this.bypasses = Arrays.asList(bypasses);
  }

  public Optional<Bypass<?>> getBypass(String name) {
    return bypasses.stream()
        .filter(bypass -> bypass.getName().equalsIgnoreCase(name))
        .findFirst();
  }


  public Optional<Bypass<?>> getBypass(String name, BypassType type) {
    return bypasses.stream()
            .filter(bypass -> bypass.getName().equalsIgnoreCase(name))
            .filter(bypass -> bypass.getType() == type)
            .findFirst();
  }

  public void registerBypass(Bypass<?> bypass) {
    bypasses.add(bypass);
  }

  public void registerBypasses(Bypass<?>... bypasses) {
    this.bypasses.addAll(Arrays.asList(bypasses));
  }

  public void unregisterBypass(Bypass<?> bypass) {
    bypasses.remove(bypass);
  }

  public void unregisterBypasses(Bypass<?>... bypasses) {
    this.bypasses.removeAll(Arrays.asList(bypasses));
  }

  public List<Bypass<?>> getBypasses() {
    return bypasses;
  }
}
