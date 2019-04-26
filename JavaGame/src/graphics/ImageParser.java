package graphics;

import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Game;
import game.Gamecore;

public class ImageParser implements Gamecore
{

    public static BufferedImage[] parseFolder(File folder)
    {
            GraphicsConfiguration gc = Game.graphicsConfig;
            File resources[] = folder.listFiles();
            BufferedImage images[] = new BufferedImage[resources.length];

            for (int index = 0; index < resources.length; index ++)
            {
                try
                {
                    //parse images to folder
                    BufferedImage temp = ImageIO.read(resources[index]);
                    BufferedImage temp2 = gc.createCompatibleImage(temp.getWidth(), temp.getHeight());
                    temp2.setData(temp.getData());
                    images[index] = temp2;

                    
                }
                catch (IOException e)
                {
                    System.out.println("Unable to parse image --> " + resources[index].getName());
                }
            }
        
        return images;

    }
}