package physics;

/*
    runs calculations for new positions, velocities for entities
*/
public interface PhysicsCal
{
    default Vec2d vecUpdate(double deltaS, Vec2d vec)
    {
        vec.x += (vec.velX * deltaS);
        vec.y += (vec.velY * deltaS);

        vec.x += (vec.acelX * deltaS * deltaS)/2;
        vec.y += (vec.acelY * deltaS * deltaS)/2;

        vec.velX += (vec.acelX * deltaS);
        vec.velY += (vec.acelY * deltaS);

        return vec;
    }

}