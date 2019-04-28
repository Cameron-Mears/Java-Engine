package game;

public interface Functions
{
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