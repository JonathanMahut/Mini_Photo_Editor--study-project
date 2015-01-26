import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;

import java.awt.Choice;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import net.sf.image4j.util.ConvertUtil;

public class SaveImage extends JFrame {
	 JFileChooser c = PhotoEdit.save;
	 /**
	  * field which store name of file
	  */
  private JTextField filename = new JTextField(), dir = new JTextField();
  /**
   * final image (merged) which can be saved or discard 
   */
  private static BufferedImage image_to_save;
  /**
   *  button responsible for saving image on which a user were working
   */
  private JButton save = new JButton("Save");
  /**
   * 
   * main panel for program
   */
  private final JPanel panel = new JPanel();
  /**
   * format in which user want's to save the image
   */
  private static String chosen_type = "png";
  /**
   * depth for format of an image
   */
  private  int chosen_depth = 0;
  /**
   * 
   * variable responsible for  behaviour of program when the image is saving
   * if which_mode == 1 then after save the program will quit 
   * else 
   * will be not closed
   */
  private int which_mode;  
/**
 * class responsible for saving the image on which usere were working
 * @param merged_image  the image which will be optionally saved
 * @param mode to recognize  if it is necessary to close the program after execution of SaveImage 
 * 
 */
  SaveImage(BufferedImage merged_image,int mode) {
	  image_to_save=merged_image;
	 which_mode = mode;
	/**
	 * panels on which there are save options    
	 */
    JPanel p = new JPanel();
    JPanel Option_1=new JPanel();
    JPanel Option_2_a=new JPanel();
    JPanel Option_2=new JPanel();
    Option_2.setLayout(new GridLayout(0, 1));
    
    Option_1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "Option_1 Give name on your own"));
    Option_2_a.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "Option_2   f.ex. image001, image002 ..."));
    
    //Option 1
    
    //Option2 
    Option_2.add(PhotoEdit.name_label);
    Option_2.add(PhotoEdit.name);
    Option_2.add(PhotoEdit.where_label);
    Option_2.add(PhotoEdit.where);
    Option_2.add(PhotoEdit.start_value_label);
    Option_2.add(PhotoEdit.start_value);
    Option_2.add(PhotoEdit.step_label);
    Option_2.add(PhotoEdit.step);
    Option_2.add(PhotoEdit.digits_label);
    Option_2.add(PhotoEdit.digits);
    p.add(Option_2_a);
    JPanel save_button = new JPanel();
    save_button.setLayout(new BoxLayout(save_button,BoxLayout.Y_AXIS));
    Option_2_a.add(Option_2);
    Option_2_a.add(save_button);
    p.add(Option_2_a);
    
    save_button.add(PhotoEdit.Save1_save);
    
    save_button.add(PhotoEdit.Option_1_set);
    save_button.add(PhotoEdit.Nazwa);
    
    PhotoEdit.Option_1_set.addActionListener(new ActionListener()
    {

		public void actionPerformed(ActionEvent e) {
			String str = PhotoEdit.name.getText();
			char[] letters = str.toCharArray();
			int  wheree=Integer.parseInt(PhotoEdit.where.getText());
			PhotoEdit.step_value=Integer.parseInt(PhotoEdit.step.getText());
			int digitss=Integer.parseInt(PhotoEdit.digits.getText());
			int start = Integer.parseInt(PhotoEdit.start_value.getText());
			
			String start_char = PhotoEdit.start_value.getText();
			char [] start_value_char=start_char.toCharArray();
			
			if(letters.length<0)
			{Object[] options = {"OK"};
            int n = JOptionPane.showOptionDialog(PhotoEdit.Nazwa,
                    "Please pick a name.","Name exception",
                       JOptionPane.PLAIN_MESSAGE,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       options,
                       options[0]);}
			else if(letters.length<wheree)
			{Object[] options = {"OK"};
            int n = JOptionPane.showOptionDialog(PhotoEdit.Nazwa,
                    "Appearance of the counter cannot be included in the name. Pick smaller one.In this case maximum is"+letters.length,"Appearance of the counter exception",
                       JOptionPane.PLAIN_MESSAGE,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       options,
                       options[0]);
            ((JTextField)PhotoEdit.name).setEditable(false);} //error} //error out of boundary
			else if(start_value_char.length>digitss)
			{Object[] options = {"OK"};
            int n = JOptionPane.showOptionDialog(PhotoEdit.Nazwa,
                    "Start value extends the digit limit.","Start exception.",
                       JOptionPane.PLAIN_MESSAGE,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       options,
                       options[0]);
            ((JTextField)PhotoEdit.where).setEditable(false);} //error
			else if(wheree<0||start<0||PhotoEdit.step_value<0||digitss<0)
			{Object[] options = {"OK"};
            int n = JOptionPane.showOptionDialog(PhotoEdit.Nazwa,
                    "Appearance of a counter, start value, step and digits must be positive values","Negative values exception",
                       JOptionPane.PLAIN_MESSAGE,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       options,
                       options[0]);
            ((JTextField)PhotoEdit.start_value).setEditable(false);} // must be >0
			else
			{
		    for (int j=0;j<digitss-start_value_char.length;j++)
		    {PhotoEdit.digits_vector.addElement(0);}
		    for (int i=0;i<start_value_char.length;i++)
		    {
		    int x = Character.getNumericValue(start_value_char[i]);
		    PhotoEdit.digits_vector.addElement(x);
		    }
		   
			for (int i=0;i<wheree;i++)
			{PhotoEdit.nazwaa=PhotoEdit.nazwaa+(letters[i]);
			PhotoEdit.first_part=PhotoEdit.first_part+(letters[i]);}
			for (int j=0;j<digitss;j++)
		    {PhotoEdit.nazwaa=PhotoEdit.nazwaa+(PhotoEdit.digits_vector.get(j));}
			
			for (int i=wheree;i<letters.length;i++)
			{PhotoEdit.nazwaa=PhotoEdit.nazwaa+(letters[i]);
			PhotoEdit.second_part=PhotoEdit.second_part+(letters[i]);}
			//start=step_value;
			//step_value =step_value + Integer.parseInt(step.getText());
			PhotoEdit.Nazwa.setText(PhotoEdit.nazwaa);
			PhotoEdit.Option_1_set.setEnabled(false);
			PhotoEdit.Save1_save.setEnabled(true);
			PhotoEdit.licznik_save=start;
			((JTextField)PhotoEdit.name).setEditable(false);
			((JTextField)PhotoEdit.start_value).setEditable(false);
			((JTextField)PhotoEdit.step).setEditable(false);
			((JTextField)PhotoEdit.digits).setEditable(false);
			((JTextField)PhotoEdit.where).setEditable(false);
			}
		}
    });
    

    //Option_1.setPreferredSize(new Dimension(Option_2_a.getWidth(),Option_2_a.getHeight()));
    Option_1.add(save);
    p.add(Option_1);
    Container cp = getContentPane();
    cp.add(p, BorderLayout.SOUTH);
    dir.setEditable(false);
    filename.setEditable(false);
    p = new JPanel();
    p.setLayout(new GridLayout(2, 1));
    p.add(filename);
    p.add(dir);
    p.setBounds(200, 200, 900, 500);
    cp.add(p, BorderLayout.NORTH);
    
    getContentPane().add(panel, BorderLayout.CENTER);
    panel.setLayout(null);
    
    JLabel lblTypeOfImage = new JLabel("Type of image");
    lblTypeOfImage.setBounds(97, 11, 112, 50);
    panel.add(lblTypeOfImage);
    JComboBox<String> comboBox =new JComboBox<String>();
    /**
     * format in which user can save the image
     */
    panel.add(comboBox);
    comboBox.setBounds(97, 58, 100, 20);
    comboBox.addItem("png");
    comboBox.addItem("jpeg");
    comboBox.addItem("jpg");
    comboBox.addItem("bmp");
    comboBox.addItem("tiff");
    
    /**
     * depth in which user can save the image
     */
    JComboBox<String> comboBox2 = new JComboBox<String>();
    comboBox2.setBounds(207, 58, 112, 20);
    comboBox2.addItem("1");
    comboBox2.addItem("8");
    comboBox2.addItem("16");
    comboBox2.addItem("24");
    comboBox2.addItem("32");
    
    /**
     * saving option which chose user (which depth)
     */
    comboBox2.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		chosen_depth= Integer.parseInt((String) comboBox2.getSelectedItem());
    		
    	}
    });
    panel.add(comboBox2);
    
    JLabel lblDepth = new JLabel("Depth");
    lblDepth.setBounds(242, 29, 46, 14);
    panel.add(lblDepth);
    comboBox.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		chosen_type =(String) comboBox.getSelectedItem();
    	}
    });
    // Save z default name
    /**
     * Name for the image which will be saved according to instructions for option 1 
     */
    PhotoEdit.Save1_save.addActionListener(new ActionListener()
    {

		public void actionPerformed(ActionEvent e) {
			
	
			//licznik=licznik+step_value;
			 disableTextField(c.getComponents());
			
			if(PhotoEdit.licznik_save>=((int)Math.pow(10, PhotoEdit.digits_vector.size())))
			{{Object[] options = {"OK"};
            int n = JOptionPane.showOptionDialog(PhotoEdit.Nazwa,
                    "You can't save image with this name","Counter exception",
                       JOptionPane.PLAIN_MESSAGE,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       options,
                       options[0]);}
			PhotoEdit.Option_1_set.setEnabled(true);
			PhotoEdit.Nazwa.setText("");
			PhotoEdit.nazwaa="";	
			PhotoEdit.first_part="";
			PhotoEdit.second_part="";
			PhotoEdit.digits_vector.clear();
			PhotoEdit.digitss="";
			PhotoEdit.licznik_save=0;
			PhotoEdit.Save1_save.setEnabled(false);
			}
			else
			{
				

			char[] licznikk = Integer.toString(PhotoEdit.licznik_save).toCharArray();
			for (int i=0;i<licznikk.length;i++)
			{
				PhotoEdit.digits_vector.remove(PhotoEdit.digits_vector.size()-1);
			}
			
		    for (int j=0;j<licznikk.length;j++)
		    {	
		    	int x = Character.getNumericValue(licznikk[j]);
		    	PhotoEdit.digits_vector.addElement(x);
		    }
		    
		    for(int i=0;i<PhotoEdit.digits_vector.size();i++)
			{PhotoEdit.digitss=PhotoEdit.digitss+PhotoEdit.digits_vector.elementAt(i);}
		    
			System.out.println(PhotoEdit.first_part+PhotoEdit.digitss+PhotoEdit.second_part);
			
			
			//Basic save
			 disableTextField(c.getComponents());
   	      // Demonstrate "Save" dialog:
   	      int rVal = c.showSaveDialog(SaveImage.this);
   	      if (rVal == JFileChooser.APPROVE_OPTION) {
   	        filename.setText(PhotoEdit.first_part+PhotoEdit.digitss+PhotoEdit.second_part);
   	        dir.setText(c.getCurrentDirectory().toString());
   	      
   	     int pixels[][] = convertTo2DWithoutUsingGetRGB(image_to_save);
   	  if(chosen_depth == 1){//1b 
      	BufferedImage image1 = new BufferedImage(image_to_save.getWidth(), image_to_save.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
      	for (int y = 0; y < image_to_save.getHeight(); y++) {
      		for (int x = 0; x < image_to_save.getWidth(); x++) {
      			image1.setRGB(x, y, new Color(pixels[x][y], pixels[x][y], pixels[x][y]).getRGB());
      		}
      	}
      
      	image_to_save = image1;

      }
      else if(chosen_depth == 8){ //8 
      	BufferedImage image8 = new BufferedImage(image_to_save.getWidth(), image_to_save.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
      	for (int y = 0; y < image_to_save.getHeight(); y++) {
      		for (int x = 0; x < image_to_save.getWidth(); x++) {
      			image8.setRGB(x, y, new Color(pixels[x][y], pixels[x][y], pixels[x][y]).getRGB());
      		}
      	}
      	image_to_save = image8;

      }
      else if(chosen_depth == 16){ //16
      	BufferedImage image16 = new BufferedImage(image_to_save.getWidth(), image_to_save.getHeight(), BufferedImage.TYPE_USHORT_565_RGB);
      	for (int y = 0; y < image_to_save.getHeight(); y++) {
      		for (int x = 0; x < image_to_save.getWidth(); x++) {
      			image16.setRGB(x, y, new Color(pixels[x][y], pixels[x][y], pixels[x][y]).getRGB());
      		}
      	}
      	
      	image_to_save = image16;

      }
      else if(chosen_depth == 24){//24 
      	BufferedImage image24 = new BufferedImage(image_to_save.getWidth(), image_to_save.getHeight(), BufferedImage.TYPE_INT_RGB);
      	for (int y = 0; y < image_to_save.getHeight(); y++) {
      		for (int x = 0; x < image_to_save.getWidth(); x++) {
      			image24.setRGB(x, y, new Color(pixels[x][y], pixels[x][y], pixels[x][y]).getRGB());
      		}
      	}
      	image_to_save = image24;
      }
      else if(chosen_depth == 32){ //32 
      	BufferedImage image32 = new BufferedImage(image_to_save.getWidth(), image_to_save.getHeight(), BufferedImage.TYPE_INT_ARGB);
      	for (int y = 0; y < image_to_save.getHeight(); y++) {
      		for (int x = 0; x < image_to_save.getWidth(); x++) {
      			image32.setRGB(x, y, new Color(pixels[x][y], pixels[x][y], pixels[x][y]).getRGB());
      		}
      	}
      	image_to_save = image32;

      }
      else{

      	BufferedImage image5 = new BufferedImage(image_to_save.getWidth(), image_to_save.getHeight(), BufferedImage.TYPE_INT_RGB);
      	for (int y = 0; y < image_to_save.getHeight(); y++) {
      		for (int x = 0; x < image_to_save.getWidth(); x++) {
      			image5.setRGB(x, y, new Color(pixels[x][y], pixels[x][y], pixels[x][y]).getRGB());
      		}
      	}
      	image_to_save = image5;

      }
   	        
   	    	  if(chosen_type  == "jpeg")
   	          {
   	  		  	

   	          	  try 
   	                {  
   	          		  
   	          		  	ImageIO.write(image_to_save, "JPEG", new File(c.getCurrentDirectory().toString()+ '\\' +(PhotoEdit.first_part+PhotoEdit.digitss+PhotoEdit.second_part)+".jpeg"));  
   	          		  	
   	          		  	if(which_mode == 1)
   	          		  	{
   	          		  		System.exit(0);
   	          		  	}
   	                }  
   	                catch ( IOException x ) {  
   	                    // Complain if there was any problem writing   
   	                    // the output file.  
   	                    x.printStackTrace();  
   	                }         
   	          }
   	    	  else if(chosen_type == "jpg")
   	    	  {

   	    		  try 
   	              {  
   	    			    ImageIO.write(image_to_save, "JPG", new File(c.getCurrentDirectory().toString()+ '\\' +(PhotoEdit.first_part+PhotoEdit.digitss+PhotoEdit.second_part)+".jpg"));  
   	    			    
   	    			    if(which_mode == 1)
   	          		  	{
   	          		  		System.exit(0);
   	          		  	}

   	                  
   	              }  
   	              catch ( IOException x ) {  
   	                  // Complain if there was any problem writing   
   	                  // the output file.  
   	                  x.printStackTrace();  
   	              } 
   	    	  }
   	    	  else if(chosen_type == "png")
   	    	  {
   	    		  
   	    	        try 			
   	    	        {  
   	    	            ImageIO.write(image_to_save, "PNG", new File(c.getCurrentDirectory().toString()+ '\\' +(PhotoEdit.first_part+PhotoEdit.digitss+PhotoEdit.second_part)+".png"));  
   	    	            
   	    	            if(which_mode == 1)
   	          		  	{
   	          		  		System.exit(0);
   	          		  	}
   	    	        }  
   	    	        catch ( IOException x ) {  
   	    	            // Complain if there was any problem writing   
   	    	            // the output file.  
   	    	            x.printStackTrace();  
   	    	        }         
   	    	  }
   	    	  

   	    	  }
   	    	  else if (chosen_type == "tiff")
   	    	  {
   	    		  try 			
 	    	        {  
 	    	            ImageIO.write(image_to_save, "TIFF", new File(c.getCurrentDirectory().toString()+ '\\' +(PhotoEdit.first_part+PhotoEdit.digitss+PhotoEdit.second_part)+".tiff"));  
 	    	          if(which_mode == 1)
	          		  	{
	          		  		System.exit(0);
	          		  	}
 	    	        }  
 	    	        catch ( IOException x ) {  
 	    	            // Complain if there was any problem writing   
 	    	            // the output file.  
 	    	            x.printStackTrace();  
 	    	        }         
   	    	  }
   	    	 else if (chosen_type == "bmp")
  	    	  {
  	    		  try 			
	    	        {  
	    	            ImageIO.write(image_to_save, "BMP", new File(c.getCurrentDirectory().toString()+ '\\' +(PhotoEdit.first_part+PhotoEdit.digitss+PhotoEdit.second_part)+".bmp"));  
	    	          if(which_mode == 1)
	          		  	{
	          		  		System.exit(0);
	          		  	}
	    	        }  
	    	        catch ( IOException x ) {  
	    	            // Complain if there was any problem writing   
	    	            // the output file.  
	    	            x.printStackTrace();  
	    	        }         
  	    	  }
   	      
   	        
   	      if (rVal == JFileChooser.CANCEL_OPTION) {
   	        filename.setText("You pressed cancel");
   	     if(PhotoEdit.step_value<PhotoEdit.licznik_save)
   	     PhotoEdit.licznik_save=PhotoEdit.licznik_save-PhotoEdit.step_value;
   	        dir.setText("");
   	      }
   	     if(rVal == JFileChooser.CANCEL_OPTION)
   	     { PhotoEdit.licznik_save=PhotoEdit.licznik_save-PhotoEdit.step_value;}
   	      
			//end basic save
   	   PhotoEdit.licznik_save=PhotoEdit.licznik_save+PhotoEdit.step_value;
   	PhotoEdit.digitss="";
			}
		}
		
    
    });
    
    
    //Magda
    
    
    
    
    /**
     * Name of the image which will be saved according to option 2 
     */
    save.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		  JFileChooser c = new JFileChooser();
    	      // Demonstrate "Save" dialog:
    	      int rVal = c.showSaveDialog(SaveImage.this);
    	      if (rVal == JFileChooser.APPROVE_OPTION) {
    	        filename.setText(c.getSelectedFile().getName());
    	        dir.setText(c.getCurrentDirectory().toString());
    	        
    	        
    	        int pixels[][] = convertTo2DWithoutUsingGetRGB(image_to_save);
    	   	     
    	   	     	
    	        if(chosen_depth == 1){//1b 
    	        	BufferedImage image1 = new BufferedImage(image_to_save.getWidth(), image_to_save.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
    	        	for (int y = 0; y < image_to_save.getHeight(); y++) {
    	        		for (int x = 0; x < image_to_save.getWidth(); x++) {
    	        			image1.setRGB(x, y, new Color(pixels[x][y], pixels[x][y], pixels[x][y]).getRGB());
    	        		}
    	        	}
    	        	
    	        	image_to_save = image1;

    	        }
    	        else if(chosen_depth == 8){ //8 
    	        	BufferedImage image8 = new BufferedImage(image_to_save.getWidth(), image_to_save.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
    	        	for (int y = 0; y < image_to_save.getHeight(); y++) {
    	        		for (int x = 0; x < image_to_save.getWidth(); x++) {
    	        			image8.setRGB(x, y, new Color(pixels[x][y], pixels[x][y], pixels[x][y]).getRGB());
    	        		}
    	        	}
    	        	image_to_save = image8;

    	        }
    	        else if(chosen_depth == 16){ //16
    	        	BufferedImage image16 = new BufferedImage(image_to_save.getWidth(), image_to_save.getHeight(), BufferedImage.TYPE_USHORT_565_RGB);
    	        	for (int y = 0; y < image_to_save.getHeight(); y++) {
    	        		for (int x = 0; x < image_to_save.getWidth(); x++) {
    	        			image16.setRGB(x, y, new Color(pixels[x][y], pixels[x][y], pixels[x][y]).getRGB());
    	        		}
    	        	}
    	        	
    	        	image_to_save = image16;

    	        }
    	        else if(chosen_depth == 24){//24 
    	        	BufferedImage image24 = new BufferedImage(image_to_save.getWidth(), image_to_save.getHeight(), BufferedImage.TYPE_INT_RGB);
    	        	for (int y = 0; y < image_to_save.getHeight(); y++) {
    	        		for (int x = 0; x < image_to_save.getWidth(); x++) {
    	        			image24.setRGB(x, y, new Color(pixels[x][y], pixels[x][y], pixels[x][y]).getRGB());
    	        		}
    	        	}
    	        	image_to_save = image24;
    	        }
    	        else if(chosen_depth == 32){ //32 
    	        	BufferedImage image32 = new BufferedImage(image_to_save.getWidth(), image_to_save.getHeight(), BufferedImage.TYPE_INT_ARGB);
    	        	for (int y = 0; y < image_to_save.getHeight(); y++) {
    	        		for (int x = 0; x < image_to_save.getWidth(); x++) {
    	        			image32.setRGB(x, y, new Color(pixels[x][y], pixels[x][y], pixels[x][y]).getRGB());
    	        		}
    	        	}
    	        	image_to_save = image32;

    	        }
    	        else{

    	        	BufferedImage image5 = new BufferedImage(image_to_save.getWidth(), image_to_save.getHeight(), BufferedImage.TYPE_INT_RGB);
    	        	for (int y = 0; y < image_to_save.getHeight(); y++) {
    	        		for (int x = 0; x < image_to_save.getWidth(); x++) {
    	        			image5.setRGB(x, y, new Color(pixels[x][y], pixels[x][y], pixels[x][y]).getRGB());
    	        		}
    	        	}
    	        	image_to_save = image5;

    	        }
    	    	  if(chosen_type  == "jpeg")
    	          {
    	  		  	

    	          	  try 
    	                {  
    	          		  	ImageIO.write(image_to_save, "JPEG", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".jpeg"));  
    	          		  	
    	          		  	if(which_mode == 1)
    	          		  	{
    	          		  		System.exit(0);
    	          		  	}
    	                }  
    	                catch ( IOException x ) {  
    	                    // Complain if there was any problem writing   
    	                    // the output file.  
    	                    x.printStackTrace();  
    	                }         
    	          }
    	    	  else if(chosen_type == "jpg")
    	    	  {

    	    		  try 
    	              {  
    	    			    ImageIO.write(image_to_save, "JPG", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".jpg"));  
    	    			    
    	    			    if(which_mode == 1)
    	          		  	{
    	          		  		System.exit(0);
    	          		  	}

    	                  
    	              }  
    	              catch ( IOException x ) {  
    	                  // Complain if there was any problem writing   
    	                  // the output file.  
    	                  x.printStackTrace();  
    	              } 
    	    	  }
    	    	  else if(chosen_type == "png")
    	    	  {
    	    		  
    	    	        try 			
    	    	        {  
    	    	            ImageIO.write(image_to_save, "PNG", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".png"));  
    	    	            
    	    	            if(which_mode == 1)
    	          		  	{
    	          		  		System.exit(0);
    	          		  	}
    	    	        }  
    	    	        catch ( IOException x ) {  
    	    	            // Complain if there was any problem writing   
    	    	            // the output file.  
    	    	            x.printStackTrace();  
    	    	        }         
    	    	  }
    	    	  
    	    	  else if (chosen_type == "tiff")
    	    	  {
    	    		  try 			
  	    	        {  
    	    			 
  	    	            ImageIO.write(image_to_save, "TIF", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".tiff"));  
  	    	          if(which_mode == 1)
	          		  	{
	          		  		System.exit(0);
	          		  	}
  	    	        }  
  	    	        catch ( IOException x ) {  
  	    	            // Complain if there was any problem writing   
  	    	            // the output file.  
  	    	            x.printStackTrace();  
  	    	        }         
    	    	  }
    	    	  else if (chosen_type == "bmp")
    	    	  {
    	    		  try 			
  	    	        {  
  	    	            ImageIO.write(image_to_save, "BMP", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".bmp"));  
  	    	          if(which_mode == 1)
	          		  	{
	          		  		System.exit(0);
	          		  	}
  	    	        }  
  	    	        catch ( IOException x ) {  
  	    	            // Complain if there was any problem writing   
  	    	            // the output file.  
  	    	            x.printStackTrace();  
  	    	        }         
    	    	  }
    	        
    	        
    	      }
    	      if (rVal == JFileChooser.CANCEL_OPTION) {
    	        filename.setText("You pressed cancel");
    	        dir.setText("");
    	      }
    	}
    });
 
  }
  
  public void disableTextField(Component[] comp)
  {
    for(int x = 0; x < comp.length; x++)
    {
      if(comp[x] instanceof JPanel) disableTextField(((JPanel)comp[x]).getComponents());
      else if(comp[x] instanceof JTextField)
      {
    	((JTextField)comp[x]).setText(PhotoEdit.first_part+PhotoEdit.digitss+PhotoEdit.second_part);
        ((JTextField)comp[x]).setEditable(false);
        return;
      }
    }
  }

  public static void main(BufferedImage image_to_save1,int mode) {
    run(new SaveImage(image_to_save1,mode), 600, 500);
  }

  public static void run(JFrame frame, int width, int height) {
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(width, height);
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    frame.setAlwaysOnTop(true);
    frame.setResizable(false);
  }
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}


private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

	 int width = image.getWidth();
     int height = image.getHeight();
     int[][] result = new int[width][height];

     for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
        	Color color = new Color (image.getRGB(col, row));
           result[col][row] = color.getBlue();
        }
     }

     return result;
 }

}