package it.md_4.troy;

import it.md_4.troy.alts.AltManager;
import it.md_4.troy.exploit.impl.nbt.*;
import it.md_4.troy.modules.Module;
import it.md_4.troy.modules.events.Event;
import it.md_4.troy.modules.events.listeners.EventKey;
import it.md_4.troy.sql.MySQL;
import it.md_4.troy.ui.HUD;
import it.md_4.troy.ui.Manager;
import it.md_4.troy.command.CommandManager;
import it.md_4.troy.command.bypass.auth.Auth1;
import it.md_4.troy.command.bypass.pex.PermissionExBypass;
import it.md_4.troy.command.impl.*;
import it.md_4.troy.exploit.ExploitManager;
import it.md_4.troy.exploit.impl.multiverse.MultiverseCore;
import it.md_4.troy.exploit.impl.other.ChunkLoadExploit;
import it.md_4.troy.exploit.impl.other.FaweExploit;
import it.md_4.troy.helper.OpenGlHelper;
import it.md_4.troy.modules.movement.Fly;
import it.md_4.troy.modules.movement.Sprint;
import it.md_4.troy.modules.movement.NoFall;
import it.md_4.troy.modules.render.FullBright;
import it.md_4.troy.modules.render.TabGUI;
import it.md_4.troy.rpc.DiscordRichPresenceManager;
import it.md_4.troy.viamcp.ViaMCP;
import net.arikia.dev.drpc.DiscordRPC;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.Minecraft;

public enum Troy
{
  INSTANCE;

  private final CommandManager commandManager;
  private final ExploitManager exploitManager;
  private final DiscordRichPresenceManager discordRichPresence;
  public static AltManager altManager;
  public static String name;
  public static String version;
  public static String creator;
  public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();

  public static MySQL SQL;

  //prt
  public static Manager modmanager;
  public static Troy instace;
  public static it.md_4.troy.ui.HUD HUD;
  //prt

  public static Troy getInstance() {
    return Troy.instace;
  }

  private Troy() {
    this.discordRichPresence = new DiscordRichPresenceManager();

    this.commandManager = new CommandManager(
            new ExploitCommand(),
            new HelpCommand(), new
            OnlineCommand(), new
            FakeGamemodeCommand(),
            new AboutCommand(),
            new MethodCommand(),
            new ClearChatCommand(),
            new ThreadCommand()
    );
    this.exploitManager = new ExploitManager(
            new FaweExploit(),
            new ChunkLoadExploit(),
            new OnePacket(),
            new Ruyu1(),
            new Goro1(),
            new Shogun1(),
            new MultiverseCore(),
            new PermissionExBypass(),
            new Auth1(),
            new Tudoku()
    );

    final GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
    gameSettings.gammaSetting += 9999.0f;
    Minecraft.getMinecraft().gameSettings.ofFastRender = true;
  }

  public void setDisplay() throws IOException {

    try
    {
      ViaMCP.getInstance().start();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }


    // MySQL


    SQL = new MySQL();

    try {
      SQL.connect();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    if(SQL.isConnected()){
      System.out.println("Pulling MySQL Requests From TroyClient...");
    }

    // MySQL

    Troy.HUD = new HUD();
    //SplashProgress.setProgress(1, "Troy - Reading Modules");

    Troy.altManager = new AltManager();
    Display.setTitle("TroyClient 1.3.6 | By md_4 & ItzNull_");
    OpenGlHelper.setWindowIcon("https://i.imgur.com/5Peeyxu.png", "https://i.imgur.com/DcjVAVX.png");
    // Modules
    modules.add(new Fly());
    modules.add(new Sprint());
    modules.add(new FullBright());
    modules.add(new NoFall());
    modules.add(new TabGUI());
    // Modules
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


  public static void check(){

    InetAddress localHost = null;
    try {
      localHost = InetAddress.getLocalHost();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    NetworkInterface ni = null;
    try {
      ni = NetworkInterface.getByInetAddress(localHost);
    } catch (SocketException e) {
      e.printStackTrace();
    }
    byte[] hardwareAddress = new byte[0];
    try {
      hardwareAddress = ni.getHardwareAddress();
    } catch (SocketException e) {
      e.printStackTrace();
    }

    String[] hexadecimal = new String[hardwareAddress.length];
    for (int i = 0; i < hardwareAddress.length; i++) {
      hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
    }
    String macAddress = String.join("-", hexadecimal);

      if(
              macAddress.equals("00-E0-4C-36-43-04") // md_4
              ||
              macAddress.equals("00-D8-61-5B-20-0E") // ItzNull_
      )
      {
        System.out.println("Licese Active On " + macAddress);
      } else {
        Minecraft.getMinecraft().shutdown();
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
    Troy.version = "1.3.6";
    Troy.creator = "md_4 & ItzNull_";
  }
}
