
package SolitaireGUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Trey Baker [21155292]
 */
public class ImageReader {
    

    
    public static BufferedImage readImage(String path){
        try {
           return ImageIO.read(new File("src/images/"+path));
        } catch (IOException e) {
        }
        return null;
    }
}
