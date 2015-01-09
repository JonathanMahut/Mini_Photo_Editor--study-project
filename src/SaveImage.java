// : c14:FileChooserTest.java
// Demonstration of File dialog boxes.
// From 'Thinking in Java, 3rd ed.' (c) Bruce Eckel 2002
// www.BruceEckel.com. See copyright notice in CopyRight.txt.

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
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

public class SaveImage extends JFrame {
  private JTextField filename = new JTextField(), dir = new JTextField();
  private static BufferedImage image_to_save;
  private JButton save = new JButton("Save");
  private final JPanel panel = new JPanel();

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
    lblTypeOfImage.setBounds(184, 11, 68, 50);
    panel.add(lblTypeOfImage);
    JComboBox<String> comboBox =new JComboBox<String>();
    panel.add(comboBox);
    comboBox.setBounds(0, 0, 217, 20);
    comboBox.addItem("png");
    comboBox.addItem("jpeg");
    comboBox.addItem("jpg");
    save.addActionListener(new SaveL((String) comboBox.getSelectedItem()));
  }

  class SaveL implements ActionListener {
	  String choosen_type_of_image;
	  SaveL(String image_type){
		  choosen_type_of_image = image_type; 
	  }
    public void actionPerformed(ActionEvent e) {
      JFileChooser c = new JFileChooser();
      // Demonstrate "Save" dialog:
      int rVal = c.showSaveDialog(SaveImage.this);
      if (rVal == JFileChooser.APPROVE_OPTION) {
        filename.setText(c.getSelectedFile().getName());
        dir.setText(c.getCurrentDirectory().toString());
    	  if(choosen_type_of_image  == "jpeg")
          {
          	  try 
                {  
          		  	ImageIO.write(image_to_save, "PNG", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".jpeg"));  
          	     
                    
                    
                }  
                catch ( IOException x ) {  
                    // Complain if there was any problem writing   
                    // the output file.  
                    x.printStackTrace();  
                }         
          }
    	  else if(choosen_type_of_image == "jpg")
    	  {
    		  try 
              {  
    			    ImageIO.write(image_to_save, "PNG", new File(c.getCurrentDirectory().toString()+ '\\' +c.getSelectedFile().getName()+".jpg"));  
    			     
                  
                  
              }  
              catch ( IOException x ) {  
                  // Complain if there was any problem writing   
                  // the output file.  
                  x.printStackTrace();  
              } 
    	  }
    	  else if(choosen_type_of_image == "png")
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
        
        
      }
      if (rVal == JFileChooser.CANCEL_OPTION) {
        filename.setText("You pressed cancel");
        dir.setText("");
      }
    }
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



           
         
    