import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
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
	static int licznik;
	String digitss="";
    Vector<Integer> digits_vector=new Vector<Integer>();
	static String nazwaa="";
	static String first_part="";
	static String second_part="";
	static int step_value;
  private JTextField filename = new JTextField(), dir = new JTextField();
  private static BufferedImage image_to_save;
  private JButton save = new JButton("Save");
  private final JPanel panel = new JPanel();
  private static String chosen_type = "png";
  private  int chosen_depth = 0;
  private int which_mode;  /// this variable is to inform from which place the class was started. If just before exiting the program it will be equal 1 else 0 .
  																									//It'll informal outer class that after execution of this class the program
  																									//must be closed.

  SaveImage(BufferedImage merged_image,int mode) {
	  image_to_save=merged_image;
	 which_mode = mode;
	    
    JPanel p = new JPanel();
    JPanel Option_1=new JPanel();
    JPanel Option_2_a=new JPanel();
    JPanel Option_2=new JPanel();
    Option_2.setLayout(new GridLayout(0, 1));
    
    Option_1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "Option_1"));
    Option_2_a.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "Option_2   f.ex. image001, image002 ..."));
    
    //Option 1
    
    //Option2 
    NumberFormat numberFormat = NumberFormat.getInstance();
    JTextField name = new JTextField();
    JTextField start_value = new JTextField();
    JTextField step = new JTextField();
    JTextField digits = new JTextField();
    JLabel name_label=new JLabel("Name");
    JFormattedTextField where = new JFormattedTextField(numberFormat);
    JLabel where_label=new JLabel("Appearance of counter");
    JLabel start_value_label=new JLabel("Start value");
    JLabel step_label=new JLabel("Step");
    JLabel digits_label=new JLabel("Digits");
    Option_2.add(name_label);
    Option_2.add(name);
    Option_2.add(where_label);
    Option_2.add(where);
    Option_2.add(start_value_label);
    Option_2.add(start_value);
    Option_2.add(step_label);
    Option_2.add(step);
    Option_2.add(digits_label);
    Option_2.add(digits);
    p.add(Option_2_a);
    JPanel save_button = new JPanel();
    save_button.setLayout(new BoxLayout(save_button,BoxLayout.Y_AXIS));
    Option_2_a.add(Option_2);
    Option_2_a.add(save_button);
    p.add(Option_2_a);
    
    JButton Save1 = new JButton("Save Check");
    save_button.add(Save1);
    
    JLabel Nazwa  = new JLabel();
    JButton Option_1_set = new JButton("Set");
    save_button.add(Option_1_set);
    save_button.add(Nazwa);
    
    Option_1_set.addActionListener(new ActionListener()
    {

		public void actionPerformed(ActionEvent e) {

			step_value=Integer.parseInt(step.getText());
			int  wheree=Integer.parseInt(where.getText());
			
			int start = Integer.parseInt(start_value.getText());
			
		    int digitss=Integer.parseInt(digits.getText());
		    for (int j=0;j<digitss-1;j++)
		    {digits_vector.addElement(0);}
		    digits_vector.addElement(start);
		    
		    String str = name.getText();
			char[] letters = str.toCharArray();
			for (int i=0;i<wheree;i++)
			{nazwaa=nazwaa+(letters[i]);
			first_part=first_part+(letters[i]);}
			for (int j=0;j<digitss;j++)
		    {nazwaa=nazwaa+(digits_vector.get(j));}
			
			for (int i=wheree;i<letters.length;i++)
			{nazwaa=nazwaa+(letters[i]);
			second_part=second_part+(letters[i]);}
			//start=step_value;
			//step_value =step_value + Integer.parseInt(step.getText());
			Nazwa.setText(nazwaa);
			Option_1_set.setEnabled(false);
			Save1.setEnabled(true);
			licznik=start;
			((JTextField)name).setEditable(false);
			((JTextField)start_value).setEditable(false);
			((JTextField)step).setEditable(false);
			((JTextField)digits).setEditable(false);
			((JTextField)where).setEditable(false);
		}
    });
    
    Save1.addActionListener(new ActionListener()
    {

		public void actionPerformed(ActionEvent e) {
			
			
			//licznik=licznik+step_value;
			 disableTextField(c.getComponents());
			
			if(licznik>=((int)Math.pow(10, digits_vector.size())))
			{{Object[] options = {"OK"};
            int n = JOptionPane.showOptionDialog(Nazwa,
                    "You can't save image with this name","Counter exception",
                       JOptionPane.PLAIN_MESSAGE,
                       JOptionPane.QUESTION_MESSAGE,
                       null,
                       options,
                       options[0]);}
			Option_1_set.setEnabled(true);
			Nazwa.setText("");
			nazwaa="";	
			first_part="";
			second_part="";
			digits_vector.clear();
			digitss="";
			licznik=0;
			Save1.setEnabled(false);
			}
			else
			{
				

			char[] licznikk = Integer.toString(licznik).toCharArray();
			for (int i=0;i<licznikk.length;i++)
			{
				digits_vector.remove(digits_vector.size()-1);
			}
			
		    for (int j=0;j<licznikk.length;j++)
		    {	
		    	int x = Character.getNumericValue(licznikk[j]);
		    	digits_vector.addElement(x);
		    }
		    
		    for(int i=0;i<digits_vector.size();i++)
			{digitss=digitss+digits_vector.elementAt(i);}
		    
			System.out.println(first_part+digitss+second_part);
			
			
			//Basic save
			 disableTextField(c.getComponents());
   	      // Demonstrate "Save" dialog:
   	      int rVal = c.showSaveDialog(SaveImage.this);
   	      if (rVal == JFileChooser.APPROVE_OPTION) {
   	        filename.setText(first_part+digitss+second_part);
   	        dir.setText(c.getCurrentDirectory().toString());
   	        
   	    	  if(chosen_type  == "jpeg")
   	          {
   	  		  	

   	          	  try 
   	                {  
   	          		  	ImageIO.write(image_to_save, "JPEG", new File(c.getCurrentDirectory().toString()+ '\\' +(first_part+digitss+second_part)+".jpeg"));  
   	          		  	
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
   	    			    ImageIO.write(image_to_save, "JPG", new File(c.getCurrentDirectory().toString()+ '\\' +(first_part+digitss+second_part)+".jpg"));  
   	    			    
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
   	    	            ImageIO.write(image_to_save, "PNG", new File(c.getCurrentDirectory().toString()+ '\\' +(first_part+digitss+second_part)+".png"));  
   	    	            
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
   	    	  else if(chosen_type == "bmp")
   	    	  {	
   	    		  
   	    	     switch(chosen_depth)
   	    	     {	
	    	    	     case 1: 
	    	    	     {
	    	    	    	 try {
	    							ImageIO.write(ConvertUtil.convert1(image_to_save), "BMP", new File(c.getCurrentDirectory().toString()+ '\\' +(first_part+digitss+second_part)+".bmp"));
	    							if(which_mode == 1)
	        	          		  	{
	        	          		  		System.exit(0);
	        	          		  	}
	    							
	    	    	    	 } 
	    	    	    	 catch (IOException e1) {
	    							// TODO Auto-generated catch block
	    							e1.printStackTrace();
	    						}
	    	    	    	break;
	    	    	     }
	    	    	     
	    	    	     case 8: 
	    	    	     {
	    	    	    	 try {
	    							ImageIO.write(ConvertUtil.convert8(image_to_save), "BMP", new File(c.getCurrentDirectory().toString()+ '\\' +(first_part+digitss+second_part)+".bmp"));
	    							if(which_mode == 1)
	        	          		  	{
	        	          		  		System.exit(0);
	        	          		  	}
	    	    	    	 } 
	    	    	    	 catch (IOException e1) {
	    							// TODO Auto-generated catch block
	    							e1.printStackTrace();
	    						}
	    	    	    	break;
	    	    	     }
	    	    	     case 32:
	    	    	     {
	    	    	    	 try {
	    							ImageIO.write(ConvertUtil.convert32(image_to_save), "BMP", new File(c.getCurrentDirectory().toString()+ '\\' +(first_part+digitss+second_part)+".bmp"));
	    							if(which_mode == 1)
	        	          		  	{
	        	          		  		System.exit(0);
	        	          		  	}
	    	    	    	 } catch (IOException e1) {
	    							// TODO Auto-generated catch block
	    							e1.printStackTrace();
	    						}
	    	    	    	break;
	    	    	     }
	    	    	     
   	    	     }
   	    		 
   	    	  }
   	    	  else if (chosen_type == "tiff")
   	    	  {
   	    		  try 			
 	    	        {  
 	    	            ImageIO.write(image_to_save, "TIFF", new File(c.getCurrentDirectory().toString()+ '\\' +(first_part+digitss+second_part)+".tiff"));  
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
   	
			//end basic save
   	      licznik=licznik+step_value;
   	      digitss="";
			}
		}
		
    
    });
    
    
    //Magda
    
    
    
    p.add(save);
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
    
    panel.add(comboBox);
    comboBox.setBounds(97, 58, 100, 20);
    comboBox.addItem("png");
    comboBox.addItem("jpeg");
    comboBox.addItem("jpg");
    comboBox.addItem("bmp");
    comboBox.addItem("tiff");
    
    JComboBox<String> comboBox2 = new JComboBox<String>();
    comboBox2.setBounds(207, 58, 112, 20);
    comboBox2.addItem("1");
    comboBox2.addItem("8");
    comboBox2.addItem("16");
    comboBox2.addItem("24");
    comboBox2.addItem("32");
    
    comboBox2.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		chosen_depth =(int) comboBox2.getSelectedItem();
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
    System.out.println("Variable after exiting actionlistner" + chosen_type);
    save.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		  JFileChooser c = new JFileChooser();
    	      // Demonstrate "Save" dialog:
    	      int rVal = c.showSaveDialog(SaveImage.this);
    	      if (rVal == JFileChooser.APPROVE_OPTION) {
    	        filename.setText(c.getSelectedFile().getName());
    	        dir.setText(c.getCurrentDirectory().toString());
    	        
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
    	    	  else if(chosen_type == "bmp")
    	    	  {	
    	    		  
    	    	     switch(chosen_depth)
    	    	     {	
	    	    	     case 1: 
	    	    	     {
	    	    	    	 try {
	    							ImageIO.write(ConvertUtil.convert1(image_to_save), "BMP", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".bmp"));
	    							if(which_mode == 1)
	        	          		  	{
	        	          		  		System.exit(0);
	        	          		  	}
	    							
	    	    	    	 } 
	    	    	    	 catch (IOException e) {
	    							// TODO Auto-generated catch block
	    							e.printStackTrace();
	    						}
	    	    	    	break;
	    	    	     }
	    	    	     
	    	    	     case 8: 
	    	    	     {
	    	    	    	 try {
	    							ImageIO.write(ConvertUtil.convert8(image_to_save), "BMP", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".bmp"));
	    							if(which_mode == 1)
	        	          		  	{
	        	          		  		System.exit(0);
	        	          		  	}
	    	    	    	 } 
	    	    	    	 catch (IOException e) {
	    							// TODO Auto-generated catch block
	    							e.printStackTrace();
	    						}
	    	    	    	break;
	    	    	     }
	    	    	     case 32:
	    	    	     {
	    	    	    	 try {
	    							ImageIO.write(ConvertUtil.convert32(image_to_save), "BMP", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".bmp"));
	    							if(which_mode == 1)
	        	          		  	{
	        	          		  		System.exit(0);
	        	          		  	}
	    	    	    	 } catch (IOException e) {
	    							// TODO Auto-generated catch block
	    							e.printStackTrace();
	    						}
	    	    	    	break;
	    	    	     }
	    	    	     
    	    	     }
    	    		 
    	    	  }
    	    	  else if (chosen_type == "tiff")
    	    	  {
    	    		  try 			
  	    	        {  
  	    	            ImageIO.write(image_to_save, "TIFF", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".tiff"));  
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
    	((JTextField)comp[x]).setText(first_part+digitss+second_part);
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
}