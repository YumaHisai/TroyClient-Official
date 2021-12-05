// 
// Decompiled by Procyon v0.5.36
// 

package it.md_4.troy.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import it.md_4.troy.Troy;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.gui.ScaledResolution;
import java.util.Calendar;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

public class HUD extends GuiIngame
{
    public static Minecraft mc;
    private static TabGui tab;
    static double currentSlot;
    public static double y;
    static double cursor;
    static int i;
    public static boolean ChatOpen;
    
    static {
        HUD.mc = Minecraft.getMinecraft();
        HUD.tab = new TabGui(HUD.mc);
        HUD.currentSlot = 0.0;
        HUD.cursor = 0.0;
        HUD.i = 0;
    }
    
    public HUD() {
        super(HUD.mc);
    }
    
    public static void Schermata() {
        if (Minecraft.SkinShow) {
            drawEntityOnScreen(GuiScreen.width - 40, GuiScreen.height - 25, 50, 10.0f, 0.0f, HUD.mc.thePlayer);
        }
        //Gui.drawRect(2, Minecraft.f3y + 9, 180, Minecraft.f3y + 46, new Color(0, 0, 0, 100).getRGB());
        //Gui.drawRectIGM(2, Minecraft.f3y + 9, 4, Minecraft.f3y + 46, 0, 10, 0);
        //Gui.drawRectIGM(2, Minecraft.f3y + 46, 180, Minecraft.f3y + 48, 0, 10, 0);
        final Calendar cal = Calendar.getInstance();
        final int h = cal.get(11);
        final int m = cal.get(12);
        final int s = cal.get(13);
        final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        ResizeMods();
        //HUD.tab.drawGui(1, 25, 78);
        GlStateManager.enableDepth();
    }
    
    public static void drawEntityOnScreen(final int p_147046_0_, final int p_147046_1_, final int p_147046_2_, final float p_147046_3_, final float p_147046_4_, final EntityLivingBase p_147046_5_) {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)p_147046_0_, (float)p_147046_1_, 50.0f);
        GlStateManager.scale((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        final float var6 = p_147046_5_.renderYawOffset;
        final float var7 = p_147046_5_.rotationYaw;
        final float var8 = p_147046_5_.rotationPitch;
        final float var9 = p_147046_5_.prevRotationYawHead;
        final float var10 = p_147046_5_.rotationYawHead;
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-(float)Math.atan(p_147046_4_ / 40.0f) * 20.0f, 1.0f, 0.0f, 0.0f);
        p_147046_5_.renderYawOffset = (float)Math.atan(p_147046_3_ / 40.0f) * 20.0f;
        p_147046_5_.rotationYaw = (float)Math.atan(p_147046_3_ / 40.0f) * 40.0f;
        p_147046_5_.rotationPitch = -(float)Math.atan(p_147046_4_ / 40.0f) * 20.0f;
        p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
        p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager var11 = Minecraft.getMinecraft().getRenderManager();
        var11.setRenderShadow(false);
        var11.renderEntityWithPosYaw(p_147046_5_, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        var11.setRenderShadow(true);
        p_147046_5_.renderYawOffset = var6;
        p_147046_5_.rotationYaw = var7;
        p_147046_5_.rotationPitch = var8;
        p_147046_5_.prevRotationYawHead = var9;
        p_147046_5_.rotationYawHead = var10;
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
    
    public static void ResizeMods() {
        Troy.getInstance();
        final Manager modmanager = Troy.modmanager;
        final ArrayList<Mod> newList = Manager.activeModules;
        int count = 0;
        int ypos = 0;
        int bottomh = 0;
        int modules = 0;
        Collections.sort(newList, new Comparator<Mod>() {
            @Override
            public int compare(final Mod mod, final Mod mod1) {
                if (Wrapper.fr().getStringWidth(mod.getName()) > Wrapper.fr().getStringWidth(mod1.getName())) {
                    return -1;
                }
                if (Wrapper.fr().getStringWidth(mod.getName()) < Wrapper.fr().getStringWidth(mod1.getName())) {
                    return 1;
                }
                return 0;
            }
        });
        Troy.getInstance();
        final Manager modmanager2 = Troy.modmanager;
        for (final Mod m : Manager.activeModules) {
            if (!m.getCategory().equals(Category.class)) {
                if (m.getState()) {
                    int yCount = 5;
                    final int right = ScaledResolution.getScaledWidth();
                    yCount += 10;
                    ypos += 9;
                    bottomh += 9;
                    ++modules;
                }
                if (m.animation != Minecraft.fontRendererObj.getStringWidth(m.getName())) {
                    final Mod mod2;
                    final Mod mod = mod2 = m;
                    ++mod2.animation;
                }
                count += 9;
                ++count;
            }
        }
        if (HUD.cursor >= modules * 9 - 5) {
            HUD.cursor = 0.0;
        }
        else if (HUD.cursor <= modules * 10) {
            HUD.cursor += 0.2;
        }
    }
}
