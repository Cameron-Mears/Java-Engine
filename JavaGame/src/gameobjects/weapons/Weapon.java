package gameobjects.weapons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Game;
import game.Gamecore;
import graphics.Sprite;

public abstract class Weapon
{
    protected static BufferedImage[] images;
    protected double damage;
    protected AmmoTypes ammoType;
    protected int currentAmmo;
    protected int magSize;
    protected double fireRate;
    protected double fireDelay;
    protected double fireWait;
    protected double accuarcy;
    protected double reloadSpeed;
    protected Sprite sprite;

    public Weapon(double fireRate)
    {
        this.fireRate = fireRate;
        this.fireDelay = 1000/fireRate;
        this.fireWait = fireDelay;
    }
    public AmmoTypes getAmmoType()
    {
        return ammoType;
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
        if(fireWait <= 0) return true;
        fireWait -= Game.deltaMS;
        return false;        
    }

    public void render(Graphics g)
    {
        
    }
}