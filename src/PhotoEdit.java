import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;


//add refresh button
//add enlarge button

public class PhotoEdit {

	private JFrame frame;
	
	static HashMap<JButton,File> all_chosen_images = new HashMap<JButton,File>(); //storing path to all images 
	static int which_merge_mode_was_chose =0;
	ArrayList<JButton> buttonList = new ArrayList<JButton>();			// list where all loaded images are as buttons
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhotoEdit window = new PhotoEdit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PhotoEdit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(new GridLayout(0, 3, 0, 0));
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
		
		
		JScrollPane scrollPane = new JScrollPane(panel);		
		panel.setLayout(new GridLayout(0, 5, 0,0));
		frame.getContentPane().add(scrollPane);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() 
		{
					JFileChooser chooser;
					String chooser_title;
					File file_from_given_directory[];
					
					
				public void actionPerformed(ActionEvent e) {
					
				        
				    FileFilter imageFilter = new FileNameExtensionFilter(
				    	    "Image files", ImageIO.getReaderFileSuffixes());
				    
				    chooser = new JFileChooser(); 
				    chooser.setCurrentDirectory(new java.io.File("."));
				    chooser.setDialogTitle(chooser_title);
				    chooser.addChoosableFileFilter(imageFilter);							// Filter to show only images in directory
				    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    //
				    // disable the "All files" option.
				    //
				    chooser.setAcceptAllFileFilterUsed(false);
				    //    
				    if (chooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) { 				
				      System.out.println("getCurrentDirectory(): " 					// delete it in final version
				         +  chooser.getCurrentDirectory());
				      System.out.println("getSelectedFile() : " 
				         +  chooser.getSelectedFile());
				      
				      
				      file_from_given_directory=chooser.getSelectedFile().listFiles(new ImageFileFilter());				//save list of all files from chosen directory
				      if(file_from_given_directory.length == 0)
				      {
				    	  JOptionPane.showMessageDialog(frame,
				    			    "The directory has not got any images.",
				    			    "Error",
				    			    JOptionPane.ERROR_MESSAGE);
				      }
				      
				      
				      for(int i =0; i<file_from_given_directory.length; i++)
				      {	
				    	  
				    	  	
				    	    final String pathToImage = file_from_given_directory[i].getAbsolutePath(); 
							JButton myButton = new JButton(new ImageIcon(((new ImageIcon(pathToImage)).getImage()).		//resizing img to fit with the button size
									  getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
							
							myButton.setPreferredSize(new Dimension(40, 40));		//size of the image icon
							
							all_chosen_images.put(myButton,file_from_given_directory[i]); // path of image -->the button
							
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
							                	  
							                	   Iterator<JButton> i = buttonList.iterator();
							                	   while (i.hasNext())
							                	   {							//searching for given button on the list of buttons to remove from it
							                		  JButton o = i.next();
							                	      if(o==myButton)
							                	      {

							                	    	  panel.remove(myButton);
							                	    	  i.remove();
							                	    	  frame.repaint(); //works only when item is deleted
							              				  frame.validate(); 
							                	    	  
							                	    	  break;
							                	      }
							                	   
							                	   }
							                	  
							                	   Iterator<JButton> keySetIterator =  all_chosen_images.keySet().iterator();

								                	   while(keySetIterator.hasNext()){
								                	     JButton key = keySetIterator.next();
								                	     
								                	     if(key==myButton)
								                	    	 all_chosen_images.remove(key);		//remove given item to delete redundant path.
								                	     
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
						      buttonList.add(myButton);
						      panel.add(buttonList.get(buttonList.size()-1));
				      }
    
				      }
				    else { 
				      System.out.println("No Selection ");
				      }
				      frame.repaint(); 
	  				  frame.validate();
				     }
				
				
			}
		);
		mnFile.add(mntmOpen);
		
		
		JMenuItem mntmExit= new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnFile.add(mntmSave);
		mnFile.add(mntmExit);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
	
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnMerge = new JButton("Merge");
		btnMerge.setBounds(28, 192, 89, 23);
		panel_2.add(btnMerge);
		
		JButton btndde = new JButton("Mlbbl");
		btndde.setBounds(28, 220, 89, 23);
		panel_2.add(btndde);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(28, 158, 89, 23);
		panel_2.add(btnRefresh);
		
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("AND");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				which_merge_mode_was_chose = 1;
			}
		});
		rdbtnNewRadioButton.setBounds(8, 65, 109, 23);
		panel_2.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("XOR");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				which_merge_mode_was_chose = 2;
			}
		});
		rdbtnNewRadioButton_1.setBounds(8, 91, 109, 23);
		panel_2.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("OR");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				which_merge_mode_was_chose = 3;
			}
		});
		rdbtnNewRadioButton_2.setBounds(8, 39, 109, 23);
		panel_2.add(rdbtnNewRadioButton_2);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.repaint(); //works only when item is deleted
				frame.validate(); //works only when item is created WTF?x2
				
			}
		});
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
	            
	           switch(which_merge_mode_was_chose)
	           {
	        	   case 1:
	        		    MergeImageAND mergeImage1 = new MergeImageAND(all_chosen_images);
						break;
	        	   case 2:
						
						MergeImageXOR mergeImage = new MergeImageXOR(all_chosen_images);
						break;
	        	   case 3:
						MergeImageOR mergeImage2 = new MergeImageOR(all_chosen_images);
						break;
	           }
				
			
				
				//LayeredImage mergeImage = new LayeredImage(all_chosen_images); 
				//z ostatniego folderu
				
			}
		});
		
		
		
		
		
		
		
		
	
		
		
	}	
}
