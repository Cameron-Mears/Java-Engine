package entities.entitylist;

import entities.Entity;

public class EntityList
{
       public EntityNode first = null;
       public EntityNode last = null;

       public EntityList()
       {

       }

       public void add(Entity entity)
       {
              if (last != null)
              {
                     entity.getNode().last = last;
                     last.next = entity.getNode();
                     last.entity = entity;
                     last = entity.getNode();
              }
              else
              {
                     System.out.println("hi");
                     if (first == null) first = entity.getNode();
                     first.entity = entity;
                     last = entity.getNode();
              }
       }

       public EntityNode getFirst()
       {
              return first;
       }
}