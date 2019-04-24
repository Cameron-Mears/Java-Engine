package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import game.Window;

public class Renderer extends Canvas
{
    BufferStrategy bs;
    Graphics g;
    public Renderer(Window window)
    {
        this.setSize(window.getWindow().getWidth(), window.getWindow().getHeight());
        window.getWindow().add(this);
    }

    public Graphics createGraphics()
    {
        bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        g = bs.getDrawGraphics();
        return g;


    }

    public void show()
    {
        g.dispose();
        bs.show();
    }
}