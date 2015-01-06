import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;


public class MergeImageOR {
	
	
	static BufferedImage[] input;
	static HashMap<JButton,File> list_of_images;
	

	MergeImageOR(HashMap<JButton,File> images)
	{
		list_of_images=images;
		input = new BufferedImage[list_of_images.size()]; 
		MergeImageOR.main(null);
		
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
	
	private static BufferedImage orImages(BufferedImage img1,BufferedImage img2)
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
	        		bDiff = b1;
	        	}
	        	else if(b1<b0)
	        	{
	        		bDiff = b0;
	        	}
	        	
	
	        	int diff = 
	            	    (255 << 24) | (bDiff << 16) | (bDiff << 8) | bDiff;
	        	
	            img_merged.setRGB(x, y, diff);
	        }
		
		return img_merged;
		      
	}
	
	
	  private static BufferedImage resizeImage(BufferedImage originalImage, int type,int destination_Widht,int destination_Height)
	  {
			BufferedImage resizedImage = new BufferedImage(destination_Widht, destination_Height, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, destination_Widht, destination_Height, null);
			g.dispose();
		 
			return resizedImage;
	  }
	
	private static BufferedImage mergeAll()
	{
		BufferedImage image = null;
		// check for the first two images if they are same resolution
		if(input[0].getHeight() * input[0].getWidth() != input[1].getHeight() * input[1].getWidth()) // check if images have same resolution
			if(input[0].getHeight() * input[0].getWidth() > input[1].getHeight() * input[1].getWidth()) // check if the first image is bigger than second
				input[1]=resizeImage(input[1],input[1].getType(),input[0].getWidth(),input[0].getHeight());
				//input[1].getScaledInstance(input[0].getWidth(), input[0].getHeight(), input[1].SCALE_DEFAULT);// enlarge smaller image to resolution of second image
			else
				input[0]=resizeImage(input[0],input[0].getType(),input[1].getWidth(),input[1].getHeight());
				//input[0].getScaledInstance(input[1].getWidth(), input[1].getHeight(), input[0].SCALE_DEFAULT);
		//make merge for first two images
		image = orImages(input[0],input[1]);
	
		for(int i = 2; i<input.length-1;i++ ) 
		{	
			if(image.getHeight() * image.getWidth() != input[i].getHeight() * input[i].getWidth()) // check if images have same resolution
			{	
				if(image.getHeight() * image.getWidth() > input[i].getHeight() * input[i].getWidth()) // check if the first image is bigger than second
					input[i]=resizeImage(input[i],input[i].getType(),image.getWidth(),image.getHeight()); // enlarge smaller image to resolution of second image
				else
					image=resizeImage(image,image.getType(),input[i].getWidth(),input[i].getHeight());
			}
			image = orImages(image,input[i]);
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
