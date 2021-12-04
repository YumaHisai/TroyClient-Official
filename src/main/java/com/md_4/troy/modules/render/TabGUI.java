package com.md_4.troy.modules.render;

import com.md_4.troy.Troy;
import com.md_4.troy.modules.Module;
import com.md_4.troy.modules.events.Event;
import com.md_4.troy.modules.events.listeners.EventKey;
import com.md_4.troy.modules.events.listeners.EventRenderGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class TabGUI extends Module {

    public int currentTab;
    public boolean expanded;

    public TabGUI() {
        super("TabGUI", Keyboard.KEY_NONE, Category.RENDER);
        toggled = true;
    }

    public void onEvent(Event e){
        if(e instanceof EventRenderGUI){
            FontRenderer fr = Minecraft.fontRendererObj;
            Gui.drawRect(5 , 20, 1 + Category.values().length * 15 + 2 , 102 , 0x90000000);
            Gui.drawRect(6, 20 + currentTab * 16, 8 ,20 + currentTab * 16 + 18, 0x5900bfd8);

            int count = 0;
            for(Category c : Module.Category.values()){
                fr.drawStringWithShadow(c.name, 14, 25 + count*16,  -1);

                count++;
            }

            if(expanded) {
                Category category = Module.Category.values()[currentTab];
                List<Module> modules = Troy.getModulesByCategory(category);

                if(modules.size() == 0)
                    return;

                Gui.drawRect(80, 20, 10 + 98 + modules.size() * 16 + 4, 102, 0x90000000);
                Gui.drawRect(82, 22 + category.moduleIndex * 16, 79, 20 + category.moduleIndex * 16 + 18, 0x5900bfd8);

                count = 0;
                for (Module m : modules) {
                    fr.drawStringWithShadow(m.name, 10 + 78, 25 + count * 16, -1);

                    count++;
                }
            }
        }

        if(e instanceof EventKey){

            List<Module> modules = Troy.getModulesByCategory(Module.Category.values()[currentTab]);

            int code = ((EventKey)e).code;

            if(code == Keyboard.KEY_UP){
                if(expanded){
                    if(category.moduleIndex <= 0){
                        category.moduleIndex = modules.size() - 1;
                    } else
                        category.moduleIndex--;
                } else {
                    if(currentTab <= 0){
                        currentTab = Module.Category.values().length - 1;
                    } else
                        currentTab--;
                }

            }

            if(code == Keyboard.KEY_DOWN){
                if(expanded){
                    if(category.moduleIndex >= modules.size() - 1){
                        category.moduleIndex = 0;
                    } else
                        category.moduleIndex++;
                } else {
                    if(currentTab >= Module.Category.values().length - 1){
                        currentTab = 0;
                    } else
                        currentTab++;
                }

            }

            if(code == Keyboard.KEY_RIGHT){
                if(expanded && modules.size() != 0){
                    Module module = modules.get(category.moduleIndex);
                    if(!module.name.equals("TabGUI"))
                        module.toggle();
                } else {
                    expanded = true;
                }
            }

            if(code == Keyboard.KEY_LEFT){
                expanded = false;
            }
        }

    }

}
