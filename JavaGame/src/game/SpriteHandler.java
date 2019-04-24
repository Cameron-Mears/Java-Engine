package game;

import java.util.Iterator;
import java.util.LinkedList;

import graphics.Sprite;

public class SpriteHandler
{
    public static LinkedList<Sprite> sprites = new LinkedList<Sprite>();

    public void update(double deltaNS)
    {
        Iterator<Sprite> iterator = sprites.iterator();
        while (iterator.hasNext())
        {
            iterator.next().update(deltaNS);
        }
    }

    public void add(Sprite spr)
    {
        sprites.add(spr);
    }

    public void remove(Sprite spr)
    {
        sprites.remove(spr);
    }


}