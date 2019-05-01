package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;

import entities.Entities;
import entities.Entity;
import gameobjects.Block;
import physics.Vec2d;

public interface Functions extends Gamecore
{

    public default double nextX(Vec2d vec)
    {
        double x = (vec.x + (vec.velX * deltaSEC()) + (vec.acelX *deltaSEC() * deltaSEC()/2));
        return x;
    }

    public default double nextY(Vec2d vec)
    {
        double x = (vec.y + (vec.velY * deltaSEC()) + (vec.acelY *deltaSEC() * deltaSEC()/2));
        return x;
    }

    public default boolean checkEntityCollision(Entity entityA, Entity entityB)
    {
        Vec2d vecA = entityA.getVec();
        Vec2d vecB = entityB.getVec();
        if 
            (
                vecA.x < vecB.x + (entityB.getWidth() * entityB.getXScale())
                &&
                vecA.x + (entityA.getWidth() * entityA.getXScale()) > vecB.x
                &&
                vecA.x < vecB.x + (entityB.getHeight() * entityB.getYScale())
                &&
                vecA.y + (entityA.getHeight() * entityA.getXScale()) > vecB.y
            )
            {
                return true;
            }
        return false;
    }

    public default boolean checkEntityCollision(Entities type, Entity entityB)
    {
        
        Iterator<Entity> iterator = Game.handler.getTickEntities().iterator();

        while (iterator.hasNext())
        {
            Entity temp = iterator.next();
            if (temp.getType() == type)
            {
                if (checkEntityCollision(temp, entityB)) return true;
            }
        }

        return false;
       
    }

    public default Entity getEntityCollision(Entities type, Entity entityB)
    {
        Iterator<Entity> iterator = Game.handler.getTickEntities().iterator();

        while (iterator.hasNext())
        {
            Entity temp = iterator.next();
            if (temp.getType() == type)
            {
                if (checkEntityCollision(temp, entityB)) return temp;
            }
        }

        return null;
    }

    public default boolean checkGridCollision(Block[][] grid, Entity e, double x, double y)
    {
        /*
        find the blocks around the entity
        */

        int xStart, yStart;
        double width, height;
        width = e.getWidth() * e.getXScale();
        height = e.getHeight() * e.getYScale();
        xStart = (int)((x/32) - 2);
        yStart = (int)((y/32) - 2);

        int yDist = Math.floorDiv((int)height, 32) + 3;
        int xDist = Math.floorDiv((int)width, 32) + 3;
        xStart = clamp(xStart, 0, grid.length - 1);
        yStart = clamp(yStart, 0, grid[0].length - 1);
        for (int xIndex = xStart; xIndex < grid.length && (xIndex < xDist + xStart); xIndex ++)
        {
            for (int yIndex = yStart; yIndex < grid[0].length && (yIndex < yDist + yStart); yIndex ++)
            {
                if (grid[xIndex][yIndex] != null)
                {
                    int blockXPos = xIndex * 32;
                    int blockYPos = yIndex * 32;

                    /*
                    Check for overlapping bounding boxes that would indicate a collision
                    */
                    if 
                    (
                        blockXPos < x + (e.getWidth() * e.getXScale())
                        &&
                        blockXPos + 32 > x
                        &&
                        blockYPos < y + (e.getHeight() * e.getYScale())
                        &&
                        blockYPos + 32 > y
                    )
                    {
                        Game.renderLevel = true;
                        return true;
                    }
                }
            }
        }
        return false;
    };


    public default boolean predictCollision(Block[][] grid, Entity e)
    {
        Vec2d vec = e.getVec();
      
        
        int yCheck, xCheck;
        if (vec.velY > 0)
        {
            yCheck = (int) Math.ceil((nextY(vec)- ((e.getHeight() / 2) * e.getYScale())) / 32);
        }
        else
        {
            yCheck = (int) Math.floor(vec.y / 32);
        }

        if (vec.velX > 0) 
        {
            xCheck = (int) Math.ceil((nextX(vec) - ((e.getWidth() / 2) * e.getXScale()) ) / 32);
        } 
        else 
        {
            xCheck = (int) Math.floor(vec.x / 32);
        }
        xCheck = clamp(xCheck, 0, 12);
        yCheck = clamp(yCheck, 0, 12);
        
        return grid[xCheck][yCheck] != null;
    }

    public default double clamp(double val, double min, double max)
    {
        if (val > max) return max;
        if (val < min) return min;
        return val;
    }
    
    public default int clamp(int val, int min, int max) 
    {
        if (val > max)
            return max;
        if (val < min)
            return min;
        return val;
    }

    public default int randint(int min, int max)
    {
        double temp = Math.round((Math.random() * (max - min)) + min);
        
        return (int) temp;
    }

    public default double rand(double min, double max) 
    {
        double temp = (Math.random() * (max - min)) + min;
        return temp;
    }

    

    public default double toRadians(double angle)
    {
        return (angle * (Math.PI/180));
    }

    public default double toDegrees(double angle)
    {
        return (angle * (180 / Math.PI));
    }

    
}