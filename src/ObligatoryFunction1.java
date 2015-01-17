import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;


public class ObligatoryFunction1 {
	static HashMap<JButton, File> all_chosen_images;
	static List <BufferedImage> all_images = new ArrayList<BufferedImage>();
	static List <File> all_images_after = new ArrayList<File>();
	static List <BufferedImage> all_images_after_list=new ArrayList<BufferedImage>();;
	
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
		int width_to_cut = 0;
		int height_to_cut = 0;
		boolean all_row = false;
		
		
		for(int y = 0; y < img1.getHeight(); y++)
		{	
	        for(int x = 0; x < img1.getWidth(); x++)  // od gory
	        {
	        	int argb0 = img1.getRGB(x,y);
	        	//System.out.println(x + "=zmienna x ||" + img1.getHeight() + "=getHeight obrazka. || " + y + " =zmienna y||" + img1.getWidth() + " =getWidth obr");
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
	        			System.out.println("Stop for loop");
	        			break;
	        		}
	        	}
 	
	        }
		if(all_row==true)
			height_to_cut++; // one row is all white
			
		else
		{
			System.out.println("Stop for loop");
			break;
		}
		
		}
		//height_to_cut+=3; // ogolnie to wtf kurwa mac. bez tego niedocina do konca???????????
		System.out.println(height_to_cut + " ile do wyciecia");
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
		 System.out.println(all_chosen_images.size() + " size w bledzie");
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



