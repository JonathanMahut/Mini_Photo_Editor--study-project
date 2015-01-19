import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
 
 
public class RotateFunction{
       
        HashMap<JButton,File> map;
        Image spiral;
        double degrees;
       
        RotateFunction(HashMap<JButton,File> source_of_image,double deg)
        {
                map = AddDirectory.all_chosen;
                degrees = deg;
        }
       
       
         public void main(){
                int counter = 0;
               
                if (spiral == null){
                        for(Map.Entry<JButton, File> entry: map.entrySet())   //////Moving through hashMap
                        {
                    try {
                        spiral = getImage(entry.getValue().getAbsolutePath());
                        counter++;
                        System.out.println("Path of image " + counter + " : " +entry.getValue().getAbsolutePath());  
                        rotateImage(degrees, null);
                        System.out.println("Rotating image "+counter+" by "+degrees+" degrees");
                       BufferedImage tmp = toBufferedImage(spiral);
                       File f;
                       f = new File( "image.png" );  
                       try
                       {  
                          
                        ImageIO.write( tmp, "PNG", f );  
                
                       }  
                       catch ( IOException x )
                       {  
                           // Complain if there was any problem writing  
                           // the output file.  
                           x.printStackTrace();  
                       }        
                        map.put(entry.getKey(), f);
                    }
                    catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }
                   
                        } 
                        PhotoEdit.all_chosen_images.clear();
                        PhotoEdit.all_chosen_images.putAll(map);  //Putting HashMap with rotated images into global HashMap
                }
        }
       
        public Image getImage(String path){
                Image tempImage = null;
                try
                {
                        tempImage = Toolkit.getDefaultToolkit().getImage(path);
                }
                catch (Exception e)
                {
                        System.out.println("An error occured - " + e.getMessage());
                }
                return tempImage;
        }
       
        ////////////////////////////////////////////////////////////////////
       
        ///////////////////IMAGE ROTATION /////////////////////////////////
       
        public void rotateImage(double degrees, ImageObserver o){
                ImageIcon icon = new ImageIcon(this.spiral);
                BufferedImage blankCanvas = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = (Graphics2D)blankCanvas.getGraphics();
                g2.rotate(Math.toRadians(degrees), icon.getIconWidth()/2, icon.getIconHeight()/2);
                g2.drawImage(this.spiral, 0, 0, o);
                this.spiral = blankCanvas;
        }
    ////
        ///////Komentarz
        //Mowa ciala to podstawa
        public static BufferedImage toBufferedImage(Image img)
        {
            if (img instanceof BufferedImage)
            {
                return (BufferedImage) img;
            }

            // Create a buffered image with transparency
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            // Draw the image on to the buffered image
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();

            // Return the buffered image
            return bimage;
        }/////////////////////////////////////////////////////////////
 
}