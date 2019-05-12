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
        
        int xCells = 20;
        int yCells = 20;

        level.grid = new Block[xCells][yCells];

        for (int xIndex = 0; xIndex < xCells; xIndex++) 
        {
            for (int yIndex = 0; yIndex < level.grid[xIndex].length; yIndex++) 
            {
                level.grid[xIndex][yIndex] = new Block(xIndex, yIndex, blockTexture);
            }
        }
        int x = randint(2, xCells - 2);
        int y = yCells - 2;
        level.grid[x][y] = null;
        addEntity(new Player(x * 32, y * 32), 5);
        while ( y > 1)
        {
            x += randint(-1, 1);
            int tempY = randint(-3, 3);
            y += (Math.abs(tempY) == 1)? tempY:0;

            if (y > yCells - 3) y = yCells - 3;
            if (x <= 1) x = 2;
            if (x > xCells - 3) x = xCells - 3;

            level.grid[x][y] = null;
                level.grid[x + 1][y] = null;
                level.grid[x - 1][y] = null;
                level.grid[x][y + 1] = null;
                level.grid[x][y - 1] = null;

        }
        return level;

    }
}