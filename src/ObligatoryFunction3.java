import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;


public class ObligatoryFunction3 {
	static HashMap<JButton,File> all_chosen_images;
	static int n_stripes;
	static String direction_of_cut;
	static int n_images;
	static List<BufferedImage[]> sliced_images;
	
	ObligatoryFunction3(HashMap<JButton,File> source_of_images,int how_many_stripes,String direction_of_cut)
	{
		all_chosen_images = source_of_images;
		n_stripes = how_many_stripes;
		direction_of_cut = direction_of_cut;
		n_images = source_of_images.size();
	}
	
	private static void sliceImages () 	// this class is making sliced images and save them to list
	{	///REMEMBER ABOUT RESIZING EACH IMAGE 
		for( File i : all_chosen_images.values())
		{
			try
			{
				Cutouts x =new Cutouts(direction_of_cut,n_stripes*n_images,ImageIO.read( i ));
				sliced_images.add(x.returnSlicedImage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	private BufferedImage createImageFromSlice()
	{	
		///////Get Height and Width of the image
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(all_chosen_images.get(0));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int width = temp.getWidth();
		int height = temp.getHeight();
		// coordinates from which starts drawing of new stripe 
		int x = 0;  // it's increases for columns 
		int y = 0;  // it's increases for rows
		////create empty image with the resolution and type of the first one from hashmap
		
		BufferedImage  created_image = new BufferedImage(width,height,temp.getType()); /// on this image the stripes will be pasted 
		//Graphics2D g = created_image.createGraphics();
		sliceImages(); // create list of sliced images
		if(direction_of_cut == "V")
		for(int i =0; i<n_stripes * n_images; i++)
		{				
			int counter = 0;
			
			if(counter > sliced_images.size()-1) // if counter will be at the end of the list it will start counting from begin again
				counter = 0;
			
			BufferedImage stripe_of_image = sliced_images.get(counter)[i];
			
			created_image.createGraphics().drawImage(stripe_of_image,x,y,null);
			
			x+=width;
		}
		else /// HORIZONTAL
		{
			for(int i =0; i<n_stripes * n_images; i++)
			{				
				int counter = 0;
				
				if(counter > sliced_images.size()-1) // if counter will be at the end of the list it will start counting from begin again
					counter = 0;
				
				BufferedImage stripe_of_image = sliced_images.get(counter)[i];
				
				created_image.createGraphics().drawImage(stripe_of_image,x,y,null);
				
				y+=height;
			}
		}
		
		return created_image;
		
		
	}
	
	
	
}
