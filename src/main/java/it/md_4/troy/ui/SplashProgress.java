package it.md_4.troy.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SplashProgress {

    private static ResourceLocation splash;

    public static void update(){
        if(Minecraft.getMinecraft() == null || Minecraft.getMinecraft().getLanguageManager() == null){
            return;
        }
        drawSplash(Minecraft.getMinecraft().getTextureManager());
    }

    public static void setProgress(){
        update();
    }

    public static void drawSplash(TextureManager tm){
        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
        int scaleFactor = scaledresolution.getScaleFactor();

        Framebuffer framebuffer = new Framebuffer(scaledresolution.getScaledHeight() * scaleFactor, scaledresolution.getScaledHeight() * scaleFactor, true);

        framebuffer.bindFramebuffer(false);

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, ScaledResolution.getScaledWidth(), scaledresolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        if(splash == null){
            splash = new ResourceLocation("TroyClient/splash.png");
        }

        tm.bindTexture(splash);

        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        Gui.drawScaledCustomSizeModalRect(0,0,  0, 0,1920, 1080, (int) scaledresolution.getScaledWidth_double(), (int) scaledresolution.getScaledHeight_double(),1920, 1080);
        //drawProgress();
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(ScaledResolution.getScaledWidth() * scaleFactor, scaledresolution.getScaledHeight() * scaleFactor);

        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0);

        Minecraft.getMinecraft().updateDisplay();
    }
}
