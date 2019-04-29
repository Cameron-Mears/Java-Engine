package entities;

import java.awt.image.BufferedImage;
import java.io.File;

import game.Functions;
import game.Game;
import game.Level;
import gameobjects.Block;
import graphics.ImageParser;

public class LevelConstructor implements Functions
{
    private int x;
    private int y;

    private Level level;

    BufferedImage blockTexture = ImageParser.parseFolder(new File(System.getProperty("user.dir") + "\\JavaGame\\assets\\sprites\\sadad"))[0];

    public LevelConstructor()
    {

    }

    public Level newLevel(Level level)
    {
        level = new Level();
        
        int xCells = Game.windowWidth/blockTexture.getWidth()/2;
        int yCells = Game.windowWidth / blockTexture.getHeight()/2;

        level.grid = new Block[xCells][yCells];

        for (int xIndex = 0; xIndex < xCells; xIndex++) 
        {
            for (int yIndex = 0; yIndex < level.grid[xIndex].length; yIndex++) 
            {
                level.grid[xIndex][yIndex] = new Block(xIndex, yIndex, blockTexture);
            }
        }
        int x = 1;
        int y = (int)( Math.random() * yCells);
        y = clamp(y, 1, yCells - 1);
        while (x > 0 && x < xCells - 1 && y > 0 && y < yCells - 1)
        {
            x += randint(-1, 1);
            y += randint(-1, 1);
            level.grid[x][y] = null;
        }
        return level;

    }
}