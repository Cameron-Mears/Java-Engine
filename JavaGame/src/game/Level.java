package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import gameobjects.Block;
import graphics.ImageParser;

public class Level implements Functions
{
    public Block[][] grid;
    public LinkedList<Point> emptyCells = new LinkedList<Point>();
    private int width;
    private int height;
    private BufferedImage background = ImageParser.parseFolder(new File(System.getProperty("user.dir") + "\\JavaGame\\assets\\sprites\\background"))[0];
    private int bgWidth = background.getWidth();
    private int bgHeight = background.getHeight();

    /*
        contains blocks of level, render method render a certain range of blocks in the array for better prefornce (unnessarcy rendering)
    */

    public void renderMain(Graphics2D g, int xStart, int yStart, int xEnd, int yEnd)
    {
        xStart = clamp(xStart, 0, grid.length);
        yStart = clamp(yStart, 0, grid[0].length);
        for (int xIndex = xStart; xIndex < grid.length && xIndex < xEnd; xIndex++)
        {
            for (int yIndex = yStart; yIndex < grid[xIndex].length && yIndex < yEnd; yIndex ++)
            {
                if (grid[xIndex][yIndex] != null) grid[xIndex][yIndex].render(g);
                else {g.drawImage(background, (xIndex * 32), (yIndex * 32), null);}
            }
        }
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
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