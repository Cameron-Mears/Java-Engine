package entities;

import java.awt.Graphics2D;

import entities.entitylist.EntityNode;
import game.Functions;
import game.Gamecore;
import game.input.Input;
import physics.PhysicsCal;
import physics.Vec2d;

/*
    super class for entities.
*/

public abstract class Entity implements Input, Gamecore, PhysicsCal, Functions
{
    protected Vec2d vec;
    protected int mass;

    protected EntityNode node;

    protected int width = 0;
    protected int height = 0;

    protected double xScale;
    protected double yScale;

    protected Entities type;


    public Entity(double x, double y)
    {
        vec = new Vec2d();
        vec.x = x;
        vec.y = y;
    }

    public Vec2d getVec()
    {
        return vec;
    }

    public EntityNode getNode()
    {
        return node;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public double getXScale()
    {
        return xScale;
    }

    public double getYScale()
    {
        return yScale;
    }


    public void tick()
    {

    }

    public void render(Graphics2D g)
    {
        
    }

    public Entities getType()
    {
        return type;
    }

}