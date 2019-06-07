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
import gameobjects.weapons.AmmoType;
import gameobjects.weapons.TommyGun;
import graphics.Camera;
import graphics.ImageParser;
import graphics.Sprite;
import physics.PhysicsCal;
import physics.Vec2d;

public class Player extends Entity 
{
    /*
     * images for sprites are contained in static folders to reduce RAM usage and
     * computing time as ImageParser.parseFolder() is very slow. (like 3ms per
     * frame)
     */
    private static BufferedImage[] images = ImageParser.parseFolder(new File(System.getProperty("user.dir") + "\\JavaGame\\assets\\sprites\\player"));
    private static BufferedImage[] xflipImages = ImageParser.flipImages(images);
    private Clip test = Audio.parseSound(new File(System.getProperty("user.dir") + "\\JavaGame\\assets\\sounds\\testing\\test.wav"));

    private Sprite sprite;
    private double angle = 0.1;
    private Camera camera;
    private TommyGun tommy = new TommyGun(0, 0, 0, 0, 0, AmmoType.ASSUALT_RIFLE);

    public Player(double x, double y) {
        super(x, y);
        sprite = new Sprite(xflipImages, 15);
        addSprite(sprite);
        xScale = 1;
        yScale = 1;

        width = images[0].getWidth();
        height = images[0].getHeight();

        type = Entities.Player;
        camera = getCamera();
        camera.setMode(Camera.Mode.Center);
        camera.setTarget(this);
    }

    @Override
    public void tick()
    {
        if (keyDown('A')) vec.x += -8;
        if (keyDown('D')) vec.x += 8;
        if (keyDown(KeyEvent.VK_RIGHT)) vec.direction += 1;
        if (keyDown(KeyEvent.VK_SPACE)) sprite.setImages(images);
        if (keyDown('W')) vec.y += -8;
        if (keyDown('S')) vec.y += 8;
        angle = directionPoint(vec.x, vec.y, mouseX(), mouseY());
        if (!checkGridCollision(Game.level.grid, this, nextX(vec), nextY(vec))) vecUpdate(deltaSEC(), vec);
        else
        {
            vec.velX = 0;
            vec.acelX = 0;
            vec.acelY = 0;
            vec.velY = 0;
        }
        getDirection(vec);

        camera.setPos((int)(vec.x + width / 2 * xScale), (int)(vec.y + height / 2 * yScale));

    }

    @Override
    public void render(Graphics2D g)
    {
        BufferedImage image = sprite.currentFrame();
        AffineTransform af = new AffineTransform();
        af.setToTranslation(vec.x, vec.y);
        af.rotate(0, image.getWidth()/2 * xScale, image.getHeight()/2 * yScale);
        af.scale(xScale, yScale);
        g.drawImage(image, af, null);

        af = new AffineTransform();
        af.setToTranslation(vec.x + 12, vec.y + 25);
        af.scale(1.5, 1.5);
        af.rotate(toRadians(-angle), 5, 8);
        tommy.render(g, af);
    }
}