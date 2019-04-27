package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import game.Window;

public class Renderer extends Canvas
{
    BufferStrategy bs;
    Graphics2D g;
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
        g = (Graphics2D) bs.getDrawGraphics();
        return g;


    }

    public void show()
    {
        g.dispose();
        bs.show();
    }
}