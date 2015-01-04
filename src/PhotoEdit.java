import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
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


//add refresh button
//add enlarge button

public class PhotoEdit {

	private JFrame frame;
	
	static HashMap<JButton,File> all_chosen_images = new HashMap<JButton,File>(); //storing path to all images 
	
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
			JPanel panel = new JPanel();
		
		
		JScrollPane scrollPane = new JScrollPane(panel);		
		panel.setLayout(new GridLayout(0, 5, 0,0));			// problem when number of img is <25 !
		scrollPane.setBounds(0, 0, 222, 240);
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
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(223, 0, 211, 240);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnMerge = new JButton("Merge");
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LayeredImage mergeImage = new LayeredImage(all_chosen_images); 
				//z ostatniego folderu
				
			}
		});
		btnMerge.setBounds(10, 206, 89, 23);
		panel_1.add(btnMerge);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.repaint(); //works only when item is deleted
				frame.validate(); //works only when item is created WTF?x2
				
			}
		});
		btnRefresh.setBounds(112, 206, 89, 23);
		panel_1.add(btnRefresh);
		
		
		
		
		
		
		
		
	
		
		
	}	
}
