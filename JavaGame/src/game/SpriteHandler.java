package game;

import game.list.Iterator;
import game.list.List;
import graphics.Sprite;

public class SpriteHandler
{
    /*
        Handles animation of sprites, pretty sraight foward
    */
    public static List<Sprite> sprites = new List<Sprite>();

    public void update(double deltaMS)
    {
        Iterator<Sprite> iterator =  new Iterator<Sprite>(sprites);
        while (iterator.hasNext())
        {
            iterator.getNext().update(deltaMS);
        }
    }

    public void add(Sprite spr)
    {
        sprites.add(spr.getNode());
    }

    public void remove(Sprite spr)
    {
        spr.getNode().free();
    }


}