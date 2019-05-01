package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.Clip;

import game.Audio;
import game.Game;
import game.Gamecore;
import game.SpriteHandler;
import game.input.MouseButtons;
import graphics.ImageParser;
import graphics.Sprite;
import physics.PhysicsCal;
import physics.Vec2d;

public class Player extends Entity
{
    /*
     * images for sprites are contained in static folders to reduce RAM usage and
     * computing time as ImageParser.parseFolder() is very slow. (like 3ms per frame)
     */
    private static BufferedImage[] images = ImageParser.parseFolder(new File(System.getProperty("user.dir") + "\\JavaGame\\assets\\sprites\\player"));
    private static BufferedImage[] xflipImages = ImageParser.flipImages(images);
    private Clip test;

    private Sprite sprite;
    private double angle = 0.1;

    public Player(double x, double y)
    {
        super( x, y);
        sprite = new Sprite(xflipImages, 15);
        addSprite(sprite);
        xScale = 0.5;
        yScale = 0.5;

        width = images[0].getWidth();
        height = images[0].getHeight();

        type = Entities.Player;
    }

    @Override
    public void tick()
    {
        if (keyDown('A')) if (!checkGridCollision(Game.level.grid, this, vec.x - 1, vec.y)) vec.x += -1;
        if (keyDown('D')) if (!checkGridCollision(Game.level.grid, this, vec.x + 1, vec.y)) vec.x += 1;
        if (keyDown(KeyEvent.VK_RIGHT)) angle += 0.1;
        if (keyDown(KeyEvent.VK_SPACE)) sprite.setImages(images);
        if (keyDown('W')) if (!checkGridCollision(Game.level.grid, this, vec.x, vec.y -1)) vec.y += -1;
        if (keyDown('S')) if (!checkGridCollision(Game.level.grid, this, vec.x, vec.y + 1)) vec.y += 1;
        if (mousePressed(MouseButtons.LEFT)) Audio.parseSound(new File(System.getProperty("user.dir") + "\\JavaGame\\assets\\sounds\\testing\\test.wav")).start();
        vecUpdate(deltaSEC(), vec);

    }

    @Override
    public void render(Graphics2D g)
    {
        BufferedImage image = sprite.currentFrame();
        AffineTransform af = new AffineTransform();
        af.setToTranslation(vec.x, vec.y);
        af.rotate(angle, image.getWidth()/2 * xScale, image.getHeight()/2 * yScale);
        af.scale(xScale, yScale);
        g.drawImage(image, af, null);
    }
}