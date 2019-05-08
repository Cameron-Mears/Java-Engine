package entities.entitylist;

import entities.Entity;

public class EntityNode
{
    public EntityNode last;
    public EntityNode next;
    public Entity entity;

    public EntityNode removeNode()
    {
        last.next = next;
        return this;
    }
}