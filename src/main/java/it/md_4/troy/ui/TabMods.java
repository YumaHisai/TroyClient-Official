// 
// Decompiled by Procyon v0.5.36
// 

package it.md_4.troy.ui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.ArrayList;

public class TabMods
{
    private TabGui gui;
    public ArrayList hacks;
    public String tabName;
    public int selectedItem;
    public int menuHeight;
    public int menuWidth;
    private int colour;
    
    public TabMods(final TabGui GUI, final String TabName) {
        this.selectedItem = 0;
        this.menuHeight = 0;
        this.menuWidth = 0;
        this.tabName = TabName;
        this.gui = GUI;
        this.hacks = new ArrayList();
    }
    
    public void countMenuSize() {
        int maxWidth = 0;
        for (int i = 0; i < this.hacks.size(); ++i) {
            Minecraft.getMinecraft();
            if (Wrapper.fr().getStringWidth(String.valueOf(this.hacks.get(i).getClass().getName()) + 4) > maxWidth) {
                maxWidth = (int)(Wrapper.fr().getStringWidth(this.hacks.get(i).getClass().getName()) + 7.5f);
            }
        }
        this.menuWidth = maxWidth;
        this.menuHeight = this.hacks.size() * this.gui.tabHeight - 1;
    }
    
    public int RGBtoHEX(final int r, final int g, final int b, final int a) {
        return (a << 24) + (r << 16) + (g << 8) + b;
    }
    
    public void drawTabMenu(int x, int y) {
        this.countMenuSize();
        x += 4;
        y += 2;
        Gui.drawRect(x - 2, y - 1, x + this.menuWidth + 3, y + this.menuHeight - 1, new Color(0, 0, 0, 100).getRGB());
        Gui.drawRectIGM(x - 2, y - 1, x, y + this.menuHeight - 1, 0, 10, 0);
        for (int i = 0; i < this.hacks.size(); ++i) {
            if (i == TabGui.selectedItem) {
                Gui.drawRectIGM(x - 2, y + this.gui.tabHeight * i + 10, x + this.menuWidth + 3, y + this.gui.tabHeight * i + 12, 0, 10, 0);
                Wrapper.fr().drawString(String.valueOf(this.hacks.get(i).getClass().desiredAssertionStatus() ? "§d§o" : "§a§o") + this.hacks.get(i).getClass().getName(), x + 2, y + this.gui.tabHeight * i + 1, -739999111);
            }
            else {
                Wrapper.fr().drawString(String.valueOf(this.hacks.get(i).getClass().desiredAssertionStatus() ? "§d" : "§f") + this.hacks.get(i).getClass().getName(), x + 2, y + this.gui.tabHeight * i + 1, -739999111);
            }
        }
    }
}
