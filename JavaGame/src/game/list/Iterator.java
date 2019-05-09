package game.list;

import entities.Entity;

public class Iterator<Type>
{
    /*
    List iterator for the entity list
    */
    private Node<Type> current;

    public Iterator(List<Type> list)
    {
        this.current = list.getFirst();
    }

    public boolean hasNext()
    {
        return current != null;
    }

    public Type getNext()
    {
        Type temp = current.data;
        current = current.next;
        return temp;
    }
}