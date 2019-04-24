package entities;

import java.awt.image.BufferedImage;

import physics.Vec2d;

public abstract class Entity
{
    private Vec2d vec;

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

    public void init()
    {

    }
}