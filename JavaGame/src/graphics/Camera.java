package graphics;

import java.awt.Dimension;

public class Camera
{
    private int x = 0;
    private int y = 0;

    private int width;
    private int height;
    private Dimension bounds;

    private Mode mode = Mode.Box;

    public static enum Mode
    {
        Center,
        Box;
    }

    public Camera(int width, int height, Mode mode, Dimension bounds)
    {
        this.mode = mode;
        this.width = width;
        this.height = height;
        this.bounds = bounds;
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
                if (x - width/2 <= 0) return 0;
                if (x - width/2 >= bounds.getWidth()) return (int) bounds.getWidth();
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
                if (y - height/2 <= 0) return 0;
                if (y - height/2 >= bounds.getHeight()) return (int) bounds.getHeight();
                return y - height/2;
            }
            case Box:
                return y;
            default:
                return 0;
        }
    }

    

}