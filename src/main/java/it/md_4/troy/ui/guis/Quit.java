/*
 * Decompiled with CFR 0.150.
 */
package it.md_4.troy.ui.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

public class Quit
extends GuiScreen {
    public static GuiButton b;

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("Quit", new Object[0])));
    }

    @Override
    public void confirmClicked(boolean bl, int n) {
        this.mc.displayGuiScreen(this);
        if (n == 109 && bl && Minecraft.getMinecraft().theWorld != null) {
            Minecraft.getMinecraft().theWorld.getWorldInfo().setDifficultyLocked(true);
        }
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) throws IOException {
        if (guiButton.enabled && guiButton.id == 200) {
            mc.shutdown();
        }
    }

    @Override
    protected void keyTyped(char c, int n) throws IOException {
        super.keyTyped(c, n);
    }

    @Override
    public void drawScreen(int n, int n2, float f) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("\u00a7aTroyClient > Are you sure you want to exit the client?", new Object[0]), this.width / 2, 100, 0xFFFFFF);
        super.drawScreen(n, n2, f);
    }
}

