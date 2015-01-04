    import java.awt.Graphics;  
import java.awt.MediaTracker;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
       
import java.util.HashMap;

import javax.imageio.ImageIO;  
import javax.swing.JButton;
       
    public class LayeredImage {  
    	static HashMap<JButton,File> list_of_images;
    	public LayeredImage(HashMap<JButton,File> images)
    	{
    		list_of_images = images;
    		System.out.println("Working!1");
    		LayeredImage.main(null);
    	}
       
        public static void main(String[] args) {  
              
            // Array of input images.  
            BufferedImage[] input = new BufferedImage[list_of_images.size()];  
            System.out.println("Working!2");  
            // Load each input image.  
           int counter = 0;
            for ( File i : list_of_images.values() ) {  
                try {  
                    File f = i;   
                    input[counter] = ImageIO.read( f );  
                    counter++;
                }  
                catch ( IOException x ) {  
                    // Complain if there is any problem loading   
                    // an input image.  
                    x.printStackTrace();  
                }  
            }  
              
            // Create the output image.  
            // It is the same size as the first  
            // input image.  I had to specify the type  
            // so it would keep it's transparency.  
            BufferedImage output = new BufferedImage(   
                    input[0].getWidth(),   
                    input[0].getHeight(),   
                    BufferedImage.TYPE_INT_ARGB );  
              
            // Draw each of the input images onto the  
            // output image.  
            Graphics g = output.getGraphics();  
            for ( int i = 0; i < input.length; i++ ) {  
                g.drawImage( input[i], 0, 0, null );  
            }  
              
            // Create the output image file and write the  
            // output image to it.  
            File f = new File( "image.png" );  
            try {  
                ImageIO.write( output, "PNG", f );  
                
            }  
            catch ( IOException x ) {  
                // Complain if there was any problem writing   
                // the output file.  
                x.printStackTrace();  
            }         
        }  
    }  