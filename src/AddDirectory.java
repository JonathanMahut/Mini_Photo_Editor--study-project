import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoundedRangeModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



public class AddDirectory {
	
	void AddDirect(JPanel where, JPanel directory, JFileChooser chooser, JPanel image, JPanel where2,HashMap<JButton, File> all_chosen_images){
		JTextField label1 = new JTextField(); 
		label1.setPreferredSize( new Dimension( 150, 20 ) );;
		JScrollBar sb1 = new JScrollBar(JScrollBar.HORIZONTAL);  
		label1.add(sb1);
		BoundedRangeModel brm = label1.getHorizontalVisibility();
		sb1.setModel(brm);
		
		JButton select1= new JButton("Select");
		JButton delete1= new JButton("Delete");

		JPanel image_main= new JPanel(new BorderLayout());
		image.setLayout(new GridLayout(1,0));
		
		JScrollPane scrollPanel = new JScrollPane(image);
	    scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	    image_main.add(scrollPanel,BorderLayout.CENTER);
		
		 
		  label1.setText(chooser.getCurrentDirectory().getAbsolutePath());
		  directory.add(label1);
		  directory.add(sb1);
		  directory.add(image_main);
		  directory.add(select1);
		  directory.add(delete1);
		   where.add(directory);
		   
		 File file_from_given_directory[]=chooser.getSelectedFile().listFiles(new ImageFileFilter());	
		 
		  
		  
		  select1.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	for (int i=0;i<image.getComponentCount();i++)
		    		{
		    		 final String pathToImage = file_from_given_directory[i].getAbsolutePath(); 
			JButton myButton = new JButton(new ImageIcon(((new ImageIcon(pathToImage)).getImage()).		//resizing img to fit with the button size
					  getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
			
			myButton.setSize(new Dimension(40, 40));		//size of the image icon

			all_chosen_images.put(myButton,file_from_given_directory[i]); // path of image -->the button
		    		where2.add(myButton);
		    		}
	    		//where.updateUI();	    		
		      }
		    });
		  
		  delete1.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    		where.remove(directory); 
		    		where.updateUI();		
		      }
		    });
		  
	}
}
