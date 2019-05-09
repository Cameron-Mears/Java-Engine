package entities.entitylist;

import entities.Entity;

public class EntityNode
{
    public EntityNode last;
    public EntityNode next;
    public Entity entity;

    public EntityNode freeNode()
    {
        if (last != null) last.next = next;
        return this;
    }
}