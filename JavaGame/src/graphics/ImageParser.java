package graphics;

import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Game;
import game.Gamecore;

public class ImageParser implements Gamecore
{
    /*
    Imports images from folders and tries to make them run better with the graphics config
    */

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
                    BufferedImage compatiableImage = gc.createCompatibleImage(temp.getWidth(), temp.getHeight());
                    compatiableImage.setData(temp.getData());
                    images[index] = compatiableImage;

                    
                }
                catch (IOException e)
                {
                    System.out.println("Unable to parse image --> " + resources[index].getName());
                }
            }
        
        return images;

    }
    /*
    Entities or anything that inverts images calls this method to have a second set of images
    that are inverted to increase preformance, inverting images is relativily expensive 
    Affinetransfrom takes care of mirroring image
    */
    public static BufferedImage[] flipImages(BufferedImage[] images) {
        BufferedImage[] returnImages = new BufferedImage[images.length];

        for (int index = 0; index < images.length; index++) {
            BufferedImage image = images[index];
            AffineTransform af = AffineTransform.getScaleInstance(-1, 1);
            af.translate(-image.getWidth(), 0);
            AffineTransformOp op = new AffineTransformOp(af, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            returnImages[index] = op.filter(image, null);
        }

        return returnImages;
    }

}