package game.list;

import entities.Entity;

public class Node<Type>
{
    /*
    Node for list holds to pointers to neighbors
    and to it's parent entity
    */
    public Node last;
    public Node next;
    public Type data;
    public List list;

    public boolean isFirst;
    public boolean isLast;

    /*
        This will free the node from the list in which it is contained,
        so that the object "dies", or so that it can be quickly switched over to another list.
    */

    public Node(Type data)
    {
        this.data = data;
    }
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
            list.last = null;
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