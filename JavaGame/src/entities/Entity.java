package entities;

import game.input.Input;
import physics.Vec2d;

public abstract class Entity implements Input
{
    private Vec2d vec;
    private int mass;

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