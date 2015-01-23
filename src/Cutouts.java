import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
 
import javax.imageio.ImageIO;
import javax.swing.JButton;
 
 /**
  * The class cutting given image into the stripes.
  * @author Napoleon
  *
  */
public class Cutouts {
	
        static int rows =1;
        static int columns =1;
       
    static BufferedImage image = null;
    static BufferedImage smallImages [][];
   
        Cutouts(String direct_of_cut,int n_of_stripes, BufferedImage source)
        {
               
                image = source;
               
        if(direct_of_cut == "H" )               // direction of cut is horizontal
        {
                this.rows = n_of_stripes;
                this.columns = 1;
        }
        else
        {
                this.rows = 1;
                this.columns = n_of_stripes;
        }
       
       
        }
        BufferedImage[][] sliceImage ()
        {
               
       // int chunks = rows * columns;  
               
        int chunkWidth = image.getWidth() / columns; // determines the chunk width and height  
        int chunkHeight = image.getHeight() / rows;  
        int count = 0;  
        smallImages = new BufferedImage[columns][rows];        
        for (int x = 0; x < columns; x++)
        {
            for (int y = 0; y < rows; y++)
            {
                smallImages[x][y] = image.getSubimage(x * chunkWidth, y
                        * chunkHeight, chunkWidth, chunkHeight);
               
            }
        }
       
       
        return smallImages;
        }
       
 
}