// 
// Decompiled by Procyon v0.5.36
// 

package it.md_4.troy.ui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.ArrayList;

public class TabGui
{
    public String colorNormal;
    public int guiWidth;
    public int guiHeight;
    public int tabHeight;
    public static int selectedTab;
    public static int selectedItem;
    public static boolean mainMenu;
    public static ArrayList tabsList;
    private Minecraft mc;
    
    static {
        TabGui.selectedTab = 0;
        TabGui.selectedItem = 2;
        TabGui.mainMenu = true;
    }
    
    public TabGui(final Minecraft minecraft) {
        this.colorNormal = "§g";
        this.guiWidth = 88;
        this.guiHeight = 0;
        this.tabHeight = 12;
        this.mc = minecraft;
        TabGui.tabsList = new ArrayList();
        final TabMods tabscanning = new TabMods(this, "Scanning");
        for (final Mod module : Manager.getModules()) {
            if (module.getCategory() == Category.Scanning) {
                tabscanning.hacks.add(module);
            }
        }
        TabGui.tabsList.add(tabscanning);
        final TabMods tabExploits = new TabMods(this, "Exploits");
        for (final Mod module2 : Manager.getModules()) {
            if (module2.getCategory() == Category.Exploits) {
                tabExploits.hacks.add(module2);
            }
        }
        TabGui.tabsList.add(tabExploits);
        final TabMods tabpostExploits = new TabMods(this, "PostExploits");
        for (final Mod module3 : Manager.getModules()) {
            if (module3.getCategory() == Category.PostExploits) {
                tabpostExploits.hacks.add(module3);
            }
        }
        TabGui.tabsList.add(tabpostExploits);
        final TabMods misc = new TabMods(this, "Misc");
        for (final Mod module4 : Manager.getModules()) {
            if (module4.getCategory() == Category.Misc) {
                misc.hacks.add(module4);
            }
        }
        TabGui.tabsList.add(misc);
        this.guiHeight = this.tabHeight + TabGui.tabsList.size() * this.tabHeight;
    }
    
    public void drawGui(final int posY, final int posX, final int width) {
        int x = posY;
        int y = posX;
        int widthAmount = 0;
        this.guiWidth = width;
        x = 5;
        y = Minecraft.f3y + 65;
        Gui.drawRect(x - 1, y - 1, x + this.guiWidth, y + this.guiHeight - 13, new Color(0, 0, 0, 100).getRGB());
        Gui.drawRectIGM(x - 3, y - 1, x - 1, y + this.guiHeight - 13, 0, 10, 0);
        int yOff = y + 1;
        int count = 0;
        int yOffset = 0;
        final int widthAmountTemp = 0;
        widthAmount = Wrapper.fr().getStringWidth("");
        if (widthAmountTemp > widthAmount) {
            widthAmount = widthAmountTemp;
        }
        yOffset += Wrapper.fr().FONT_HEIGHT + 1;
        ++count;
    }
    
    public int RGBtoHEX(final int r, final int g, final int b, final int a) {
        return (a << 24) + (r << 16) + (g << 8) + b;
    }
    
    public static void parseKeyUp() {
        if (TabGui.mainMenu) {
            --TabGui.selectedTab;
            if (TabGui.selectedTab < 0) {
                TabGui.selectedTab = TabGui.tabsList.size() - 1;
            }
        }
        else {
            --TabGui.selectedItem;
        }
    }
    
    public static void parseKeyDown() {
        if (TabGui.mainMenu) {
            ++TabGui.selectedTab;
            if (TabGui.selectedTab > TabGui.tabsList.size() - 1) {
                TabGui.selectedTab = 0;
            }
        }
        else {
            ++TabGui.selectedItem;
        }
    }
    
    public static void parseKeyLeft() {
        if (!TabGui.mainMenu) {
            TabGui.mainMenu = true;
        }
    }
    
    public static void parseKeyRight() {
        if (TabGui.mainMenu) {
            TabGui.mainMenu = false;
            TabGui.selectedItem = 0;
        }
    }
}
