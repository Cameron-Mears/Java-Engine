package game;

import java.awt.GraphicsConfiguration;

import graphics.Sprite;

public interface Gamecore
{
    public default Window getWindow()
    {
        return Game.window;
    };

    public default double deltaSEC()
    {
        double temp = (double) Game.deltaMS();
        return temp/1000;
    }

    public default GraphicsConfiguration graphicsConfig()
    {
        return Game.graphicsConfig;
    }

    public default void addSprite(Sprite spr)
    {
        Game.spriteHandler.add(spr);
    }
}