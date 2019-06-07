package graphics;

import java.awt.Dimension;

import entities.Entity;
import game.Functions;
import game.Game;
import gameobjects.Block;

public class Camera implements Functions
{
    private int x = 0;
    private int y = 0;

    private int width;
    private int height;
    private Dimension bounds;
    private Entity target;

    private Mode mode = Mode.Box;

    public static enum Mode
    {
        Center,
        Box;
    }

    public Camera(int width, int height, Mode mode, Entity entity)
    {
        this.mode = mode;
        this.width = width;
        this.height = height;
        target = entity;
    }

    public void setMode(Mode mode)
    {
        this.mode = mode;
    }

    public void setPos(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getXOffset()
    {
        switch (mode)
        {
            case Center:{
                return  clamp(x - width/2, 0,  Game.level.getWidth() - width + Block.getWidth()/2);
            }
            case Box:
                return x;
            default:
                return 0;
        }
    }

    public int getYOffset()
    {
        switch (mode)
        {
            case Center:{
                return clamp(y - height/2, 0,  Game.level.getHeight() -height + 192);
            }
            case Box:
                return clamp(x - width/2, 0,  Game.level.getHeight() - height + Block.getWidth()/2);
            default:
                return 0;
        }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setTarget(Entity target)
    {
        this.target = target;
    }

    public int getTargetYOffset()
    {
        return (target == null)? -1: (int)(target.getHeight() * target.getYScale());
    }

    public int getTargetXOffset() 
    {
        return (target == null) ? -1 : (int) (target.getWidth() * target.getXScale());
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }



}