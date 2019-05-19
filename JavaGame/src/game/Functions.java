package game;

import java.awt.Color;
import java.awt.Graphics2D;
import game.list.*;

import entities.Entities;
import entities.Entity;
import gameobjects.Block;
import graphics.Camera;
import physics.Vec2d;

public interface Functions extends Gamecore
{

    public default double nextX(Vec2d vec)
    {
        double x = (vec.x + (vec.velX * deltaSEC()) + (vec.acelX *deltaSEC() * deltaSEC())/2);
        return x;
    }

    public default double nextY(Vec2d vec)
    {
        double x = (vec.y + (vec.velY * deltaSEC()) + (vec.acelY *deltaSEC() * deltaSEC())/2);
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
        
        Iterator<Entity> iterator = new Iterator<Entity>(Game.handler.getGameEntities());

        while (iterator.hasNext())
        {
            Entity temp = iterator.getNext();
            if (temp.getType() == type)
            {
                if (checkEntityCollision(temp, entityB)) return true;
            }
        }

        return false;
       
    }

    public default Entity getEntityCollision(Entities type, Entity entityB)
    {
        Iterator<Entity> iterator = new Iterator<Entity>(Game.handler.getGameEntities());

        while (iterator.hasNext())
        {
            Entity temp = iterator.getNext();
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

        int xStart, yStart, width, height;
        width = e.getWidth();
        height = e.getHeight();
        xStart = (int)((x/32) - 2);
        yStart = (int)((y/32) - 2);

        int yDist = Math.floorDiv(height, 32) + 3;
        int xDist = Math.floorDiv(width, 32) + 3;
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
                    Check for overlapMath.PIng bounding boxes that would indicate a collision
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

    public default double directionPoint(double x1, double y1, double x2, double y2)
    {
        double deltaY = y2 - y1;
        double deltaX = x2 - x1;
        double temp = toDegrees(Math.abs(Math.atan(deltaY/deltaX)));
        if (deltaY < 0 && deltaX > 0)
        {
            return temp;
        }

        if (deltaY < 0 && deltaX < 0) 
        {
            return 360 - temp; 

        }

        if (deltaY > 0 && deltaX > 0)
        {
            return 180 - temp;
        }

        else
        {
            return 180 + temp;

        }
    

    } 

    public default Vec2d getDirection(Vec2d vec)
    {
        if (vec.velX == 0)
        {
            vec.direction = (vec.velY > 0)? 180:0;
            return vec;
        }

        if (vec.velY == 0)
        {
            vec.direction = (vec.velX < 0)? 270:90;
            return vec;
        }

        double temp = toDegrees(Math.abs(Math.atan(vec.velY/vec.velX)));
        if (vec.velY < 0 && vec.velX > 0)
        {
            vec.direction = temp;
            return vec;
        }

        if (vec.velY < 0 && vec.velX < 0) 
        {
            temp = 360 - temp;
            vec.direction = temp;
            return vec;
        }

        if (vec.velY > 0 && vec.velX > 0)
        {
            temp = 180 - temp;
            vec.direction = temp;
            return vec;
        }

        else
        {
            temp = 180 + temp;
            vec.direction = temp;
            return vec;
        }
    }

    /*
    trig functions use taylor series, functions approximate function angle is mod pi as function only
    accutate to cos(pi) which is 1, -1, cos min. This is better for game
    as these trig functions are quite as accurate as their are less terms here
    but still very close like 99.99% accurate, but with the bonus of much better preformance
    */
    public default double cos(double a)
    {
        double angle = a % Math.PI;
        return (1 - Math.pow(angle, 2)/2 + Math.pow(angle, 4)/24 - Math.pow(angle, 6)/720 + Math.pow(angle, 8)/40320 - Math.pow(angle, 10)/3628800);
    }

    public default double sin(double a)
    {
        double angle = a % Math.PI;
        return (angle - Math.pow(angle, 3)/6 + Math.pow(angle, 5)/120 - Math.pow(angle, 7)/5040 + Math.pow(angle, 9)/362880 - Math.pow(angle, 11)/39916800);
    }

    public default double tan(double a)
    {
        //tan(theta) = sin(theta)/cos(theta)
        return sin(a)/cos(a);
    }

    public default void addEntity(Entity entity, int depth)
    {
        Game.handler.add(entity);
        Game.renderer.add(depth, entity);
    }

    public default Camera getCamera()
    {
        return Game.renderer.camera;
    }


    
}