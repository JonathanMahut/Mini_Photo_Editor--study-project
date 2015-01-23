import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
 
 
 
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JButton;
 
 /**
  * The class dividing given images in stripes and then create the new one from the stripes.
  * 
  * @author Adam Malysz
  *
  */
public class ObligatoryFunction3 {
	/**
	 * Images  as files which are values for JButtons. 
	 * 
	 */
        static HashMap<JButton,File> all_chosen_images;
        /**
    	 * The list for selected images from given directories.
    	 */
        static List <BufferedImage> all_images;
        /**
         * The variable storing chosen be a user number of stripes.
         */
        static int n_stripes;
        /**
         * The variable storing chosen be a user direction of cutting the image.
         *  
         *  V - Vertical else Horizontal
         *  
         */
        static String direction_of_cut;
        /**
         * The number of selected images from given directories.
         */
        static int n_images;
         /**
          * The list storing images which are sliced on stripes. 
          */
        static List <BufferedImage[][]> sliced_images;
        /**
    	 * The variable storing the option for situation when images are different sizes.
    	 *  2 - Enlarge smaller one
    	 *  3 - Shrink bigger one
    	 *  4 - cut the bigger to the smaller one. 
    	 */
        static int mode =0;
        /**
         * The final output 
         */
        static BufferedImage final_image;
        /** 
         * constructor
         * @param source_of_images
         * @param how_many_stripes
         * @param direction_of_cut1 
         * @param diff Option for situation when images are of different sizes. 
         */
       
        ObligatoryFunction3(HashMap<JButton,File> source_of_images,int how_many_stripes,String direction_of_cut1,int diff)
        {
        	
                this.all_chosen_images = AddDirectory.all_chosen;
                n_stripes = how_many_stripes;
                this.direction_of_cut = direction_of_cut1;
                n_images = source_of_images.values().size();
                sliced_images = new ArrayList <BufferedImage[][]>();
                all_images = new ArrayList <BufferedImage>();
                all_images.clear();
                
                this.final_image = null;
                this.mode = diff;
    
                ObligatoryFunction3.main(null);
                
               
        }
       /**
        * The method which is slicing the selected images in stripes.
        */
        private static void sliceImages ()     
        {       
                int count =1; // to switch images in list
                all_images = saveImagesToList();
                all_images = makeImagesEqual();
               
                for( int i = 0; i <all_images.size(); i++)
                {
               
                       
                                Cutouts x =new Cutouts(direction_of_cut,n_stripes*n_images,all_images.get(i));
                                BufferedImage[][] tmp = x.sliceImage();
                               
                                sliced_images.add(tmp);
                                
                                count++;
                       
 
                }
               
        }
        /**
         * The method which create final image from all stripes.
         * @return
         */
        public static BufferedImage createImageFromSlice()
        {      
                ///////Get Height and Width of the image
               /* BufferedImage temp = null;
               
                Object myKey = all_chosen_images.keySet().toArray()[0];
                File myValue = all_chosen_images.get(myKey);
               
                try
                {
                        temp = ImageIO.read(myValue); //// tutaj blad w trakcie proby uzyskania dostepu do obrazka
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }*/
                int width = all_images.get(0).getWidth();
                int height = all_images.get(0).getHeight();
               
                // coordinates from which starts drawing of new stripe
                int x = 0;  // it's increases for columns
                int y = 0;  // it's increases for rows
                ////create empty image with the resolution and type of the first one from hashmap
               
                BufferedImage  created_image = new BufferedImage(width,height,all_images.get(0).getType()); /// on this image the stripes will be pasted
                //Graphics2D g = created_image.createGraphics();
                //sliceImages(); // create list of sliced images
                int counter = 0;
               
                if(direction_of_cut == "V")
               
                for(int i =0; i<n_stripes * n_images; i++)
                {                              
                       
                       
                        if(counter >= sliced_images.size()) // if counter will be at the end of the list it will start counting from begin again
                                counter = 0;
                       
                                                //      stripe_of_image[COLUMNS][ROWS]
                       
                        BufferedImage stripe_of_image[][] = sliced_images.get(counter);
                       
                       
                        created_image.createGraphics().drawImage(stripe_of_image[i][0],x,y,null);
                       
 
                        x+=stripe_of_image[i][0].getWidth()+1;
                        counter++;
                }
                else /// HORIZONTAL
                {
                	
                        for(int i =0; i<n_stripes * n_images; i++)
                        {                              
                                                                //rows = n_stripes
                                        //                              stripe_of_image[COLUMNS][ROWS]
                                if(counter >= sliced_images.size()) // if counter will be at the end of the list it will start counting from begin again
                                        counter = 0;
                               
                                BufferedImage stripe_of_image[][] = sliced_images.get(counter);
                               
                                created_image.createGraphics().drawImage(stripe_of_image[0][i],x,y,null);
                               
                               
                               
                                y+=stripe_of_image[0][i].getHeight()+1;
                                counter++;
                        }
                        
                }
               
                
                
              
                final_image = created_image;
                return created_image;
               
               
        }
       
