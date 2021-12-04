package com.md_4.troy;

import com.md_4.troy.command.impl.*;
import com.md_4.troy.modules.Module;
import com.md_4.troy.modules.events.Event;
import com.md_4.troy.modules.events.listeners.EventKey;
import com.md_4.troy.modules.movement.*;
import com.md_4.troy.modules.player.*;
import com.md_4.troy.modules.render.*;
import com.md_4.troy.ui.HUD;
import com.md_4.troy.ui.Manager;
import net.arikia.dev.drpc.DiscordRPC;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.md_4.troy.helper.OpenGlHelper;
import org.lwjgl.opengl.Display;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.Minecraft;
import com.md_4.troy.command.bypass.pex.PermissionExBypass;
import com.md_4.troy.exploit.Bypass;
import com.md_4.troy.command.bypass.auth.Auth1;
import com.md_4.troy.exploit.impl.multiverse.MultiverseCore;
import com.md_4.troy.exploit.impl.nbt.Shogun1;
import com.md_4.troy.exploit.impl.nbt.Goro1;
import com.md_4.troy.exploit.impl.nbt.Ruyu1;
import com.md_4.troy.exploit.impl.nbt.OnePacket;
import com.md_4.troy.exploit.impl.other.ChunkLoadExploit;
import com.md_4.troy.exploit.impl.other.FaweExploit;
import com.md_4.troy.exploit.Exploit;
import com.md_4.troy.alt.AltManager;
import com.md_4.troy.rpc.DiscordRichPresenceManager;
import com.md_4.troy.command.bypass.BypassManager;
import com.md_4.troy.exploit.ExploitManager;
import com.md_4.troy.command.CommandManager;

public enum Troy
{
  INSTANCE;

  private final CommandManager commandManager;
  private final ExploitManager exploitManager;
  private final BypassManager bypassManager;
  private final DiscordRichPresenceManager discordRichPresence;
  public static AltManager altManager;
  public static String name;
  public static String version;
  public static String creator;
  public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
  //prt
  public static Manager modmanager;
  public static Troy instace;
  public static HUD HUD;
  //prt

  public static Troy getInstance() {
    return Troy.instace;
  }

  private Troy() {
    this.discordRichPresence = new DiscordRichPresenceManager();
    this.commandManager = new CommandManager(new ExploitCommand(), new BypassCommand(), new HelpCommand(), new OnlineCommand(), new FakeGamemodeCommand(), new AboutCommand(), new MethodCommand(), new ClearChatCommand(), new ThreadCommand());
    this.exploitManager = new ExploitManager((Exploit<?>[])new Exploit[] { new FaweExploit(), new ChunkLoadExploit(), new OnePacket(), new Ruyu1(), new Goro1(), new Shogun1(), new MultiverseCore()});
    this.bypassManager = new BypassManager((Bypass<?>[])new Bypass[] { new PermissionExBypass(), new Auth1() });
    final GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
    gameSettings.gammaSetting += 9999.0f;
    Minecraft.getMinecraft().gameSettings.ofFastRender = true;
  }

  public void setDisplay() throws IOException {
    Troy.HUD = new HUD();
    //SplashProgress.setProgress(1, "Troy - Reading Modules");

    // Modules
    modules.add(new Fly());
    modules.add(new Sprint());
    modules.add(new FullBright());
    modules.add(new NoFall());
    modules.add(new TabGUI());
    // Modules
    Troy.altManager = new AltManager();
    Display.setTitle("TroyClient 1.1.7 | By md_4 & ItzNull_");
    OpenGlHelper.setWindowIcon("https://i.imgur.com/5Peeyxu.png", "https://i.imgur.com/DcjVAVX.png");
  }

  public void shutDown() {
    DiscordRPC.discordShutdown();
  }

  public CommandManager getCommandManager() {
    return this.commandManager;
  }

  public ExploitManager getExploitManager() {
    return this.exploitManager;
  }

  public BypassManager getBypassManager() {
    return this.bypassManager;
  }

  @Deprecated
  public DiscordRichPresenceManager getDiscordRichPresence() {
    return this.discordRichPresence;
  }

  public static void onEvent(Event e){
    for(Module m : modules){
      if(!m.toggled)
        continue;

      m.onEvent(e);
    }
  }

  public static void keyPress(int key){
    Troy.onEvent(new EventKey(key));

    for(Module m : Troy.modules){
      if(m.getKey() == key){
        m.toggle();
      }
    }
  }

  public static List<Module> getModulesByCategory(Module.Category c){
    List<Module> modules = new ArrayList<Module>();

    for(Module m : Troy.modules){
      if(m.category == c)
        modules.add(m);
    }
    return modules;
  }

  static {
    Troy.name = "TroyClient";
    Troy.version = "1.1.7";
    Troy.creator = "md_4 & ItzNull_";
  }
}
