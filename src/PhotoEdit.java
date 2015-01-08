import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDesktopPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
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
		frame.setBounds(200, 200, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(new GridLayout(0, 3, 0, 0));
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
		
		//I think that we shouldn't add scrollpane to the frame
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
		
		JDesktopPane center= new JDesktopPane();
		frame.getContentPane().add(BorderLayout.CENTER, center);
		center.setBackground(Color.darkGray);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(BorderLayout.WEST, panel_1);
		panel_1.setSize(30, 10);
		panel_1.setLayout(null);
		
		// why it's not working?
		JScrollPane scroller= new JScrollPane(panel_1);
		
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(BorderLayout.EAST, panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_2a = new JPanel();
		panel_2a.setOpaque(true);
		panel_2a.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "Merge options"));
		panel_2a.setLayout(new BoxLayout(panel_2a, BoxLayout.Y_AXIS));
		panel_2.add(panel_2a);
		
		
		JPanel radiopanel =new JPanel();
		radiopanel.setLayout(new BoxLayout(radiopanel, BoxLayout.Y_AXIS));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("AND");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				which_merge_mode_was_chose = 1;
			}
		});
		rdbtnNewRadioButton.setBounds(78, 67, 47, 23);
		radiopanel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("XOR");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				which_merge_mode_was_chose = 2;
			}
		});
		rdbtnNewRadioButton_1.setBounds(78, 92, 47, 23);
		radiopanel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("OR");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				which_merge_mode_was_chose = 3;
			}
		});
		
		rdbtnNewRadioButton_2.setBounds(78, 41, 60, 23);
		radiopanel.add(rdbtnNewRadioButton_2);
		
		//panel_2a.add(radiopanel);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		
		
		JPanel MergeButtons =new JPanel(); // Panel for buttons from merge options
		MergeButtons.setLayout(new BoxLayout(MergeButtons, BoxLayout.Y_AXIS));
		
		JButton btnMerge = new JButton("Merge");
		btnMerge.setBounds(28, 192, 89, 23);
		
		JPanel BasicMerge=new JPanel();
		BasicMerge.add(radiopanel);
		BasicMerge.add(btnMerge);
		
		panel_2a.add(BasicMerge);
		panel_2a.add(MergeButtons); //end of Panel for buttons from merge options
		
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				OptionFrame.main(null);  // ask if user wants to see?
				OptionFrame quest=new OptionFrame(); // get answer from OptionFrame class
    		    Display disp= new Display(); // invoke Display class
				
	           switch(which_merge_mode_was_chose)
	           {
	        	   case 1:
	        		    MergeImageAND mergeImage1 = new MergeImageAND(all_chosen_images);
	        		    if (quest.GetSelectedOption() == JOptionPane.YES_OPTION) {
	        		    File one= MergeImageAND.getF().getAbsoluteFile();
	        		    disp.createFrame(center, one); //create new frame with image
	        		    } 
						break;
	        	   case 2:
						MergeImageXOR mergeImage = new MergeImageXOR(all_chosen_images);
	        		    if (quest.GetSelectedOption() == JOptionPane.YES_OPTION) {
	        		    File two= MergeImageXOR.getF().getAbsoluteFile();
	        		    disp.createFrame(center, two); //create new frame with image
	        		    } 
						break;
	        	   case 3:
						MergeImageOR mergeImage2 = new MergeImageOR(all_chosen_images);
	        		    if (quest.GetSelectedOption() == JOptionPane.YES_OPTION) {
	        		    File three= MergeImageOR.getF().getAbsoluteFile();
	        		    disp.createFrame(center, three); //create new frame with image
	        		    } 
						break;
	           }
				
				//LayeredImage mergeImage = new LayeredImage(all_chosen_images); 
				//z ostatniego folderu
				
			}
			
		});
		
		
		
		//Frames
		
		JPanel panel_2b = new JPanel();
	panel_2b.setOpaque(true);
	panel_2b.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "Frame options"));
	panel_2b.setLayout(new BoxLayout(panel_2b, BoxLayout.Y_AXIS));
	panel_2.add(panel_2b);
	JButton frame_button = new JButton("ADD FRAME");
	panel_2b.add(frame_button);
	JLabel Set  = new JLabel("Set treshold");
	JPanel SetPanel   = new JPanel();
	JTextField inSet  = new JTextField( 7 );
	JButton SetButton = new JButton("SET");
	SetPanel.add(Set);
	SetPanel.add(inSet);
	SetPanel.add(SetButton);
	panel_2b.add(SetPanel);
	//panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.PAGE_AXIS));
		
	
	// Refresh button
	JPanel panel_2c = new JPanel();
	JButton btnRefresh = new JButton("Refresh");
	btnRefresh.setBounds(28, 158, 89, 23);
	panel_2c.add(btnRefresh);
	JButton btnClear = new JButton("Clear");
	btnRefresh.setBounds(28, 158, 89, 23);
	panel_2c.add(btnClear);
	
	panel_2.add(panel_2c);
	
	btnClear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//remove all components in panel.
			center.removeAll(); 
			// refresh the panel.
			center.updateUI();
		}
	});
	
	btnRefresh.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.repaint(); //works only when item is deleted
			frame.validate(); //works only when item is created WTF?x2
		}
		
		
	});
	
	}
}
