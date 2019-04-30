package game;

import entities.Entity;
import gameobjects.Block;
import physics.Vec2d;

public interface Functions extends Gamecore
{

    public default boolean checkCollision(Block[][] grid, Entity e)
    {
        Vec2d vec = e.getVec();
        
        int yCheck, xCheck;
        if (vec.velY > 0)
        {
            yCheck = (int) Math.ceil((vec.y + (vec.velY * deltaSEC()) - 32) / 32);
        }
        else
        {
            yCheck = (int) Math.floor(vec.y / 32);
        }

        if (vec.velX > 0) 
        {
            xCheck = (int) Math.ceil((vec.x - ((e.getWidth() / 3) * e.getXScale())) / 32);
        } 
        else 
        {
            xCheck = (int) Math.floor(vec.x  / 32);
        }
        xCheck = clamp(xCheck, 0, 13);
        
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