package physics;

/*
    runs calculations for new positions, velocities for entities
*/
public interface PhysicsCal
{
    default Vec2d vecUpdate(double deltaS, Vec2d vec)
    {

        double velX0 = vec.velX;
        double velY0 = vec.velY;

        vec.velX += (vec.acelX * deltaS);
        vec.velY += (vec.acelY * deltaS);

        vec.x += ((vec.velX + velX0)/2) * deltaS;
        vec.y += ((vec.velY + velY0)/2) * deltaS;

        return vec;
    }

}