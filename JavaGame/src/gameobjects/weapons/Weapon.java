package gameobjects.weapons;

import graphics.Sprite;

public abstract class Weapon
{
    protected double damage;
    protected AmmoTypes ammoType;
    protected int currentAmmo;
    protected int magSize;
    protected double fireRate;
    protected double accuarcy;
    protected double reloadSpeed;
    protected Sprite sprite;

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
}