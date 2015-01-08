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

public class SaveImage extends JFrame {
  private JTextField filename = new JTextField(), dir = new JTextField();
  private static BufferedImage image_to_save;
  private JButton save = new JButton("Save");
  private final JPanel panel = new JPanel();

  SaveImage(BufferedImage merged_image) {
	  image_to_save=merged_image;
    JPanel p = new JPanel();
    save.addActionListener(new SaveL());
    p.add(save);
    Container cp = getContentPane();
    cp.add(p, BorderLayout.SOUTH);
    dir.setEditable(false);
    filename.setEditable(false);
    p = new JPanel();
    p.setLayout(new GridLayout(2, 1));
    p.add(filename);
    p.add(dir);
    cp.add(p, BorderLayout.NORTH);
    
    getContentPane().add(panel, BorderLayout.CENTER);
    panel.setLayout(null);
    
    JComboBox comboBox =new JComboBox();
    comboBox.setBounds(259, 33, 28, 20);
    panel.add(comboBox);
  }

  class SaveL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      JFileChooser c = new JFileChooser();
      // Demonstrate "Save" dialog:
      int rVal = c.showSaveDialog(SaveImage.this);
      if (rVal == JFileChooser.APPROVE_OPTION) {
        filename.setText(c.getSelectedFile().getName());
        dir.setText(c.getCurrentDirectory().toString());
        
        
        try {  
            ImageIO.write( image_to_save, "PNG", new File(c.getCurrentDirectory().toString()) );  
            
        }  
        catch ( IOException x ) {  
            // Complain if there was any problem writing   
            // the output file.  
            x.printStackTrace();  
        }         
        
      }
      if (rVal == JFileChooser.CANCEL_OPTION) {
        filename.setText("You pressed cancel");
        dir.setText("");
      }
    }
  }

  public static void main(String[] args) {
    run(new SaveImage(image_to_save), 250, 110);
  }

  public static void run(JFrame frame, int width, int height) {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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



           
         
    