package entities.entitylist;

import entities.Entity;

public class EntityIterator
{
    /*
    List iterator for the entity list
    */
    private EntityNode current;

    public EntityIterator(EntityList list)
    {
        this.current = list.getFirst();
    }

    public boolean hasNext()
    {
        return current != null;
    }

    public Entity getNext()
    {
        Entity temp = current.entity;
        current = current.next;
        return temp;
    }
}