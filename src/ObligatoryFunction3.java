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
 
 
public class ObligatoryFunction3 {
        static HashMap<JButton,File> all_chosen_images;
        static List <BufferedImage> all_images = new ArrayList <BufferedImage>();
        static int n_stripes;
        static String direction_of_cut;
        static int n_images;
        static int mode = 0; // enlarge or shrink
        static List <BufferedImage[][]> sliced_images;
       
        ObligatoryFunction3(HashMap<JButton,File> source_of_images,int how_many_stripes,String direction_of_cut1,int mode)
        {
        	
                this.all_chosen_images = AddDirectory.all_chosen;
                n_stripes = how_many_stripes;
                this.direction_of_cut = direction_of_cut1;
                n_images = source_of_images.values().size();
                sliced_images = new ArrayList <BufferedImage[][]>();
                all_images = saveImagesToList();
                all_images = makeImagesEqual();
               
                ObligatoryFunction3.main(null);
                
               
        }
       
        private static void sliceImages ()      // this class is making sliced images and save them to list
        {       ///REMEMBER ABOUT RESIZING EACH IMAGE
                int count =1; // to switch images in list
               
               
                for( int i = 0; i <all_images.size(); i++)
                {
               
                       
                                Cutouts x =new Cutouts(direction_of_cut,n_stripes*n_images,all_images.get(i));
                                BufferedImage[][] tmp = x.sliceImage();
                               
                                sliced_images.add(tmp);
                                
                                count++;
                       
 
                }
               
        }
        public static BufferedImage createImageFromSlice()
        {      
                ///////Get Height and Width of the image
                BufferedImage temp = null;
                System.out.println(all_chosen_images);
                Object myKey = all_chosen_images.keySet().toArray()[0];
                File myValue = all_chosen_images.get(myKey);
               
                try
                {
                        temp = ImageIO.read(myValue); //// tutaj blad w trakcie proby uzyskania dostepu do obrazka
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
                	System.out.println(direction_of_cut);
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
               
                BufferedImage returned_image = createImageFromSlice();
                return returned_image;
               
        }
       
       
        public static void main(String[] args)
         {  
                BufferedImage final_img;
                sliceImages();
                final_img=createImageFromSlice();
     
                f = new File( "image.png" );  
        try
        {  
           
         ImageIO.write( final_img, "PNG", f );  
 
        }  
        catch ( IOException x )
        {  
            // Complain if there was any problem writing  
            // the output file.  
            x.printStackTrace();  
        }        
         }
       
        private static BufferedImage resizeImage(BufferedImage originalImage, int type,int destination_Widht,int destination_Height)
          {
                        BufferedImage resizedImage = new BufferedImage(destination_Widht, destination_Height, type);
                        Graphics2D g = resizedImage.createGraphics();
                        g.drawImage(originalImage, 0, 0, destination_Widht, destination_Height, null);
                        g.dispose();
                 
                        return resizedImage;
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
       
        private static List<BufferedImage>makeImagesEqual()
        {
               
                if(mode == 1 )
                {
                        for(int i  =0; i <all_images.size(); i++)
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
                else
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
                return all_images;
               
        }
}