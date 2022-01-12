package it.md_4.troy.pvp.hud;

import com.google.common.collect.Sets;
import it.md_4.troy.pvp.events.EventManager;
import it.md_4.troy.pvp.events.EventTarget;
import it.md_4.troy.pvp.events.impl.RenderEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;

import java.util.HashSet;
import java.util.Set;

public class HUDManager {

    private HUDManager() {}

    private static HUDManager instance = null;

    public static HUDManager getInstance() {

        if(instance != null){
            return instance;
        }

        instance = new HUDManager();
        EventManager.register(instance);


        return instance;
    }

    private Set<IRenderer> registeredRenderers = Sets.newHashSet();

    public void register(IRenderer... renderers){
        for(IRenderer render : renderers){
            this.registeredRenderers.add(render);
        }
    }

    public void unregister(IRenderer... renderers){
        for(IRenderer render : renderers){
            this.registeredRenderers.remove(render);
        }
    }

    public HashSet<IRenderer> getRegisteredRenderers() {
        return Sets.newHashSet(registeredRenderers);
    }

    public void openConfigScreen(){
        Minecraft.getMinecraft().displayGuiScreen(new HUDConfigScreen(this));
    }

    @EventTarget
    public void onRender(RenderEvent e){
        if(Minecraft.getMinecraft().currentScreen == null || Minecraft.getMinecraft().currentScreen instanceof GuiContainer || Minecraft.getMinecraft().currentScreen instanceof GuiChat){
            for(IRenderer renderer : registeredRenderers){
                callRenderer(renderer);
            }
        }
    }

    private void callRenderer(IRenderer renderer){
        if(!renderer.isEnabled()){
            return;
        }

        ScreenPosition pos = renderer.load();

        if(pos == null){
            pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
        }

        renderer.render(pos);
    }

}
