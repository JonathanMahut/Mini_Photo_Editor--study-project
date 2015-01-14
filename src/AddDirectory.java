import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;



public class AddDirectory {
	static HashMap<JButton,File> all_chosen = new HashMap<JButton,File>();
	static int iter=0;
	//static JPanel image=new JPanel();
	void AddDirect(JPanel where, JFileChooser chooser, JPanel where2,HashMap<JButton, File> all_chosen_images,JButton btnClearAll2,ArrayList<JButton> buttonList,JFrame frame,JButton delete1){
		 File file_from_given_directory[]=chooser.getSelectedFile().listFiles(new ImageFileFilter());	
			 JPanel image_main= new JPanel(new BorderLayout());
			 ArrayList<JButton> buttonList1=buttonList;
			 JPanel directory=new JPanel();
			JPanel image=new JPanel();
		 
		for(int i =0; i<file_from_given_directory.length; i++)
		{image.add(buttonList1.get(i));}
		
		JTextField label1 = new JTextField(); 
		label1.setPreferredSize( new Dimension( 150, 20 ) );;
		label1.setMaximumSize(new Dimension( 150, 20 ));
		label1.setOpaque(false);
		JScrollBar sb1 = new JScrollBar(JScrollBar.HORIZONTAL);  
		label1.add(sb1);
		BoundedRangeModel brm = label1.getHorizontalVisibility();
		sb1.setModel(brm);

		directory.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY));
		directory.setPreferredSize(new Dimension(where.getWidth(), 140));
		directory.setMaximumSize( directory.getPreferredSize() );
		directory.setLayout(new BoxLayout(directory, BoxLayout.Y_AXIS));
		
		JButton select1= new JButton("Select");
		
		image.setLayout(new GridLayout(1,0));
		image.setMaximumSize(new Dimension(where.getWidth(), 150));
		
		JScrollPane scrollPanel = new JScrollPane(image);
	    scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	    image_main.add(scrollPanel,BorderLayout.CENTER);
		
		 
		  label1.setText(chooser.getCurrentDirectory().getAbsolutePath());
		  directory.add(label1);
		  directory.add(sb1);
		  directory.add(image_main);
		  JPanel buttons=new JPanel();
		  buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		  buttons.add(select1);
		  buttons.add(delete1);
		  directory.add(buttons);
		  where.add(directory);
		  buttonList1.clear(); 
		  
		  select1.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {

		    	for (int i=0;i<image.getComponentCount();i++)
		    		{
		    		 final String pathToImage = file_from_given_directory[i].getAbsolutePath(); 
			JButton myButton = new JButton(new ImageIcon(((new ImageIcon(pathToImage)).getImage()).		//resizing img to fit with the button size
					  getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
			myButton.setOpaque(false);
			myButton.setContentAreaFilled(false);
			myButton.setBorderPainted(false);
			myButton.setSize(new Dimension(40, 40));		//size of the image icon

			buttonList1.add(myButton);
			all_chosen_images.put(myButton,file_from_given_directory[i]); // path of image -->the button			
			where2.add(myButton);
			
			myButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
	                final JPopupMenu menu = new JPopupMenu("Menu");	
	                
	                
	                ActionListener menuListener;
	                menuListener = new ActionListener() 		// action listener for items on JPopMenu
	                {
			            @Override
			            public void actionPerformed(ActionEvent event) {
			            	if( event.getActionCommand()=="Delete")				// graphic problem after deleting buttons(need to refresh to see result)
			                   {
			                	  
			                	   Iterator<JButton> i = buttonList1.iterator();
			                	   while (i.hasNext())
			                	   {							
			  							//searching for given button on the list of buttons to remove from it
				                		  JButton o = i.next();
				                	      if(o==myButton)
				                	      {		  Container temp=myButton.getParent().getParent();
				                	    		  myButton.getParent().remove(myButton);
				                	    		  if(all_chosen.size()<=0)  // not working
				                	    		  {where2.remove(temp);}
				                	    	where2.updateUI();	
				                	    	  i.remove();
				                	    	  frame.repaint(); //works only when item is deleted
				              				  frame.validate(); 
				                	    	  
				                	    	  break;
			                	      }
			                	   
			                	   }
			                	  
			                	   Iterator<JButton> keySetIterator =  all_chosen.keySet().iterator();

				                	   while(keySetIterator.hasNext()){
				                	     JButton key = keySetIterator.next();
				                	     
				                	     if(key==myButton)
				                	    	 all_chosen.remove(key);		//remove given item to delete redundant path.
				                	     
				                	   }
				                	   
				                   }
			                   if (event.getActionCommand()=="View")
			                   {
			                	   
			                	   ShowImage image = new ShowImage(frame, pathToImage, pathToImage);  // enlarge chosen image
			                	   
			                   }
			                	
			                   
			            }   
	                };
	                
	                JMenuItem item;// menu after click on the image
	                menu.add(item = new JMenuItem("Delete"));
	                item.addActionListener(menuListener);
	                menu.add(item = new JMenuItem("View"));
	                item.addActionListener(menuListener); 
	                menu.show(myButton, myButton.getWidth()/2, myButton.getHeight()/2);
	                
	            
				}
			});
		//	all_chosen_images.put(myButton,file_from_given_directory[i]); // path of image -->the button
		    		}
		  // all_chosen.putAll(all_chosen_images);
	    		where2.updateUI();	   
	  	 		all_chosen.putAll(all_chosen_images);
	  	 		buttonList1.clear();

		      }
		      
		    });
		  
		  delete1.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    		iter++;
		    		buttonList.clear();
		    	  image.removeAll();
    			  image_main.removeAll();	 
    			  directory.removeAll();
		    	 where.remove(directory); 
		    		where.updateUI();	
}
		    });
		  
		  btnClearAll2.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  all_chosen.clear();
		    	  where2.removeAll(); 
		    	  where2.updateUI();	
		      }
		    });
		  
		  
		  
	}
	
	HashMap<JButton, File> getAll(){
		return all_chosen;
	}
}
