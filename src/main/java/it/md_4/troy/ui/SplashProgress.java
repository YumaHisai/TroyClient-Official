package it.md_4.troy.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class SplashProgress {

    private static final int MAX = 8;
    private static int PROGRESS = 0;
    private static String CURRENT = "";
    private static ResourceLocation splash;
    private static UnicodeFontRenderer ufr;

    public static void update(){
        if(Minecraft.getMinecraft() == null || Minecraft.getMinecraft().getLanguageManager() == null){
            return;
        }
        drawSplash(Minecraft.getMinecraft().getTextureManager());
    }

    public static void setProgress(int givenProgress, String givenText){
        PROGRESS = givenProgress;
        CURRENT = givenText;
        update();
    }

    public static void drawSplash(TextureManager tm){
        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
        int scaleFactor = scaledresolution.getScaleFactor();

        Framebuffer framebuffer = new Framebuffer(scaledresolution.getScaledHeight() * scaleFactor, scaledresolution.getScaledHeight() * scaleFactor, true);

        framebuffer.bindFramebuffer(false);

        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
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
        framebuffer.framebufferRender(scaledresolution.getScaledWidth() * scaleFactor, scaledresolution.getScaledHeight() * scaleFactor);

        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0);

        Minecraft.getMinecraft().updateDisplay();
    }
    /*
    private static void drawProgress(){

        if(Minecraft.getMinecraft().gameSettings == null || Minecraft.getMinecraft().getTextureManager() == null){
            return;
        }

        if(ufr == null){
            ufr = UnicodeFontRenderer.getFontOnPC("Arial", 20);
        }

        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

        double nProgress = PROGRESS;
        double calc = (nProgress / MAX) * sr.getScaledWidth();

        Gui.drawRect(0, sr.getScaledHeight() -35, sr.getScaledWidth(), sr.getScaledHeight(), new Color(0,0,0,50).getRGB());

        GlStateManager.resetColor();
        resetTextureState();

        ufr.drawString(CURRENT, 20F, (sr.getScaledHeight() - 25), 0xFFFFFFFF);

        String step = PROGRESS + "/" + MAX;

        ufr.drawString(step, (sr.getScaledWidth() - 20 - ufr.getStringWidth(step)),(sr.getScaledHeight() - 25), 0xe1e1e1FF);

        GlStateManager.resetColor();
        resetTextureState();

        Gui.drawRect(0, sr.getScaledHeight() - 2, (int)calc, sr.getScaledHeight(), new Color(149, 201, 144).getRGB());

        Gui.drawRect(0, sr.getScaledHeight() -2, sr.getScaledWidth(), sr.getScaledHeight(), new Color(0,0,0,10).getRGB());
    }

    private static void resetTextureState(){
        GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName = -1;
    }
     */
}
