package entities;

import java.awt.Graphics2D;

import game.Functions;
import game.Gamecore;
import game.input.Input;
import game.list.Node;
import physics.PhysicsCal;
import physics.Vec2d;

/*
    super class for entities.
*/

public abstract class Entity implements Input, Gamecore, PhysicsCal, Functions
{
    protected Vec2d vec;
    protected int mass;

    protected Node<Entity> rendererNode; //holds the node used by the renderer
    protected Node<Entity> handlerNode; //holds the node used by the handler

    protected int width = 0;
    protected int height = 0;

    protected double xScale;
    protected double yScale;

    protected Entities type;


    public Entity(double x, double y)
    {
        rendererNode = new Node<Entity>(this);
        handlerNode = new Node<Entity>(this);
        vec = new Vec2d();
        vec.x = x;
        vec.y = y;
    }

    public Vec2d getVec()
    {
        return vec;
    }

    public Node<Entity> getRenderNode()
    {
        return rendererNode;
    }

    public Node<Entity> getHandlerNode()
    {
        return handlerNode;
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

    public void render(Graphics2D g, int xOffset, int yOffset)
    {
        
    }

    public Entities getType()
    {
        return type;
    }

}