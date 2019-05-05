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