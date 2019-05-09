package entities.entitylist;

import entities.Entity;

public class EntityNode
{
    public EntityNode last;
    public EntityNode next;
    public Entity entity;
    public EntityList list;

    public EntityNode freeNode()
    {
        if (last != null)
        {
            last.next = next;
            next = last = null;
        }
        else
        {
            list.first = next;
            next = last = null;
        }
        if (list.last == this) list.last = null;

        return this;
    }
}