package gameobjects.weapons;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import game.Timer;
import graphics.ImageParser;
import graphics.Sprite;

public class TommyGun extends Weapon
{
    private static BufferedImage[] images = ImageParser.parseFolder(new File(System.getProperty("user.dir") + "\\JavaGame\\assets\\sprites\\tommy gun"));;

    public TommyGun(double fireRate, double accuarcy, double reloadSpeed, double damage, int magSize, AmmoType ammoType)
    {
        super(fireRate, accuarcy, reloadSpeed, damage, magSize, ammoType);
        this.magSize = magSize;
        reloadTimer = new Timer(this.fireDelay);
        sprite = new Sprite(images, 30);
        addSprite(sprite);
    }

    @Override
    public void render(Graphics2D g, AffineTransform transform)
    {
        g.drawImage(sprite.currentFrame(), transform, null);
    }
}