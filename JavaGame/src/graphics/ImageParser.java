package graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class ImageParser
{
    public static BufferedImage[] parseFolder(File folder)
    {
        
            File resources[] = folder.listFiles();
            BufferedImage images[] = new BufferedImage[resources.length];

            for (int index = 0; index < resources.length; index ++)
            {
                try
                {
                    //parse images to folder
                    images[index] = ImageIO.read(resources[index]);
                }
                catch (IOException e)
                {
                    System.out.println("Unable to parse image --> " + resources[index].getName());
                }
            }
        
        return images;

    }
}