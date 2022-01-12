package it.md_4.troy.pvp.mods;

import it.md_4.troy.pvp.hud.IRenderer;
import it.md_4.troy.pvp.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements IRenderer {

    public final int getLineOffset(ScreenPosition pos, int lineNum){
        return pos.getAbsoluteY() + getLineOffset(lineNum);
    }

    private int getLineOffset(int lineNum){
        return (font.FONT_HEIGHT + 3) * lineNum;
    }

    public abstract int getHeight();
}
