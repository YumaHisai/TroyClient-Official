// 
// Decompiled by Procyon v0.5.36
// 

package com.md_4.troy.ui.utils;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.ResourceLocation;

public class RenderUtil
{
    private static float zLevel;
    
    public static void drawPic(final ResourceLocation src, final int x, final int y, final int w, final int h) {
        GL11.glPushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(src);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.blendFunc(770, 771);
        GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
        GL11.glEnable(3042);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, w, h, (float)w, (float)h);
        GL11.glPopMatrix();
    }
    
    public static void drawBorderedRect(final float x, final float y, final float x2, final float y2, final float l1, final int col1, final int col2) {
        drawRect(x, y, x2, y2, col2);
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x2, (double)y);
        GL11.glVertex2d((double)x, (double)y2);
        GL11.glVertex2d((double)x2, (double)y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    public static void drawPic(final String src, final int x, final int y, final int w, final int h) {
        drawPic(new ResourceLocation(src), x, y, w, h);
    }
    
    public static void drawGradientRectHorizontal(final int left, final int top, final int right, final int bottom, final int leftColor, final int rightColor) {
        final float f = (leftColor >> 24 & 0xFF) / 255.0f;
        final float f2 = (leftColor >> 16 & 0xFF) / 255.0f;
        final float f3 = (leftColor >> 8 & 0xFF) / 255.0f;
        final float f4 = (leftColor & 0xFF) / 255.0f;
        final float f5 = (rightColor >> 24 & 0xFF) / 255.0f;
        final float f6 = (rightColor >> 16 & 0xFF) / 255.0f;
        final float f7 = (rightColor >> 8 & 0xFF) / 255.0f;
        final float f8 = (rightColor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(right, top, RenderUtil.zLevel).color(f6, f7, f8, f5).endVertex();
        worldrenderer.pos(left, top, RenderUtil.zLevel).color(f2, f3, f4, f).endVertex();
        worldrenderer.pos(left, bottom, RenderUtil.zLevel).color(f2, f3, f4, f).endVertex();
        worldrenderer.pos(right, bottom, RenderUtil.zLevel).color(f6, f7, f8, f5).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawGradientRectVertical(final int left, final int top, final int right, final int bottom, final int startColor, final int endColor) {
        final float f = (startColor >> 24 & 0xFF) / 255.0f;
        final float f2 = (startColor >> 16 & 0xFF) / 255.0f;
        final float f3 = (startColor >> 8 & 0xFF) / 255.0f;
        final float f4 = (startColor & 0xFF) / 255.0f;
        final float f5 = (endColor >> 24 & 0xFF) / 255.0f;
        final float f6 = (endColor >> 16 & 0xFF) / 255.0f;
        final float f7 = (endColor >> 8 & 0xFF) / 255.0f;
        final float f8 = (endColor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(right, top, RenderUtil.zLevel).color(f2, f3, f4, f).endVertex();
        worldrenderer.pos(left, top, RenderUtil.zLevel).color(f2, f3, f4, f).endVertex();
        worldrenderer.pos(left, bottom, RenderUtil.zLevel).color(f6, f7, f8, f5).endVertex();
        worldrenderer.pos(right, bottom, RenderUtil.zLevel).color(f6, f7, f8, f5).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void drawRect(final float g, final float h, final float i, final float j, final int col1) {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(7);
        GL11.glVertex2d((double)i, (double)h);
        GL11.glVertex2d((double)g, (double)h);
        GL11.glVertex2d((double)g, (double)j);
        GL11.glVertex2d((double)i, (double)j);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
}
