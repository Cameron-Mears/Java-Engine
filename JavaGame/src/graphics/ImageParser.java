package graphics;

import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Functions;

public class ImageParser implements Functions
{

    public BufferedImage[] parseFolder(File folder)
    {
            GraphicsConfiguration gc = graphicsConfig();
            System.out.println(gc);
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

                    
                }
                catch (IOException e)
                {
                    System.out.println("Unable to parse image --> " + resources[index].getName());
                }
            }
        
        return images;

    }
}