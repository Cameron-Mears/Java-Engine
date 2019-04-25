package entities;

import java.awt.Graphics;

import game.Gamecore;
import game.input.Input;
import physics.PhysicsCal;
import physics.Vec2d;

public abstract class Entity implements Input, Gamecore, PhysicsCal
{
    protected Vec2d vec;
    protected int mass;

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

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        
    }
}