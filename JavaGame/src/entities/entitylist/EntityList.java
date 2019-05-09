package entities.entitylist;

import entities.Entity;

public class EntityList
{
    /*
        This is a just a linkedlist that is built only for entities
        could be changed for anything nut that is not useful at this point
        using this list will give more control of everything and storing the nodes
        in the entities will make it possible to pretty much instantly remove nodes
        from the list, instead of list.remove(Object) (O(n/2)) it is just O(1)).
    */
    public EntityNode first = null;
    public EntityNode last = null;


    public EntityList()
    {

    }

    public void add(Entity entity)
    {
        EntityNode node = entity.getNode();
        node.list = this;
        if (last != null)
        {
            node.last = last;
            node.next = null;
            last.next = node;
            last = node;
            node.isLast = true;
        }
        else
        {
            if (first == null)
            {
                node.last = node.next = null;
                node.isFirst = true;
                first = node;
            }
        }
    }

    public EntityNode getFirst()
    {
        return first;
    }
}