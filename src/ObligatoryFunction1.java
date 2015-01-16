import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;


public class ObligatoryFunction1 {
	static HashMap<JButton, File> all_chosen_images;
	static List <BufferedImage> all_images;
	static List <File> all_images_after;
	static List <BufferedImage> all_images_after_list;
	
	static int black_or_white;
	static int treshold =0;
	
	
	ObligatoryFunction1(int mode,int treshold)
	{
		ObligatoryFunction1.all_chosen_images = AddDirectory.all_chosen;
		ObligatoryFunction1.black_or_white = mode;
		ObligatoryFunction1.treshold = treshold;
		all_images = ObligatoryFunction1.saveImagesToList();
		
	}
	
	static BufferedImage cutFrame(BufferedImage img_to_cut)
	{
		BufferedImage img1 = img_to_cut;
		int width_after_cut = 0;
		int height_to_cut = 0;
		boolean all_row = false;
		int columns = img1.getWidth();
		int rows = img1.getHeight();
		
		for(int x = 0; x < rows; x++)
		{	
	        for(int y = 0; y < columns; y++)  // od gory
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
			height_to_cut++; // one row is all white
		else
			break;
			
		}
		img1 = img1.getSubimage(0, 0, img1.getWidth(), height_to_cut);
		/////////////////////////////////////////
							//from bottom
		
		for(int x = img1.getWidth(); x >0 ; x--)
		{	
	        for(int y = 0; y < columns; y++)  // od gory
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
		img1 = img1.getSubimage(0, img1.getHeight()-height_to_cut, img1.getWidth(), height_to_cut);
		
		//////////////////////////////////////////
				//from left
		
		for(int x = 0; x < columns ; x++)
		{	
	        for(int y = 0; y < rows; y++)  
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
			width_after_cut++; // one row is all white
		else
			break;
			
		}
		img1 = img1.getSubimage(0, 0, width_after_cut, img1.getHeight());
		
		/////////////////////////////////
		 			// from right
		for(int x = img1.getWidth(); x>0 ; x--)
		{	
	        for(int y = 0; y < rows; y++)  
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
			width_after_cut++; // one row is all white
		else
			break;
			
		}
		img1 = img1.getSubimage(img1.getWidth()-width_after_cut, 0, width_after_cut, img1.getHeight());
		
	return img1;
		
	}
	
	 private static List<BufferedImage> saveImagesToList()
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
		 for(BufferedImage tmp : all_images)
		 {
			BufferedImage result = cutFrame(tmp); //????????????????????????????????
				
			File f;
			  f = new File( "image.png" );  
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
		 }

		 
		 return all_images_after;
	 }
	 
	 static List<BufferedImage> cutOut1()
	 {
		 for(BufferedImage tmp : all_images)
		 {
			BufferedImage result = cutFrame(tmp); //????????????????????????????????
				
			all_images_after_list.add(result);
		 }

		 
		 return all_images_after_list;
	 }
}



