package graphics;

import java.awt.image.BufferedImage;


public class Sprite
{
    /*
        Handles animations for things
        use static variables to hold
        the images on the entity and just
        pass the sprite a pointer to that array
        so that the iumages only need to parsed once
        versus every time the sprite is created.
    */
    private BufferedImage images[];
    private int frames;
    private int currentFrame;
    private double animationSpeed;
    private double currentwait;

    public Sprite(BufferedImage images[], double FPS)
    {
        animationSpeed = 1000/FPS;
        this.images = images;
        this.animationSpeed = animationSpeed;
        this.currentwait = animationSpeed;
        this.frames = images.length;
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
        }
    }
}