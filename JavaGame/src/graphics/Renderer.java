package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import entities.Entity;
import entities.entitylist.EntityIterator;
import entities.entitylist.EntityList;
import game.Functions;
import game.Window;

public class Renderer extends Canvas implements Functions
{
    private static final long serialVersionUID = -8352885578412682145L;
    private static final int layers = 10;
    private EntityList[] depths = new EntityList[layers];    
    private BufferStrategy bs;

    public Renderer(Window window)
    {
        //this.setSize(window.getWindow().getWidth(), window.getWindow().getHeight());
        window.getWindow().add(this);
        for (int index = 0; index < depths.length; index++)
        {
            depths[index] = new EntityList();
        }
    }

    public Graphics2D createGraphics()
    {
        bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(2);
            bs = this.getBufferStrategy();
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        return g;

    }

    public void render(Graphics2D g)
    {
        for (EntityList list : depths)
        {
            EntityIterator iterator = new EntityIterator(list);
            if (list.getFirst() == null) continue;
            do
            {
                System.out.println("sd");
                iterator.getNext().render(g);
            }
            while (iterator.hasNext());
        }
    }

    public void add(int depth, Entity entity)
    {
        depth = clamp(depth, 0, layers - 1);
        depths[depth].add(entity);
        System.out.println(depths[depth].getFirst());
    }

    public void setDepth(int depth, Entity entity)
    {
        entity.getNode().removeNode();
        depths[depth].add(entity);
    }

    public void show()
    {
        bs.show();
    }
}