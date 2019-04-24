package graphics;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas
{
    public Renderer()
    {
        this.createBufferStrategy(2);
        BufferStrategy bs = this.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
    }
}