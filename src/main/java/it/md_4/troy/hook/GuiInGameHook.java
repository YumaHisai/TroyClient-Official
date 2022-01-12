package it.md_4.troy.hook;

import it.md_4.troy.Troy;
import it.md_4.troy.helper.ChatHelper;
import it.md_4.troy.helper.OpenGlHelper;
import it.md_4.troy.helper.TimeHelper;
import it.md_4.troy.holder.Holder;
import it.md_4.troy.modules.Module;
import it.md_4.troy.modules.events.listeners.EventRenderGUI;
import it.md_4.troy.hook.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import java.util.Comparator;


public class GuiInGameHook extends GuiIngame {


  private static final Minecraft mc = Minecraft.getMinecraft();

  public GuiInGameHook(Minecraft mcIn) {
    super(mcIn);
  }

  @Override
  public void renderGameOverlay(float partialTicks) {
    super.renderGameOverlay(partialTicks);

    GL11.glScaled(2.0, 2.0, 2.0);
    mc.fontRendererObj.drawStringWithShadow("§3T§broy", 1, 1, OpenGlHelper.rainbowColor(3000, 1));
    GL11.glScaled(0.5, 0.5, 0.5);

    if (!mc.isSingleplayer()) {
      int x = (int) mc.thePlayer.posX, y = (int) mc.thePlayer.posY, z = (int) mc.thePlayer.posZ;
      long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
      long lastPacketMS = (long) (TimeHelper.getCurrentTime() - Holder.getLastPacketMS());
      if (lastPacketMS > 1000 && Holder.getTPS() > 0.00) //idk xd, imagine doesn't know how to gix -0.00
      {
        Holder.setTPS(Holder.getTPS() - 0.01);
      }

      mc.fontRendererObj.drawStringWithShadow("beta", 54, 10, OpenGlHelper.rainbowColor(3000, 1));

      mc.fontRendererObj
          .drawStringWithShadow(ChatHelper.fix("&bNick: &3" + mc.session.getUsername()), 5, 110,
              0);
      mc.fontRendererObj
          .drawStringWithShadow(ChatHelper.fix("&bIP: &3" + mc.getCurrentServerData().serverIP),
              5, 120, 0);



      if (mc.thePlayer.getClientBrand() != null) {
        String brand = mc.thePlayer.getClientBrand().contains("<- ") ?
            mc.thePlayer.getClientBrand().split(" ")[0] + " -> " + mc.thePlayer.getClientBrand()
                .split("<- ")[1] : mc.thePlayer.getClientBrand().split(" ")[0];
        mc.fontRendererObj.drawStringWithShadow(
            ChatHelper.fix("&bBrand: &3" + brand), 5, 130, 0);
      }

      mc.fontRendererObj
          .drawStringWithShadow(ChatHelper.fix("&bFPS: &3" + Minecraft.debugFPS * 3), 5, 140, 0);

      if (Holder.getTPS() != -1) {
        String tps = String.format((Holder.getTPS() > 15
            ? "&a%.2f" : (Holder.getTPS() > 10
            ? "&e%.2f" : (Holder.getTPS() > 5
            ? "&6%.2f" : "&c%.2f"))), Holder.getTPS());

        mc.fontRendererObj
            .drawStringWithShadow(ChatHelper.fix(String.format("&bTPS: &3%s", tps)), 5, 150, 0);

      }

      if (Holder.getLastPacketMS() != -1) {
        String packetMs = (lastPacketMS < 300
            ? "&a" + lastPacketMS : (lastPacketMS < 600
            ? "&e" + lastPacketMS : (lastPacketMS < 800
            ? "&6" + lastPacketMS : "&c" + lastPacketMS)));

        mc.fontRendererObj
            .drawStringWithShadow(ChatHelper.fix(
                lastPacketMS > 30500 ? "&bLast packet: &3Broken pipe"
                    : String.format("&bLast packet: &3%s &bms", packetMs)), 5, 160, 0);


        //Collections.sort(Troy.modules, new ModuleComparator());

        Troy.modules.sort(Comparator.comparingInt(m ->
                Wrapper.fr.getStringWidth(((Module)m).name))
                .reversed()
        );

        ScaledResolution sr = new ScaledResolution(mc);
        FontRenderer fr = mc.fontRendererObj;
        int count = 0;

        for(Module m : Troy.modules){
            if(!m.toggled || m.name.equals("TabGUI"))
                continue;

            int offset = count*(fr.FONT_HEIGHT + 6);

            Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(m.name) -10,  offset, sr.getScaledWidth() - fr.getStringWidth(m.name) -8, 6 + fr.FONT_HEIGHT +  offset, 0x5900bfd8);
            Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(m.name) -8,  offset, sr.getScaledWidth(), 6 + fr.FONT_HEIGHT +  offset, 0x90000000);
            fr.drawStringWithShadow(m.name, sr.getScaledWidth() - fr.getStringWidth(m.name) -4, 4 + offset, -1);

            count++;
        }
        Troy.onEvent(new EventRenderGUI());
      }
    }
  }
}