        public static File getF() {
                return f;
        }
 
        public static void setF(File f) {
                ObligatoryFunction3.f = f;
        }
        private static File f;
       
        public static BufferedImage returnImage ()
        {		
        		
        	
                return final_image;
               
        }
       
       
        public static void main(String[] args)
        
         {  
        	
                
               
                sliceImages();
                final_image=createImageFromSlice();
     
                f = new File( "image.png" );  
        try
        {  
           
         ImageIO.write( final_image, "PNG", f );  
 
        }  
        catch ( IOException x )
        {  
            // Complain if there was any problem writing  
            // the output file.  
            x.printStackTrace();  
        }        
         }
       /**
        * The method which is changing a resolution of the given image.
        * @param originalImage
        * @param type
        * @param destination_Widht
        * @param destination_Height
        * @return
        */
        private static BufferedImage resizeImage(BufferedImage originalImage, int type,int destination_Widht,int destination_Height)
          {
                        BufferedImage resizedImage = new BufferedImage(destination_Widht, destination_Height, type);
                        Graphics2D g = resizedImage.createGraphics();
                        g.drawImage(originalImage, 0, 0, destination_Widht, destination_Height, null);
                        g.dispose();
                 
                        return resizedImage;
          }
        /**
         * The method which is transferring selected images from the hashmap into the list. 
         * @return
         */
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
       /**
        * The method which is making all selected images of the same resolution. 
        * @return
        */
        private static List<BufferedImage>makeImagesEqual()
        {
        		
               System.out.println(mode + " MODEMODE");
                if(mode == 1 ) // enlarge
                {
                        for(int i  =0; i <all_images.size()-1; i++)
                        {
                               
                                BufferedImage first = all_images.get(i);
                                BufferedImage second = all_images.get(i + 1);
                               
                                if(first.getType() != second.getType())
                                {
                                        second=resizeImage(second,first.getType(),second.getWidth(),second.getHeight());
                                }
                                if(first.getHeight() != second.getHeight() ||first.getWidth() != second.getWidth())
                                {
                                        if(first.getHeight()  > second.getHeight())    
                                                second=resizeImage(second,first.getType(),second.getWidth(),first.getHeight());
                                        if(first.getHeight() < second.getHeight())    
                                                first=resizeImage(first,first.getType(),first.getWidth(),second.getHeight());
                                        if(first.getWidth() > second.getWidth())
                                                second=resizeImage(second, first.getType(), first.getWidth(),second.getHeight());
                                        if(first.getWidth() < second.getWidth())
                                                first=resizeImage(first, first.getType(), second.getWidth(),first.getHeight());
                                }
                                all_images.set(i,first);
                                all_images.set(i+1,second);
   
                        }

                }
                else if (mode == 2) // shrink
                {
                        for(int i  =0; i <all_images.size()-1; i++)
                        {
                               
                                BufferedImage first = all_images.get(i);
                                BufferedImage second = all_images.get(i + 1);
                               
                                if(first.getType() != second.getType())
                                {
                                        second=resizeImage(second,first.getType(),second.getWidth(),second.getHeight());
                                }
                             
                                if(first.getHeight() != second.getHeight() ||first.getWidth() != second.getWidth())
                                        if(first.getHeight()  > second.getHeight())    
                                                first=resizeImage(first,first.getType(),first.getWidth(),second.getHeight());
                                        if(first.getHeight() < second.getHeight())    
                                                second=resizeImage(second,first.getType(),second.getWidth(),first.getHeight());
                                        if(first.getWidth() > second.getWidth())
                                                first=resizeImage(first, first.getType(), second.getWidth(),first.getHeight());
                                        if(first.getWidth() < second.getWidth())
                                                second=resizeImage(second, first.getType(), first.getWidth(),second.getHeight());
                            all_images.set(i,first);
                            all_images.set(i+1,second);
                        }
                }
                else if (mode == 3) // cut to smaller
                {
                        for(int i  =0; i <all_images.size()-1; i++)
                        {
                              
                                BufferedImage first = all_images.get(i);
                                BufferedImage second = all_images.get(i + 1);
                               
                                if(first.getType() != second.getType())
                                {
                                        second=resizeImage(second,first.getType(),second.getWidth(),second.getHeight());
                                }
                             
                                if(first.getHeight() != second.getHeight() ||first.getWidth() != second.getWidth())
                				{

                					if(first.getHeight() != second.getHeight() ||first.getWidth() != second.getWidth())
                					{
                						if(first.getHeight() * first.getWidth() > second.getHeight() * second.getWidth() )
                							first=first.getSubimage((first.getWidth()/2)-(second.getWidth()/2), (first.getHeight()/2)-(second.getHeight()/2), second.getWidth(), second.getHeight());
                						else
                							second=second.getSubimage((second.getWidth()/2)-(first.getWidth()/2), (second.getHeight()/2)-(first.getHeight()/2), first.getWidth(), first.getHeight());
                					}
                					
                				}
                            all_images.set(i,first);
                            all_images.set(i+1,second);
                        }
                }
                return all_images;
               
        }
}