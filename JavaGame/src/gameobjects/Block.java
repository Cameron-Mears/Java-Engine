package gameobjects;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;

import game.Gamecore;

public class Block
{
    private BufferedImage texture;

    private int xPos;
    private int yPos;

    private int width;
    private int height;

    public Block( int x, int y, BufferedImage texture)
    {
        this.texture = texture;
        xPos = x;
        yPos = y;
        width = texture.getWidth();
        height = texture.getHeight();
    }

    public void render(Graphics g)
    {
        g.drawImage(texture, xPos * width, yPos * height, null);
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}