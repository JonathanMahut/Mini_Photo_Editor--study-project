import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDesktopPane;

/**
 * The class which purpose is to cut out the frame.
 * @author Robert Kubica
 *
 */
public class ObligatoryFunction1 {
	/**
	 * Images  as files which are values for JButtons. 
	 * 
	 */
	static HashMap<JButton, File> all_chosen_images;
	/**
	 * The list for selected images from given directories.
	 */
	static List <BufferedImage> all_images = new ArrayList<BufferedImage>();
	/**
	 * The list for all images (as files) on which there were proceed cut out function.
	 */
	static List <File> all_images_after = new ArrayList<File>();
	/**
	 * 
	 * The list for all images on which there were proceed cut out function.
	 */
	static List <BufferedImage> all_images_after_list=new ArrayList<BufferedImage>();;
	
	/**
	 * The variable storing option of cutting.
	 * 1- cut out the black background.
	 * 2 - cut out the white background.
	 */
	static int black_or_white;
	/**
	 * The variable storing tolerance for the brightness of the background which should be cut.
	 * If it is high then the more brighter  pixel will be cut including the darker pixels too.
	 */
	static double treshold =0;
	
	/**
	 * constructor
	 * @param mode which background should be cut.
	 * @param treshold Tolerance of the pixel to cut.
	 */
	ObligatoryFunction1(int mode,double treshold)
	{
		ObligatoryFunction1.all_chosen_images = AddDirectory.all_chosen;
		System.out.println(all_chosen_images.size() + "size all_chosen_images zaraz po inicjalizacji");
		ObligatoryFunction1.black_or_white = mode;
		ObligatoryFunction1.treshold = treshold;
		all_images.clear();
		System.out.println(all_images.size() + "all_images po saveimagestolist");
		
		
	}
	/**
	 * Main function where there is proceeding cutting of the background.
	 * @param img_to_cut The image on which there will be proceed cutting function.
	 * @return The image without white or black background.
	 */
	static BufferedImage cutFrame(BufferedImage img_to_cut)
	{
		BufferedImage img1 = img_to_cut;
		int width_to_cut = 0;
		int height_to_cut = 0;
		boolean all_row = false;
		
		
		////////////////////////////////////////
		//// from top
		for(int y = 0; y < img1.getHeight(); y++)
		{	
	        for(int x = 0; x < img1.getWidth(); x++) 
	        {
	        	int argb0 = img1.getRGB(x,y);
	        	

	        	// Here the 'b' stands for 'blue' as well
	        	// as for 'brightness' :-)
	        	int b0 = argb0 & 0xFF;

	        		if(b0 < treshold )
	        		{
	        			all_row = true;
	        		}
	        		
	        		else
	        		{
	        			all_row = false;
	        			
	        			break;
	        		}
	        	
 	
	        }
		if(all_row==true)
			height_to_cut++; // one row is all white
		
		else

			break;
		
		
		}
	
		if(height_to_cut>0)
		{
			System.out.println(img1.getHeight() + "wysokosc obrazka" + height_to_cut + "wysokosc do ciecia");
			img1 = img1.getSubimage(0, height_to_cut-1, img1.getWidth(), (img1.getHeight()) - height_to_cut+1);

		}
		
		/////////////////////////////////////////
						//from bottom
		height_to_cut=0;
		width_to_cut=0;
		for(int y = img1.getHeight()-1; y >0 ; y--)
		{	
	        for(int x = 0; x < img1.getWidth()-1; x++)  // od gory
	        {
	        	
	        	int argb0 = img1.getRGB(x, y);

	        	// Here the 'b' stands for 'blue' as well
	        	// as for 'brightness' :-)
	        	int b0 = argb0 & 0xFF;
	        	
	        
	        		if(b0 < treshold )
	        		{
	        			all_row = true;
	        		}
	        		
	        		else
	        		{
	        			all_row = false;
	        			break;
	        		}
	        	
 	
	        }
		if(all_row==true)
			height_to_cut++; // one row is all white
		else
			break;
			
		}
		
		if(height_to_cut>0)
		img1 = img1.getSubimage(0, 0, img1.getWidth()-1, (img1.getHeight()-1) -height_to_cut);
		
		
		//////////////////////////////////////////
				//from left
		height_to_cut=0;
		width_to_cut=0;
		for(int x = 0; x < img1.getWidth()-1 ; x++)
		{	
	        for(int y = 0; y < img1.getHeight()-1; y++)  
	        {
	        	int argb0 = img1.getRGB(x, y);

	        	// Here the 'b' stands for 'blue' as well
	        	// as for 'brightness' :-)
	        	int b0 = argb0 & 0xFF;

	        		if(b0 < treshold )
	        		{
	        			all_row = true;
	        		}
	        		
	        		else
	        		{
	        			all_row = false;
	        			break;
	        		}

	        }
		if(all_row==true)
			width_to_cut++; // one row is all white
		else
			break;
			
		}
		
		if(width_to_cut>0)
		img1 = img1.getSubimage(width_to_cut-1, 0, (img1.getWidth())-width_to_cut+1, img1.getHeight());
		
		/////////////////////////////////
					// from right
		height_to_cut=0;
		width_to_cut=0;
		for(int x = img1.getWidth()-1; x>0 ; x--)
		{	
	        for(int y = 0; y < img1.getHeight()-1; y++)  
	        {
	        	int argb0 = img1.getRGB(x, y);

	        	// Here the 'b' stands for 'blue' as well
	        	// as for 'brightness' :-)
	        	int b0 = argb0 & 0xFF;
	        	
	        	
	        		if(b0 < treshold )
	        		{
	        			all_row = true;
	        		}
	        		
	        		else
	        		{
	        			all_row = false;
	        			break;
	        		}
	        	
 	
	        }
		if(all_row==true)
			width_to_cut++; // one row is all white
		else
			break;
			
		}
		if(width_to_cut>0)
		img1 = img1.getSubimage(0, 0, (img1.getWidth())-width_to_cut, img1.getHeight());
		
	return img1;
		
	}
	/**
	 * Method in which the selected images from the hashmap are transferring into the list.
	 * @return List of selected images.
	 */
	  static List<BufferedImage> saveImagesToList()
     {	
		  
             for(File i : all_chosen_images.values())
             {
            	 	
                     try {
                             all_images.add(ImageIO.read(i));
                     } catch (IOException e) {
                             // TODO Auto-generated catch block
                             e.printStackTrace();
                     }
             
             }
             
           
            
             return all_images;
             
             
             
     }
	/**
	 * 
	 * The method which perform cutting the background on all images saved in the list(as files) with selected images.
	 * @return List of the images as files without the background.
	 */
	 static List<File> cutOut()
	 {	
		 all_images = ObligatoryFunction1.saveImagesToList();
		 int counter =0;	
		 for(BufferedImage tmp : all_images)
		 {	
			 BufferedImage result;
			 if(black_or_white==1 || black_or_white == 2)
				result = getCroppedImage(tmp,0.8);
			 else
			 result = cutFrame(tmp);
			//BufferedImage result = getCroppedImage(tmp,treshold); //????????????????????????????????
			
			File f;
			  f = new File( "image" +counter +".png" );  
		        try
		        {  
		           
		         ImageIO.write( result, "PNG", f );  
		 
		        }  
		        catch ( IOException x )
		        {  
		            // Complain if there was any problem writing  
		            // the output file.  
		            x.printStackTrace();  
		        }    
		        all_images_after.add(f);
		        counter++;
		 }
		 
		 
		 return all_images_after;
	 }
	 /**
		 * 
		 * The method which perform cutting the background on all images saved in the list(as BufferedImage) with selected images.
		 * @return List of the images as BufferedImage without the background.
		 */
	 static List<BufferedImage> cutOut1()
	 {
		 all_images = ObligatoryFunction1.saveImagesToList();
		 for(BufferedImage tmp : all_images)
		 {
			 BufferedImage result;
			 if(black_or_white==1 || black_or_white == 2)
				result = getCroppedImage(tmp,0.8);
			 else
			 result = cutFrame(tmp);
			all_images_after_list.add(result);
		 }

		 
		 return all_images_after_list;
	 }
	
