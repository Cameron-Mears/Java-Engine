package gameobjects;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;

import game.Gamecore;

public class Block
{
    private static BufferedImage texture;

    private int xPos;
    private int yPos;
    private int xRender;
    private int yRender;

    private static int width;
    private static int height;

    public Block( int x, int y, BufferedImage texture)
    {
        this.texture = texture;
        xPos = x;
        yPos = y;
        width = texture.getWidth();
        height = texture.getHeight();
        xRender = xPos * width;
        yRender = yPos * height;
    }

    public void render(Graphics g)
    {
        g.drawImage(texture, xRender, yRender, null);
    }

    public static int getWidth()
    {
        return width;
    }

    public static int getHeight()
    {
        return height;
    }
}