package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import game.Gamecore;
import game.SpriteHandler;
import graphics.ImageParser;
import graphics.Sprite;
import physics.PhysicsCal;
import physics.Vec2d;

public class Player extends Entity
{
    private static BufferedImage[] images = ImageParser.parseFolder(new File(System.getProperty("user.dir") + "\\JavaGame\\assets\\sprites"));
    private Sprite sprite;

    public Player(double x, double y)
    {
        super( x, y);
        sprite = new Sprite(images, 15);
        addSprite(sprite);
    }

    @Override
    public void tick()
    {
        if (keyDown('A')) vec.x -= 1;
        if (keyDown('D')) vec.x += 1;
        vec = vecUpdate(deltaSEC(), vec);
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1000, 1000);
        g.drawImage(sprite.currentFrame(), (int) vec.x, (int) vec.y, null);
    }
}