package entities;

import java.awt.image.BufferedImage;
import java.io.File;

import game.Functions;
import game.Level;
import gameobjects.Block;
import graphics.ImageParser;

public class LevelConstructor implements Functions
{
    private int x;
    private int y;

    private Level level;

    BufferedImage test = ImageParser.parseFolder(new File(System.getProperty("user.dir") + "\\JavaGame\\assets\\sprites\\sadad"))[0];

    public LevelConstructor()
    {

    }

    public Level newLevel(Level level)
    {
        level = new Level();
        
        int cells = 25;
        level.grid = new Block[cells][cells];

        for (int index = 0; index < cells; index++) 
        {
            for (int i = 0; i < cells; i++) 
            {
                level.grid[index][i] = new Block(index, i, test);
            }
        }
        int x = 1;
        int y = (int)( Math.random() * cells);
        y = clamp(y, 1, cells - 1);
        while (x > 0 && x < cells - 1 && y > 0 && y < cells - 1)
        {
            x += randint(-1, 1);
            System.out.println(x);
            y += randint(-1, 1);
            level.grid[x][y] = null;
        }
        return level;

    }
}