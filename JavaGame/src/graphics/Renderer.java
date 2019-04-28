package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import game.Window;

public class Renderer extends Canvas
{
    private static final long serialVersionUID = -8352885578412682145L;
    BufferStrategy bs;
    public Renderer(Window window)
    {
        //this.setSize(window.getWindow().getWidth(), window.getWindow().getHeight());
        window.getWindow().add(this);
    }

    public Graphics2D createGraphics()
    {
        bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        return g;


    }

    public void show()
    {
        bs.show();
    }
}