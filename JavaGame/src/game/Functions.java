package game;

import java.awt.GraphicsConfiguration;

public interface Functions
{
    public default Window getWindow()
    {
        return Game.window;
    };

    public default double deltaSEC()
    {
        return Game.deltaNS()/1000000000;
    }

    public default GraphicsConfiguration graphicsConfig()
    {
        return Game.graphicsConfig;
    }
}