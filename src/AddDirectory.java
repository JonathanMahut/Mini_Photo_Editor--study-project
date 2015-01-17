import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
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
	int check_flag=0;
	//static JPanel image=new JPanel();
	void AddDirect(JPanel where, File[] file_from_given_directory1, JPanel where2,HashMap<JButton, File> all_chosen_images,HashMap<JButton,File> all_chosen_temp,JButton btnClearAll2,ArrayList<JButton> buttonList,JFrame frame,JButton delete1,HashMap<JButton,File> clear,ArrayList<JButton> buttonListTemp2){
		HashMap<JButton,File> clear_inner = new HashMap<JButton,File>();
		clear_inner.putAll(clear);
		check_flag=0;
		File file_from_given_directory[]=file_from_given_directory1;	
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
		
		 
		  label1.setText(file_from_given_directory[1].getParent());
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
		    	  all_chosen_temp.putAll(clear_inner);

		  		all_chosen_images.putAll(all_chosen_temp);
		    	  JPanel image_main= new JPanel(new BorderLayout());
					 
		    	  if(check_flag>=1)
		    	  {all_chosen_images.putAll(all_chosen_temp);}
		    	  check_flag++;
		  		ArrayList<JButton> buttonListTemp=new ArrayList<JButton>();
		  		//all_chosen.putAll(all_chosen_images);
		  		 
		  		buttonListTemp.clear();
		  		buttonListTemp.addAll(buttonList1);
		  		 
		    	for (int i=0;i<image.getComponentCount();i++)
		    		{
		    		  
                    Image image = null;
		    		 try {
                         image = ImageIO.read(file_from_given_directory[i]);
                 } catch (IOException e1) {
                         // TODO Auto-generated catch block
                         e1.printStackTrace();
                 }
                         image =image.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
                         ImageIcon icon = new ImageIcon(image);
                         
		    		 final String pathToImage = file_from_given_directory[i].getAbsolutePath(); 
			JButton myButton = new JButton(icon);
			myButton.setOpaque(false);
			myButton.setContentAreaFilled(false);
			myButton.setBorderPainted(false);
			myButton.setSize(new Dimension(40, 40));		//size of the image icon

			buttonList1.add(myButton);
			buttonListTemp.add(buttonList1.get(i));
			
			all_chosen_images.put(myButton,file_from_given_directory[i]); // path of image -->the button	
			all_chosen_temp.put(myButton,file_from_given_directory[i]);
			all_chosen.put(myButton, file_from_given_directory[i]);
			where2.add(myButton);
			myButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
	                final JPopupMenu menu = new JPopupMenu("Menu");	
	                
	                
	                
	                ActionListener menuListener;
	                menuListener = new ActionListener() 		// action listener for items on JPopMenu
	                {
			            @Override
			            public void actionPerformed(ActionEvent event) {
			            	if( event.getActionCommand()=="Delete")				
			                   {
		
			            		 for(Iterator<Map.Entry<JButton,File>>it=all_chosen.entrySet().iterator();it.hasNext();){
						              
			                		   Map.Entry<JButton,File> entry = it.next();
			          
			                		   if (entry.getKey() == myButton) {
			                			   
			                		   it.remove();
			                		   
			                		  
              		  	 	  
			                		   }
			                		 
			                		   }
			            	
			            		 
			                	   Iterator<JButton> i = buttonListTemp.iterator();
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
		    		}
		    	
		    	all_chosen_images.clear();
	    		where2.updateUI();	   
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
		    	  check_flag=1;
		    	  all_chosen.clear();
		    	  all_chosen_temp.clear();
		    	  all_chosen_temp.putAll(clear_inner);
		    	  all_chosen_images.clear();
		    	  
		    	  where2.removeAll(); 
		    	  where2.updateUI();	
		      }
		    });
		  
		  
		  
	}
	
	HashMap<JButton, File> getAll(){
		return all_chosen;
	}
}
