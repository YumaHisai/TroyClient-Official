/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package it.md_4.troy.ui.guis;

import java.io.IOException;

import it.md_4.troy.ui.guis.proxy.ProxyManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public final class GuiProxy
extends GuiScreen {
    private static /* synthetic */ boolean isRunning;
    public static /* synthetic */ String renderText;
    static /* synthetic */ GuiScreen before;
    static /* synthetic */ GuiTextField ip;
    private /* synthetic */ String status;
    private /* synthetic */ GuiButton button;

    public GuiProxy(GuiScreen guiScreen) {
        before = guiScreen;
    }

    @Override
    public void updateScreen() {
        ip.updateCursorCounter();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    @Override
    public void initGui() {
        renderText = "";
        this.button = new GuiButton(0, this.width / 2 - 100, this.height / 3 + 40, 200, 20, isRunning ? "\u00a7cDisconnect" : "\u00a7aConnect");
        this.buttonList.add(this.button);
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 3 + 66, 200, 20, "Back"));
        ip = new GuiTextField(this.height, this.mc.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        ip.setMaxStringLength(100);
        ip.setText("127.0.0.1:8080");
        this.status = "\u00a74Waiting...";
        ip.setFocused(true);
        Keyboard.enableRepeatEvents((boolean)true);
    }

    static {
        renderText = "";
    }

    @Override
    protected void mouseClicked(int n, int n2, int n3) {
        try {
            super.mouseClicked(n, n2, n3);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        ip.mouseClicked(n, n2, n3);
    }

    @Override
    public void drawScreen(int n, int n2, float f) {
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        this.drawDefaultBackground();
        GL11.glPushMatrix();
        GL11.glColor4d((double)1.0, (double)1.0, (double)1.0, (double)1.0);
        GL11.glScaled((double)4.0, (double)4.0, (double)4.0);
        this.drawCenteredString(this.mc.fontRendererObj, renderText, this.width / 8, this.height / 4 - this.mc.fontRendererObj.FONT_HEIGHT, 0);
        GL11.glPopMatrix();
        this.drawCenteredString(this.mc.fontRendererObj, this.status, this.width / 2, 20, -1);
        ip.drawTextBox();
        this.drawCenteredString(this.mc.fontRendererObj, "\u00a77Proxy IP:Port", this.width / 2, 50, -1);
        super.drawScreen(n, n2, f);
    }

    @Override
    protected void keyTyped(char c, int n) {
        try {
            super.keyTyped(c, n);
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        if (c == '\r') {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
        ip.textboxKeyTyped(c, n);
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) {
        switch (guiButton.id) {
            case 1: {
                this.mc.displayGuiScreen(before);
                break;
            }
            case 0: {
                if (isRunning) {
                    isRunning = false;
                    guiButton.displayString = "\u00a7aConnect";
                    ProxyManager.setProxy(null);
                    break;
                }
                String[] arrstring = ip.getText().split(":");
                if (arrstring.length == 2) {
                    ProxyManager.setProxy(ProxyManager.getProxyFromString(ip.getText()));
                    this.status = String.valueOf(new StringBuilder().append("\u00a74Proxy used ").append(ProxyManager.getProxy().address().toString().replace("/", "")));
                    isRunning = true;
                    guiButton.displayString = "\u00a7cDisconnect";
                    break;
                }
                this.status = "\u00a7cPlease use: <host>:<port>";
                break;
            }
        }
    }
}

