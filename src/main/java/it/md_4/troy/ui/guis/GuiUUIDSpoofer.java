/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  us.myles.viaversion.libs.bungeecordchat.api.ChatColor
 */
package it.md_4.troy.ui.guis;

import java.awt.*;
import java.io.IOException;

import it.md_4.troy.ui.guis.uts.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.Session;

public class GuiUUIDSpoofer
extends GuiScreen {
    protected /* synthetic */ GuiTextField ipField;
    protected /* synthetic */ GuiTextField realNickField;
    protected /* synthetic */ GuiTextField fakeNickField;
    protected /* synthetic */ GuiScreen prevScreen;
    protected /* synthetic */ String ActualUUID;

    public GuiUUIDSpoofer(GuiScreen guiScreen) {
        this.prevScreen = guiScreen;
    }

    @Override
    public void drawScreen(int n, int n2, float f) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Real nick", this.width / 2, this.realNickField.yPosition - 15, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "Fake nick", this.width / 2, this.fakeNickField.yPosition - 15, 0xFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "Fake IP", this.width / 2, this.ipField.yPosition - 15, 0xFFFFFF);
        this.ipField.drawTextBox();
        this.fakeNickField.drawTextBox();
        this.realNickField.drawTextBox();
        int n3 = this.height / 4 + 48;
        GuiUUIDSpoofer.drawString(this.fontRendererObj, String.valueOf(new StringBuilder().append("Actual UUID : ").append(this.ActualUUID)), this.width / 2 - this.fontRendererObj.getStringWidth(String.valueOf(new StringBuilder().append("Actual UUID : ").append(this.ActualUUID))) / 2, n3 + 120, 0xFF5555);
        super.drawScreen(n, n2, f);
    }

    @Override
    public void initGui() {
        int n = 200;
        int n2 = 20;
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 95, n, n2, "Save"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 95 + n2 + 4, n, n2, "Cancel"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 95 + n2 + 28, n, n2, this.getEnableButtonText()));
        this.realNickField = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, this.height / 5, n, n2);
        this.fakeNickField = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 100, this.height / 5 + 40, n, n2);
        this.ipField = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 100, this.height / 5 + 80, n, n2);
        this.ipField.setText(this.mc.getFakeIp());
        this.fakeNickField.setText(this.mc.getFakeNick());
        this.realNickField.setText(Minecraft.getSession().getUsername());
        this.ActualUUID = UUID.getUUID();
    }

    private String getEnableButtonText() {
        return this.mc.isUUIDHack ? String.valueOf(new StringBuilder().append((Object) Color.RED).append("Disable BungeeHack")) : String.valueOf(new StringBuilder().append((Object)Color.GREEN).append("Enable BungeeHack"));
    }

    @Override
    protected void mouseClicked(int n, int n2, int n3) throws IOException {
        this.ipField.mouseClicked(n, n2, n3);
        this.fakeNickField.mouseClicked(n, n2, n3);
        this.realNickField.mouseClicked(n, n2, n3);
        super.mouseClicked(n, n2, n3);
    }

    @Override
    protected void keyTyped(char c, int n) throws IOException {
        if (n == 1) {
            this.mc.displayGuiScreen(this.prevScreen);
            return;
        }
        if (n == 15) {
            if (this.realNickField.isFocused()) {
                this.realNickField.setFocused(false);
                this.fakeNickField.setFocused(true);
            } else if (this.fakeNickField.isFocused()) {
                this.fakeNickField.setFocused(false);
                this.ipField.setFocused(true);
            } else if (this.ipField.isFocused()) {
                this.ipField.setFocused(false);
                this.realNickField.setFocused(true);
            }
        }
        if (this.ipField.isFocused()) {
            this.ipField.textboxKeyTyped(c, n);
        }
        if (this.fakeNickField.isFocused()) {
            this.fakeNickField.textboxKeyTyped(c, n);
        }
        if (this.realNickField.isFocused()) {
            this.realNickField.textboxKeyTyped(c, n);
        }
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) throws IOException {
        if (guiButton.id == 1) {
            Session session = Minecraft.getSession();
            this.mc.setSession(new Session(this.realNickField.getText(), session.getPlayerID(), session.getToken(), Session.Type.LEGACY.name()));
            this.mc.setFakeNick(this.fakeNickField.getText());
            this.mc.setFakeIp(this.ipField.getText());
            this.mc.displayGuiScreen(this.prevScreen);
            if (this.mc.getCurrentServerData() != null && this.mc.theWorld != null) {
                ServerData serverData = this.mc.getCurrentServerData();
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld(null);
                this.mc.displayGuiScreen(new GuiConnecting(this.prevScreen, this.mc, serverData));
            } else {
                this.mc.displayGuiScreen(this.prevScreen);
            }
        } else if (guiButton.id == 2) {
            this.mc.displayGuiScreen(this.prevScreen);
        } else if (guiButton.id == 3) {
            this.mc.isUUIDHack = !this.mc.isUUIDHack;
            guiButton.displayString = this.getEnableButtonText();
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
    }
}

