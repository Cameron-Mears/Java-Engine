package entities.entitylist;

import entities.Entity;

public class EntityIterator
{
    private EntityNode current;

    public EntityIterator(EntityList list)
    {
        this.current = list.getFirst();
    }

    public boolean hasNext()
    {
        return current.next != null;
    }

    public Entity getNext()
    {
        Entity temp = current.entity;
        current = current.next;
        return temp;
    }
}