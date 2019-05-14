package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import entities.Entity;
import game.list.*;
import gameobjects.Block;
import physics.Vec2d;
import game.Functions;
import game.Game;
import game.Level;
import game.Window;

public class Renderer extends Canvas implements Functions
{
    private static final long serialVersionUID = -8352885578412682145L;
    private static final int layers = 10;
    /*
        renderer holds all things to get rendered,
        and creates a drawable canvas.
        the number of layers is the depth range
    */
    private List<Entity>[] depths = new List[layers];    
    private BufferStrategy bs;
    public static Camera camera;


    public Renderer(Window window)
    {
        //this.setSize(window.getWindow().getWidth(), window.getWindow().getHeight());
        window.getWindow().add(this);
        camera = new Camera(1760 / 2, 992/2, Camera.Mode.Box, null);
        for (int index = 0; index < depths.length; index++)
        {
            depths[index] = new List<Entity>();
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
        int camX = camera.getXOffset();
        int camY = camera.getYOffset();
        camX = clamp(camX, 0,  Game.level.getWidth() - camera.getWidth() + Block.getWidth()/2);
        camY = clamp(camY, 0, Game.level.getHeight() - camera.getHeight() + Block.getHeight());
        //camY = clamp(camY, 0, Game.windowHeight - camera.getHeight()/2);
        g.translate(-(camX), -camY);
        Level level = Game.level;
        if (level != null)
        {

            level.renderMain(g, Math.floorDiv(camX, Block.getWidth()), Math.floorDiv(camY, Block.getHeight()), (int)Math.ceil((camX + camera.getWidth())/Block.getWidth()) + 1,  (int)Math.ceil((camY + camera.getHeight())/Block.getHeight()) + 1);
        }
       for (List<Entity> list : depths)
       {
           Iterator<Entity> iterator = new Iterator<Entity>(list);
           while (iterator.hasNext())
           {
               Entity temp = iterator.getNext();
               Vec2d vec = temp.getVec();

                if 
                (
                    camX < vec.x + (temp.getWidth() * temp.getXScale())
                    &&
                    camX + camera.getWidth() > vec.x
                    &&
                    camY < vec.y + (temp.getHeight() * temp.getYScale())
                    &&
                    camY + camera.getHeight() > vec.y
                ) temp.render(g);
                else continue;

           }
       }

    g.translate(camX, camY);
    }



    /*
        depth system just uses an array of entitylists
        the lower the depth number, the higher depth.
        eg. depth 9 will be drawn over depth 0
        add method initally add to the renderer,
        setDepth to change the depth of an existing entity,
        to just remove an entity, call entity.getNode().freeNode()
        that will remove it from list;
    */

    public void add(int depth, Entity entity)
    {
        depth = clamp(depth, 0, layers - 1);
        depths[depth].add(entity.getRenderNode());
    }

    public void setDepth(int depth, Entity entity)
    {
        /*
        "Free", the node from the list at that depth,
        then append it to the new list at the desired depth.
        */
        depth = clamp(depth, 0, layers - 1);
        entity.getRenderNode().free();
        System.out.println(entity.getRenderNode().list.first);
        depths[depth].add(entity.getRenderNode());
        System.out.println(entity.getRenderNode().list.first);
    }

    public void show()
    {
        bs.show();
    }

}