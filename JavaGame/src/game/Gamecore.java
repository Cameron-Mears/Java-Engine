package game;

import java.awt.GraphicsConfiguration;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import graphics.Sprite;

public interface Gamecore
{
    /*
        Interface for acess to main componets of game pretty self explanitory
    */
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