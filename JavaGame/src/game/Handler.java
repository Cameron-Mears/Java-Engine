package game;

import java.util.LinkedList;

import entities.Entity;

import java.awt.Graphics2D;
import java.util.Iterator;

/*
    This class handles all entities in the game, gameEntities is for entities that need to be update each game tick
    render enities need to be re rendered each time render is called, just methods for adding/removing entities from the
    handler
*/

public class Handler
{
    private LinkedList<Entity> gameEntities = new LinkedList<Entity>();
    private LinkedList<Entity> renderEntities = new LinkedList<Entity>();
    private LinkedList<Timer> timers = new LinkedList<Timer>();

    public void updateTimers()
    {
        Iterator<Timer> iterator = timers.iterator();

        while (iterator.hasNext())
        {
            Timer temp = iterator.next();
            temp.update(Game.deltaMS);
        }
    }

    public void tick()
    {
        Iterator<Entity> iterator = gameEntities.iterator();

        while (iterator.hasNext())
        {
            Entity temp = iterator.next();
            temp.tick();
        }
    }

    public void render(Graphics2D g)
    {
        Iterator<Entity> iterator = renderEntities.iterator();

        while (iterator.hasNext())
        {
            Entity temp = iterator.next();
            temp.render(g);
        }
    }
    public void removeTimer(Timer t)
    {
        timers.remove(t);
    }

    public void addTimer(Timer t)
    {
        timers.add(t);
    }
    public void tickAdd(Entity entity)
    {
        gameEntities.add(entity);
    }

    public void tickRemove(Entity entity)
    {
        gameEntities.remove(entity);
    }

    public void renderAdd(Entity entity)
    {
        renderEntities.add(entity);
    }

    public void renderRemove(Entity entity)
    {
        renderEntities.remove(entity);
    }

    public LinkedList<Entity> getTickEntities()
    {
        return gameEntities;
    }

    public LinkedList<Entity> getRenderEntities()
    {
        return renderEntities;
    }
}