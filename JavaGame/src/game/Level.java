package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gameobjects.Block;

public class Level
{
    public static Block[][] grid;
    private BufferedImage[] backgrounds;

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