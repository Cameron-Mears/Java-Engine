package entities.entitylist;

import entities.Entity;

public class EntityNode
{
    /*
    Node for list holds to pointers to neighbors
    and to it's parent entity
    */
    public EntityNode last;
    public EntityNode next;
    public Entity entity;
    public EntityList list;

    public boolean isFirst;
    public boolean isLast;

    /*
        This will free the node from the list in which it is contained,
        so that the object "dies", or so that it can be quickly switched over to another list.
    */
    public void freeNode()
    {
       if (isFirst)
       {
            if (next != null)
            {
                list.first = next;
                next.isFirst = true;
                isFirst = false;
                next = last = null;
                return;
            }
            list.first = null;
            isFirst = false;
            next = last = null;
            return;
       }
       if (isLast)
       {
            list.last = last;
            last.next = null;
            isLast = false;
            next = last = null;
            return;
       }
       
       last.next = next;
       next.last = last;
       last = next = null;
       return;


       
    }
}