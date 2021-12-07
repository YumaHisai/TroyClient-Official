/*
 * Decompiled with CFR 0.150.
 */
package it.md_4.troy.ui.guis;

import java.awt.Color;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class GuiPortScan
extends GuiScreen {
    protected /* synthetic */ ArrayList<Integer> ports;
    protected /* synthetic */ ArrayList<Thread> portscanthread;
    protected /* synthetic */ boolean started;
    protected /* synthetic */ GuiScreen prevScreen;
    protected /* synthetic */ int checked;
    protected /* synthetic */ GuiTextField ipField;

    public int getChecked() {
        return this.checked;
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
    }

    @Override
    public void onGuiClosed() {
        this.started = false;
        this.checked = 0;
    }

    @Override
    protected void keyTyped(char c, int n) throws IOException {
        if (n == 1) {
            this.mc.displayGuiScreen(this.prevScreen);
            return;
        }
        if (n == 15 && this.ipField.isFocused()) {
            this.ipField.setFocused(false);
        }
        if (this.ipField.isFocused()) {
            this.ipField.textboxKeyTyped(c, n);
        }
    }

    public GuiPortScan(GuiScreen guiScreen) {
        this.portscanthread = new ArrayList();
        this.prevScreen = guiScreen;
    }

    @Override
    protected void mouseClicked(int n, int n2, int n3) throws IOException {
        this.ipField.mouseClicked(n, n2, n3);
        super.mouseClicked(n, n2, n3);
    }

    public void PortScan(String string) {
        this.started = true;
        this.ports = new ArrayList();
        String string2 = string;
        int n = 140;
        int n2 = 0;
        while (n2 < n) {
            final int[] n3 = {n2++};
            Thread thread = new Thread(() -> {
                n3[0] = 45535;
                int n4 = n3[0] / n;
                int n5 = n4 * n3[0] + 20000;
                int n6 = n4 * (n3[0] + 1) + 20000;
                for (int i = n5; i < n6; ++i) {
                    try {
                        Socket socket = new Socket();
                        socket.connect(new InetSocketAddress(string2, i), 500);
                        this.ports.add(i);
                        System.out.println(String.valueOf(new StringBuilder().append("Found working port : ").append(i)));
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                    ++this.checked;
                }
            });
            thread.start();
            this.portscanthread.add(thread);
        }
    }

    @Override
    public void drawScreen(int n, int n3, float f) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Adress ip", this.width / 2, this.ipField.yPosition - 15, 0xFFFFFF);
        this.ipField.drawTextBox();
        int n4 = this.height / 4 + 120 + 12;
        Gui.drawRect(this.width / 2 - 100, n4 - 130, this.width / 2 + 100, n4 - 20, -16777216);
        int[] arrn = new int[]{0, 0, 0};
        if (this.started) {
            Gui.drawString(this.fontRendererObj, "Port scan started ...", this.width / 2 - 95 - 1, n4 - 126, Color.GRAY.getRGB());
            if (this.ports.size() > 0) {
                this.ports.forEach(n2 -> {
                    arrn[0] = arrn[0] + 1;
                    if (arrn[0] > 6) {
                        if (arrn[1] > 6) {
                            arrn[2] = arrn[2] + 1;
                            Gui.drawString(this.fontRendererObj, String.valueOf(n2), this.width / 2 - 95 - 1 + this.fontRendererObj.getStringWidth("20000 20000"), n4 - (126 - 10 * arrn[2]), Color.GREEN.getRGB());
                        }
                        arrn[1] = arrn[1] + 1;
                        Gui.drawString(this.fontRendererObj, String.valueOf(n2), this.width / 2 - 95 - 1 + this.fontRendererObj.getStringWidth("20000 "), n4 - (126 - 10 * arrn[1]), Color.GREEN.getRGB());
                    } else {
                        Gui.drawString(this.fontRendererObj, String.valueOf(n2), this.width / 2 - 95 - 1, n4 - (126 - 10 * arrn[0]), Color.GREEN.getRGB());
                    }
                });
            }
        }
        this.drawCenteredString(this.fontRendererObj, String.valueOf(new StringBuilder().append("Checked port : ").append(this.getChecked()).append("/45535")), this.width / 2, ((GuiButton)this.buttonList.get((int)2)).yPosition + 45, Color.GRAY.getRGB());
        super.drawScreen(n, n3, f);
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) throws IOException {
        if (guiButton.id == 1) {
            String string = this.ipField.getText();
            this.PortScan(string);
        } else if (guiButton.id == 2) {
            for (Thread thread : this.portscanthread) {
                thread.stop();
            }
            this.checked = 0;
            this.ports.clear();
            this.started = false;
        } else if (guiButton.id == 3) {
            this.mc.displayGuiScreen(this.prevScreen);
        }
    }

    @Override
    public void initGui() {
        int n = 200;
        int n2 = 20;
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 95 + 20, n, n2, "Start"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 95 + 40 + 4, n, n2, "Stop"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 95 + 40 + 28, n, n2, "Cancel"));
        this.ipField = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, this.height / 5, n, n2);
        this.started = false;
        this.checked = 0;
    }
}

