import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;


public class Cutouts {
	static int rows =1;
	static int cols =1;
	
    static BufferedImage image = null;
    
	Cutouts(String direct_of_cut,int n_of_stripes, BufferedImage source)
	{
		
		image = source;
		
        if(direct_of_cut == "horizontal" )		// direction of cut is horizontal
        {
        	int rows = n_of_stripes;
        	int cols = 1;
        }
        else
        {
        	int rows = 1;
        	int cols = n_of_stripes;
        }
        
       
	}
	private static BufferedImage[] sliceImage ()	
	{
		
        int chunks = rows * cols;  
  
        int chunkWidth = image.getWidth() / cols; // determines the chunk width and height  
        int chunkHeight = image.getHeight() / rows;  
        int count = 0;  
        BufferedImage imgs[] = new BufferedImage[chunks];
        //Image array to hold image chunks  
        for (int x = 0; x < rows; x++)
        {  
            for (int y = 0; y < cols; y++) 
            {  
                //Initialize the image array with image chunks  
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());  
  
                // draws the image chunk  
                Graphics2D gr = imgs[count++].createGraphics();  
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            }  
        }  
        System.out.println("Splitting done");  
        return imgs;
	}
	public static  BufferedImage[] returnSlicedImage()
	{
		BufferedImage[] slicedImage = sliceImage();
		return slicedImage;
	}

}
