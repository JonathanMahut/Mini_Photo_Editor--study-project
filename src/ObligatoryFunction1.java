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


public class ObligatoryFunction1 {
	static HashMap<JButton, File> all_chosen_images;
	static List <BufferedImage> all_images = new ArrayList<BufferedImage>();
	static List <File> all_images_after = new ArrayList<File>();
	static List <BufferedImage> all_images_after_list=new ArrayList<BufferedImage>();;
	
	static int black_or_white;
	static double treshold =0;
	
	
	ObligatoryFunction1(int mode,double treshold)
	{
		ObligatoryFunction1.all_chosen_images = AddDirectory.all_chosen;
		System.out.println(all_chosen_images.size() + "size all_chosen_images zaraz po inicjalizacji");
		ObligatoryFunction1.black_or_white = mode;
		ObligatoryFunction1.treshold = treshold;
		all_images.clear();
		System.out.println(all_images.size() + "all_images po saveimagestolist");
		
		
	}
	
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
	        	
	        	//System.out.println(x + "=zmienna x ||" + img1.getHeight() + "=getHeight obrazka. || " + y + " =zmienna y||" + img1.getWidth() + " =getWidth obr");
	        	// Here the 'b' stands for 'blue' as well
	        	// as for 'brightness' :-)
	        	int b0 = argb0 & 0xFF;
	        	
	        	if(black_or_white == 1) // black
	        	{
		        	if( b0==0)
		        	{
		        		all_row = true;
		        	}
		        	else 
		        	{
		        		all_row = false;
		        		break;
		        	}
	        	}
	        	else if (black_or_white == 2) // white
	        		if(b0==255)
		        	{
		        		all_row = true;
		        	}
		        	else 
		        	{
		        		all_row = false;
		        		break;
		        	}
	        	else
	        	{
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
 	
	        }
		if(all_row==true)
			height_to_cut++; // one row is all white
			
		else
		{
			
			break;
		}
		
		}
		//height_to_cut+=3; // ogolnie to wtf kurwa mac. bez tego niedocina do konca???????????
	
		if(height_to_cut>0)
		img1 = img1.getSubimage(0, height_to_cut+1, img1.getWidth()-1, (img1.getHeight()-1) - height_to_cut);
		
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
	        	
	        	if(black_or_white == 1) // black
	        	{
		        	if(b0==0)
		        	{
		        		all_row = true;
		        	}
		        	else 
		        	{
		        		all_row = false;
		        		break;
		        	}
	        	}
	        	else if (black_or_white == 2) //white
	        		if(b0==255)
		        	{
		        		all_row = true;
		        	}
		        	else 
		        	{
		        		all_row = false;
		        		break;
		        	}
	        	else
	        	{
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
 	
	        }
		if(all_row==true)
			height_to_cut++; // one row is all white
		else
			break;
			
		}
		//height_to_cut+=3; // ogolnie to wtf kurwa mac. bez tego niedocina do konca???????????
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
	        	
	        	if(black_or_white == 1) // black
	        	{
		        	if(b0==0)
		        	{
		        		all_row = true;
		        	}
		        	else 
		        	{
		        		all_row = false;
		        		break;
		        	}
	        	}
	        	else if (black_or_white == 2)
	        		if(b0==255)
		        	{
		        		all_row = true;
		        	}
		        	else 
		        	{
		        		all_row = false;
		        		break;
		        	}
	        	else
	        	{
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
 	
	        }
		if(all_row==true)
			width_to_cut++; // one row is all white
		else
			break;
			
		}
		
		if(width_to_cut>0)
		img1 = img1.getSubimage(width_to_cut+1, 0, (img1.getWidth()-1)-width_to_cut, img1.getHeight()-1);
		
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
	        	
	        	if(black_or_white == 1) // black
	        	{
		        	if(b0==0)
		        	{
		        		all_row = true;
		        	}
		        	else 
		        	{
		        		all_row = false;
		        		break;
		        	}
	        	}
	        	else if (black_or_white == 2) // white
	        		if(b0==255)
		        	{
		        		all_row = true;
		        	}
		        	else 
		        	{
		        		all_row = false;
		        		break;
		        	}
	        	else
	        	{
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
 	
	        }
		if(all_row==true)
			width_to_cut++; // one row is all white
		else
			break;
			
		}
		if(width_to_cut>0)
		img1 = img1.getSubimage(0, 0, (img1.getWidth()-1)-width_to_cut, img1.getHeight()-1);
		
	return img1;
		
	}
	
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
	
	 static List<File> cutOut()
	 {	
		 all_images = ObligatoryFunction1.saveImagesToList();
		 int counter =0;	
		 for(BufferedImage tmp : all_images)
		 {
			 BufferedImage result = cutFrame(tmp);
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
	 
	 static List<BufferedImage> cutOut1()
	 {
		 all_images = ObligatoryFunction1.saveImagesToList();
		 for(BufferedImage tmp : all_images)
		 {
			 //BufferedImage result = getCroppedImage(tmp,treshold);  //????????????????????????????????
			 BufferedImage result = cutFrame(tmp);
			all_images_after_list.add(result);
		 }

		 
		 return all_images_after_list;
	 }
	
	 public static BufferedImage getCroppedImage(BufferedImage source, double tolerance) {
		   // Get our top-left pixel color as our "baseline" for cropping
		 int baseColor = 0;
		 //if(black_or_white == 1)
			  baseColor =  source.getRGB(0, 0);
		/* else if (black_or_white == 2)
			 baseColor =  -1;
		 else
			 baseColor = Color.white.getRGB();*/
		 
		 //System.out.println(baseColor + "=baseColor" + black_or_white + "mode");
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



