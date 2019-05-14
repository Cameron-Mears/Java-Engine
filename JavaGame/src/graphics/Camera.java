package graphics;

import java.awt.Dimension;

import entities.Entity;

public class Camera
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
                return x - width/2;
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
                return (y - height/2);
            }
            case Box:
                return y;
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

    public int getTargetXOffset() {
        return (target == null) ? -1 : (int) (target.getWidth() * target.getXScale());
    }

}