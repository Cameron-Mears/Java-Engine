package game;

public class Timer implements Functions
{
    protected double delay;
    protected double currentWait;
    protected boolean running;
    protected boolean finished;

    public Timer(double delayMS)
    {
        this.delay = delayMS;
            }

    public void setDelay(double delay)
    {
        this.delay = clamp(delay, 0, delay);
    }

    public void update(double deltaMS)
    {
        currentWait -= deltaMS;
        if (currentWait <= 0)
        {
            finished = true;
            disable();
        }
    }

    public void reset()
    {
        currentWait = delay;
        finished = false;
    }

    public void disable()
    {
        running = false;
    }

    public void start()
    {
        running = true;
    }

    public boolean isDone()
    {
        return finished;
    }
}