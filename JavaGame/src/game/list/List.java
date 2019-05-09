package game.list;

import entities.Entity;
public class List<Type>
{
    /*
        This is a just a linkedlist that is built only for entities
        could be changed for anything nut that is not useful at this point
        using this list will give more control of everything and storing the nodes
        in the entities will make it possible to pretty much instantly remove nodes
        from the list, instead of list.remove(Object) (O(n/2)) it is just O(1)).
    */
    public Node first = null;
    public Node last = null;


    public List()
    {

    }

    public void add(Node node)
    {

        node.list = this;
        if (last != null)
        {
            node.last = last;
            node.next = null;
            last.next = node;
            last.isLast = false;
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
                last = node;
            }
        }
    }

    public Node getFirst()
    {
        return first;
    }
}