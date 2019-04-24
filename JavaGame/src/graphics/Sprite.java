package graphics;

import java.awt.image.BufferedImage;

public class Sprite
{
    private BufferedImage images[];
    private int frames;
    private int currentFrame;
    private double animationSpeed;
    private double currentwait;

    public Sprite(BufferedImage images[], double animationSpeed)
    {
        animationSpeed = 1000000000/animationSpeed;
        this.images = images;
        this.animationSpeed = animationSpeed;
        this.currentwait = animationSpeed;
        this.frames = images.length;
        System.out.println(images.length);
    }

    public int current()
    {
        return currentFrame;
    }

    public void setFrame(int frame)
    {
        currentFrame = frame;
    }

    public void setImages(BufferedImage[] images)
    {
        this.images = images;
    }

    public void setAnimationSpeed(double animationSpeed)
    {
        this.animationSpeed = animationSpeed;
    }

    public BufferedImage currentFrame()
    {
        return images[currentFrame];
    }

    public void update(double deltaMS)
    {
        currentwait -= deltaMS;
        if (currentwait < 0)
        {
            currentwait = animationSpeed + currentwait;
            currentFrame = (currentFrame + 1 > frames - 1)? 0:currentFrame + 1;
            System.out.println(currentFrame);
        }

    }

}