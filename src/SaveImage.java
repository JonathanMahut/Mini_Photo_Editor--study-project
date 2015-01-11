// : c14:FileChooserTest.java
// Demonstration of File dialog boxes.
// From 'Thinking in Java, 3rd ed.' (c) Bruce Eckel 2002
// www.BruceEckel.com. See copyright notice in CopyRight.txt.

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import net.sf.image4j.util.ConvertUtil;

public class SaveImage extends JFrame {
  private JTextField filename = new JTextField(), dir = new JTextField();
  private static BufferedImage image_to_save;
  private JButton save = new JButton("Save");
  private final JPanel panel = new JPanel();
  private static String chosen_type = "png";
  private  int chosen_depth = 0;

  SaveImage(BufferedImage merged_image) {
	  image_to_save=merged_image;
	 
	    
    JPanel p = new JPanel();
    
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
	    						} catch (IOException e) {
	    							// TODO Auto-generated catch block
	    							e.printStackTrace();
	    						}
	    	    	    	break;
	    	    	     }
	    	    	     
	    	    	     case 8: 
	    	    	     {
	    	    	    	 try {
	    							ImageIO.write(ConvertUtil.convert8(image_to_save), "BMP", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".bmp"));
	    						} catch (IOException e) {
	    							// TODO Auto-generated catch block
	    							e.printStackTrace();
	    						}
	    	    	    	break;
	    	    	     }
	    	    	     case 32:
	    	    	     {
	    	    	    	 try {
	    							ImageIO.write(ConvertUtil.convert32(image_to_save), "BMP", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".bmp"));
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
  
  

 
  public static void main(BufferedImage image_to_save1) {
    run(new SaveImage(image_to_save1), 400, 200);
  }

  public static void run(JFrame frame, int width, int height) {
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(width, height);
    frame.setVisible(true);
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
} ///:~



           
         
    