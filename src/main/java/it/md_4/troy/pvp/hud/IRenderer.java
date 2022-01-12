package it.md_4.troy.pvp.hud;

public interface IRenderer extends IRenderConfig{

    int getWeight();
    int getHeight();

    void render(ScreenPosition pos);

    default void renderDummy(ScreenPosition pos){
        render(pos);
    }

    public default boolean isEnabled(){
        return true;
    }
}