	 public static BufferedImage getCroppedImage(BufferedImage source, double tolerance) {
		   // Get our top-left pixel color as our "baseline" for cropping
		   int baseColor = source.getRGB(0, 0);

		   int width = source.getWidth();
		   int height = source.getHeight();

		   int topY = Integer.MAX_VALUE, topX = Integer.MAX_VALUE;
		   int bottomY = -1, bottomX = -1;
		   for(int y=0; y<height; y++) {
		      for(int x=0; x<width; x++) {
		         if (colorWithinTolerance(baseColor, source.getRGB(x, y), tolerance)) {
		            if (x < topX) topX = x;
		            if (y < topY) topY = y;
		            if (x > bottomX) bottomX = x;
		            if (y > bottomY) bottomY = y;
		         }
		      }
		   }

		   BufferedImage destination = new BufferedImage( (bottomX-topX+1), 
		                 (bottomY-topY+1), BufferedImage.TYPE_INT_ARGB);

		   destination.getGraphics().drawImage(source, 0, 0, 
		               destination.getWidth(), destination.getHeight(), 
		               topX, topY, bottomX, bottomY, null);

		   return destination;
		}

		private static boolean colorWithinTolerance(int a, int b, double tolerance) {
		    int aAlpha  = (int)((a & 0xFF000000) >>> 24);   // Alpha level
		    int aRed    = (int)((a & 0x00FF0000) >>> 16);   // Red level
		    int aGreen  = (int)((a & 0x0000FF00) >>> 8);    // Green level
		    int aBlue   = (int)(a & 0x000000FF);            // Blue level

		    int bAlpha  = (int)((b & 0xFF000000) >>> 24);   // Alpha level
		    int bRed    = (int)((b & 0x00FF0000) >>> 16);   // Red level
		    int bGreen  = (int)((b & 0x0000FF00) >>> 8);    // Green level
		    int bBlue   = (int)(b & 0x000000FF);            // Blue level

		    double distance = Math.sqrt((aAlpha-bAlpha)*(aAlpha-bAlpha) +
		                                (aRed-bRed)*(aRed-bRed) +
		                                (aGreen-bGreen)*(aGreen-bGreen) +
		                                (aBlue-bBlue)*(aBlue-bBlue));

		    // 510.0 is the maximum distance between two colors 
		    // (0,0,0,0 -> 255,255,255,255)
		    double percentAway = distance / 510.0d;     

		    return (percentAway > tolerance);
		}
}


