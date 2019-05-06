package gameobjects.weapons;

import java.awt.image.BufferedImage;

public class Pistol extends Weapon
{
    private BufferedImage images;

    public Pistol(double fireRate, double accuarcy, double reloadSpeed, double damage, int magSize, AmmoType ammoType, BufferedImage[] images)
    {
        super(fireRate, accuarcy, reloadSpeed, damage, magSize, ammoType);
    }
}