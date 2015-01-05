import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;


public class MergeImageAND {
	
	
	static BufferedImage[] input;
	static HashMap<JButton,File> list_of_images;
	

	MergeImageAND(HashMap<JButton,File> images)
	{
		list_of_images=images;
		input = new BufferedImage[list_of_images.size()]; 
		MergeImageAND.main(null);
		
	}
	
	private static void getImagesFromHashmapIntoArray()
	{

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
	}
	
	private static BufferedImage xorImages(BufferedImage img1,BufferedImage img2)
	{
		BufferedImage img_merged = new BufferedImage(img1.getWidth(), img1.getHeight(), img1.getType());
	
		for(int x = 0; x < img1.getWidth(); x++)	
	        for(int y = 0; y < img1.getHeight(); y++) 
	        {
	        	int argb0 = img1.getRGB(x, y);
	        	int argb1 = img2.getRGB(x, y);
	
	        	// Here the 'b' stands for 'blue' as well
	        	// as for 'brightness' :-)
	        	int b0 = argb0 & 0xFF;
	        	int b1 = argb1 & 0xFF;
	        	int bDiff = 0;
	        	if(b1>b0 || b1==b0)
	        	{
	        		bDiff = b0;
	        	}
	        	else if(b1<b0)
	        	{
	        		bDiff = b1;
	        	}
	        	
	
	        	int diff = 
	            	    (255 << 24) | (bDiff << 16) | (bDiff << 8) | bDiff;
	        	
	            img_merged.setRGB(x, y, diff);
	        }
		
		return img_merged;
		      
	}
	
	private static BufferedImage mergeAll()
	{
		BufferedImage image = null;
		
		image = xorImages(input[0],input[1]);
	
		for(int i = 2; i<input.length-1;i++ ) //is input.length-1 correct?
		{
			image = xorImages(image,input[i]);
		}
		return image;
	}
	
	 public static void main(String[] args) 
	 {  
		BufferedImage final_img;
		 getImagesFromHashmapIntoArray();
		 
		final_img=mergeAll();
		 
		File f = new File( "image.png" );  
         try {  
             ImageIO.write( final_img, "PNG", f );  
             
         }  
         catch ( IOException x ) {  
             // Complain if there was any problem writing   
             // the output file.  
             x.printStackTrace();  
         }         
	 }
}
