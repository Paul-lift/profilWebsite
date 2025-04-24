package ch.paul.graphic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class ImageLoader {



    public static BufferedImage loadImage(String path){
        System.out.println("Bild laden: " + path);
        return loadImage(ImageLoader.class.getClassLoader().getResourceAsStream(path));
    }

    //loads image with path given
    public static BufferedImage loadImage(InputStream inputStream) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return image;
    }
}
