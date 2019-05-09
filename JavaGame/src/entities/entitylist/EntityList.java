package entities.entitylist;

import entities.Entity;

public class EntityList
{
       private EntityNode first = null;
       private EntityNode last = null;

       public EntityList()
       {

       }

       public void add(Entity entity)
       {
              if (last != null)
              {
                     entity.getNode().last = last;
                     last.next = entity.getNode();
              }
              else
              {
                     System.out.println("hi");
                     if (first == null) first = entity.getNode();
                     System.out.println(first);
                     last = entity.getNode();
                     return;
              }
       }

       public EntityNode getFirst()
       {
              return first;
       }
}