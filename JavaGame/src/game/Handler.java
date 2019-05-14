package game;


import entities.Entity;
import game.list.*;

import java.awt.Graphics2D;

/*
    This class handles all entities in the game, gameEntities is for entities that need to be update each game tick
    render enities need to be re rendered each time render is called, just methods for adding/removing entities from the
    handler
*/

public class Handler
{
    private List<Entity> gameEntities = new List<Entity>();


    public void tick()
    {
        Iterator<Entity> iterator = new Iterator<Entity>(gameEntities);
        while (iterator.hasNext())
        {
            iterator.getNext().tick();
        }
    }

    public void add(Entity entity)
    {
        Node<Entity> node = entity.getHandlerNode();
        gameEntities.add(node);
    }


    public void remove(Entity entity)
    {
        entity.getHandlerNode().free();
    }

    public List<Entity> getGameEntities()
    {
        return gameEntities;
    }


}