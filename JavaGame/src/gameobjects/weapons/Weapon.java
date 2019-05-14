package gameobjects.weapons;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import game.Functions;
import game.Game;
import game.Gamecore;
import game.Timer;
import graphics.Sprite;

public abstract class Weapon implements Functions
{
    protected static BufferedImage[] images;
    protected double damage;
    protected AmmoType ammoType;
    protected Elements element;
    protected int currentAmmo;
    protected int magSize;
    protected double fireRate;
    protected double fireDelay;
    protected double fireWait;
    protected double accuarcy;
    protected double reloadSpeed;
    protected Sprite sprite;
    protected Timer reloadTimer;

    public Weapon(double fireRate, double accuarcy, double reloadSpeed, double damage, int magSize, AmmoType ammoType)
    {
        this.fireRate = fireRate;
        this.fireDelay = 1000/fireRate;
        this.fireWait = fireDelay;
    }
    public AmmoType getAmmoType()
    {
        return ammoType;
    }

    public double getAccuarcy()
    {
        return accuarcy;
    }

    public double setAccuarcy(double accuarcy)
    {
        return clamp(accuarcy, 0.11, 100);
    }

    protected double getDirection()
    {
        double mouseDirection = 0;
        double offSet = (100/accuarcy) - 1;
        return mouseDirection + rand(-offSet, offSet);
    }

    protected void reload()
    {
        fireWait = reloadSpeed;
    }    

    public double getDamage() 
    {
       return damage;
    }

    public void setDamage(double damage) 
    {
        this.damage = damage;
    }

    public int getMagSize() 
    {  
       return magSize; 
    }

    public void setMagsize(int magSize) 
    {
        this.magSize = magSize;
    }

    public boolean canFire()
    {
        fireWait -= Game.deltaMS;
        if (fireWait <= 0) return true;
        return false;        
    }

    public void render(Graphics2D g, AffineTransform transform)
    {
        
    }
}