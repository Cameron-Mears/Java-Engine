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

public class Player2 extends Entity
{
    /*
     * images for sprites are contained in static folders to reduce RAM usage and
     * computing time as ImageParser.parseFolder() is very slow. (like 3ms per frame)
     */
    private static BufferedImage[] images = ImageParser.parseFolder(new File(System.getProperty("user.dir") + "\\JavaGame\\assets\\sprites\\player"));
    private static BufferedImage[] xflipImages = ImageParser.flipImages(images);
    private Clip test = Audio.parseSound(new File(System.getProperty("user.dir") + "\\JavaGame\\assets\\sounds\\testing\\test.wav"));

    private Sprite sprite;
    private double angle = 0.1;

    public Player2(double x, double y)
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
        if (keyDown(KeyEvent.VK_LEFT)) vec.x += -1;
        if (keyDown(KeyEvent.VK_RIGHT)) vec.x += 1;
        if (keyDown(KeyEvent.VK_RIGHT)) vec.direction += 1;
        if (keyDown(KeyEvent.VK_SPACE)) sprite.setImages(images);
        if (keyDown(KeyEvent.VK_UP)) vec.y += -1;
        if (keyDown(KeyEvent.VK_DOWN)) vec.y += 1;
        if (keyDown('M')) Game.renderer.setDepth(10, this);
        if (keyDown('N')) Game.renderer.setDepth(0, this);
        if (mousePressed(MouseButtons.LEFT)) 
        {
            Audio.playClip(test);
        }
        if (!checkGridCollision(Game.level.grid, this, nextX(vec), nextY(vec))) vecUpdate(deltaSEC(), vec);
        else
        {
            vec.velX = 0;
            vec.acelX = 0;
            vec.acelY = 0;
            vec.velY = 0;
        }
        getDirection(vec);

    }

    @Override
    public void render(Graphics2D g)
    {
        BufferedImage image = sprite.currentFrame();
        AffineTransform af = new AffineTransform();
        af.setToTranslation(vec.x, vec.y);
        af.rotate(toRadians(vec.direction), image.getWidth()/2 * xScale, image.getHeight()/2 * yScale);
        af.scale(xScale, yScale);
        g.setColor(Color.blue);
        g.fillRect((int) vec.x, (int) vec.y, 32, 32);
    }
}