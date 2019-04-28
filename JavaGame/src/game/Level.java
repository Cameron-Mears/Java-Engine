package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gameobjects.Block;

public class Level
{
    public Block[][] grid;
    private BufferedImage[] backgrounds;

    /*
        contains blocks of level, render method render a certain range of blocks in the array for better prefornce (unnessarcy rendering)
    */

    public void render(Graphics2D g, int xStart, int yStart, int xEnd, int yEnd)
    {
        for (int xIndex = xStart; xIndex < grid.length && xIndex < xEnd; xIndex++)
        {
            for (int yIndex = yStart; yIndex < grid[xIndex].length && yIndex < yEnd; yIndex ++)
            {
                if (grid[xIndex][yIndex] != null) grid[xIndex][yIndex].render(g);
            }
        }
    }

}