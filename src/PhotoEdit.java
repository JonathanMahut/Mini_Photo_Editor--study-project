import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDesktopPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.Box;
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
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
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

public class PhotoEdit{
	JDesktopPane center= new JDesktopPane();
	int flag=0;
	private JFrame frame;
	int licznik=0;
	static int n_of_stripes=0;
	static String horizontal_or_vertical="wartosc";
	int directory_counter=0;
	static HashMap<JButton,File> all_chosen_images = new HashMap<JButton,File>(); //storing path to all images 
	static int which_merge_mode_was_chose =0;
	static BufferedImage merged_image=null;
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
	    frame.setLocationRelativeTo(null);

		//
		
		//////////////////
		// Ask before close if user want to save image
		
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we)
			{ 
				
				if(Display.where_global.getComponents().length>0)
				{
				
					 String ObjButtons[] = {"Yes","No"};
					 int PromptResult = JOptionPane.showOptionDialog(null,"You have unsaved images. \n Do you want to exit?","Question",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
					  if(PromptResult==JOptionPane.YES_OPTION)
					  {
      		    		System.exit(0);
					  }	  
				}
				
			  else
			  {
				  
				  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			  }
			}
			});
		
			frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.X_AXIS));			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
		
			//LEFT SIDE
			JPanel left_main=new JPanel();
			left_main.setLayout(new BoxLayout(left_main,BoxLayout.Y_AXIS));
			JTabbedPane tabbedPane = new JTabbedPane();
			left_main.add(tabbedPane);
			frame.getContentPane().add(BorderLayout.WEST,left_main);
			
			JPanel left_panel_1 = new JPanel();
			 
			left_panel_1.setBackground(Color.WHITE);
			left_panel_1.setLayout(new BoxLayout(left_panel_1, BoxLayout.Y_AXIS));
			tabbedPane.setPreferredSize(new Dimension(210, 400));// hardCoded sizing
			tabbedPane.setMaximumSize(new Dimension(210, 720));  // hardCoded sizing
			tabbedPane.setMinimumSize(new Dimension(210, 300));  // hardCoded sizing

			JPanel tab=new JPanel();
			tab.setLayout(new BorderLayout());
			  JScrollPane scrollPanel1 = new JScrollPane(left_panel_1);
			  scrollPanel1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			  scrollPanel1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			  tab.add(scrollPanel1,BorderLayout.CENTER);
			  
			tabbedPane.addTab("Directories", tab);
			tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
			
			JButton btnClearAll = new JButton("Clear all");
			btnClearAll.setBounds(28, 158, 89, 23);
			left_main.add(btnClearAll);
			tab.add(btnClearAll,BorderLayout.SOUTH);
			
			
			JPanel left_panel_2_main=new JPanel(new BorderLayout());
			
			JPanel left_panel_2 = new JPanel(new GridLayout(0,3));
			left_panel_2.setBackground(Color.WHITE);
			JScrollPane scrollPanel = new JScrollPane(left_panel_2);
		    scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		    scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    left_panel_2_main.add(scrollPanel,BorderLayout.CENTER);
			
			tabbedPane.addTab("Selected", left_panel_2_main);
			tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);


			JButton btnClearAll2 = new JButton("Clear all");
			btnClearAll2.setBounds(28, 158, 89, 23);
			left_main.add(btnClearAll2);
			left_panel_2_main.add(btnClearAll2,BorderLayout.SOUTH);
			
			//END OF LEFT SIDE
			
			
		//I think that we shouldn't add scrollpane to the frame
		//JScrollPane scrollPane = new JScrollPane(panel);		
		//panel.setLayout(new GridLayout(0, 5, 0,0));
		//frame.getContentPane().add(scrollPane);
		
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
				    	  flag++;
				      }
				      else				      { flag=0;
				    	  directory_counter++;
				      licznik=directory_counter-AddDirectory.iter;
				     }  // increase counter by one
				     if(licznik<=5&&flag==0)
				     {
				      for(int i =0; i<file_from_given_directory.length; i++)
				      {	
				    	  
				    	  	
				    	    final String pathToImage = file_from_given_directory[i].getAbsolutePath(); 
							JButton myButton = new JButton(new ImageIcon(((new ImageIcon(pathToImage)).getImage()).		//resizing img to fit with the button size
									  getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
							myButton.setOpaque(false);
							myButton.setContentAreaFilled(false);
							myButton.setBorderPainted(false);
							myButton.setPreferredSize(new Dimension(40, 40));		//size of the image icon
							
							all_chosen_images.put(myButton,file_from_given_directory[i]); // path of image -->the button
							buttonList.add(myButton);
							myButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									
					                final JPopupMenu menu = new JPopupMenu("Menu");	
					                
					                
					                ActionListener menuListener;
					                menuListener = new ActionListener() 		// action listener for items on JPopMenu
					                {
							            @Override
							            public void actionPerformed(ActionEvent event) {
  
							                   if (event.getActionCommand()=="View")
							                   {
							                	   
							                	   ShowImage image = new ShowImage(frame, pathToImage, pathToImage);  // enlarge chosen image
							                	   
							                   }
							                	
							                
							            }
					                };
					                
					                JMenuItem item;// menu after click on the image
					                menu.add(item = new JMenuItem("View"));
					                item.addActionListener(menuListener); 
					                menu.show(myButton, myButton.getWidth()/2, myButton.getHeight()/2);
					                
					            
								}
							});

				      }
				 	  			  JButton delete1= new JButton("Delete");
                	    		  AddDirectory first=new AddDirectory();
                	    		  HashMap<JButton,File> all_chosen_temp = new HashMap<JButton,File>();
                	    		  all_chosen_temp.putAll(all_chosen_images);
                	    		  ArrayList<JButton> buttonListTemp = new ArrayList<JButton>();	
                	    		  buttonListTemp.addAll(buttonList);// list where all loaded images are as buttons

                	    	   	  HashMap<JButton,File> clear = new HashMap<JButton,File>();
                	    		  clear.putAll(all_chosen_images);
                	    		  first.AddDirect(left_panel_1, chooser,left_panel_2,all_chosen_images,all_chosen_temp,btnClearAll2,buttonList,frame,delete1,clear,buttonListTemp);   
                	    		  buttonList.clear();
                		  	 	  all_chosen_images.clear();
                		  	 	  clear.clear();
                    			

				    }
				     else
				     { 
				    	 if(licznik>5)
				    	 {
				    	 JOptionPane.showMessageDialog(frame,
			    			    "You cannot add more than 5 directories",
			    			    "Error",
			    			    JOptionPane.ERROR_MESSAGE);}
						    directory_counter--;
				     		}
				     // licznik=directory_counter-AddDirectory.iter;
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
				
				if(center.getComponents().length>0)
				{
					 String ObjButtons[] = {"Yes","No"};
					 int PromptResult = JOptionPane.showOptionDialog(null,"You have unsaved images. \n Do you want to exit?","Question",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
					  if(PromptResult==JOptionPane.YES_OPTION)
					  {
      		    		System.exit(0);
					  }
						  
				}
				else
					frame.dispose();
				
			}
		});
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				merged_image=Display.imgglobal;
				if(merged_image != null)
				SaveImage.main(merged_image,0);
				else
				{
					  Object[] options = {"OK"};
					    int n = JOptionPane.showOptionDialog(frame,
					    		"Please first merge images","No source",
					                   JOptionPane.PLAIN_MESSAGE,
					                   JOptionPane.QUESTION_MESSAGE,
					                   null,
					                   options,
					                   options[0]);
				}
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
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		
		
		JButton btnMerge = new JButton("Merge");
		btnMerge.setBounds(28, 192, 89, 23);
		
		JPanel BasicMerge=new JPanel();
		BasicMerge.add(radiopanel);
		BasicMerge.add(btnMerge);
		
		panel_2a.add(BasicMerge);
		
		
		
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					int CHECK=AddDirectory.all_chosen.size();

				if(CHECK>0)
				{
					
					OptionFrame.main(null); // ask if user wants to see?
					OptionFrame quest_see_the_result=new OptionFrame(); // get answer from OptionFrame class
					Display disp= new Display(); // invoke Display class
					
					
					
		           switch(which_merge_mode_was_chose)
		           {
		        	   case 1:
		        		    MergeImageAND mergeImage1 = new MergeImageAND(AddDirectory.all_chosen);
		        		    merged_image = MergeImageAND.returnImage();
		        		    if (quest_see_the_result.GetSelectedOption() == JOptionPane.YES_OPTION) {
		        		    File one= MergeImageAND.getF().getAbsoluteFile();
		        		    disp.createFrame(center, one, merged_image); //create new frame with image
		        		    } 
		        		    else
		        		    {	
		        		    	OptionFrame2.main(null);
		        				OptionFrame2 quest_save_the_result= new OptionFrame2();
		        		    	if(quest_save_the_result.GetSelectedOption()==JOptionPane.YES_OPTION)
		        		    		{
		        		    		SaveImage.main(merged_image,0);
		        		    		merged_image = null;				// free variable keeping merged image
		        		    		}
		        		    }
							break;
		        	   case 2:
							MergeImageXOR mergeImage = new MergeImageXOR(AddDirectory.all_chosen);
							merged_image = MergeImageXOR.returnImage();
		        		    if (quest_see_the_result.GetSelectedOption() == JOptionPane.YES_OPTION) {
		        		    	
		        		    File two= MergeImageXOR.getF().getAbsoluteFile();
		        		    
		        		    disp.createFrame(center, two,merged_image); //create new frame with image
		        		    } 
		        		    else
		        		    {	
		        		    	OptionFrame2.main(null);
		        				OptionFrame2 quest_save_the_result= new OptionFrame2();
		        		    	if(quest_save_the_result.GetSelectedOption()==JOptionPane.YES_OPTION)
		        		    	{
		        		    		SaveImage.main(merged_image,0);
		        		    		merged_image = null;				// free variable keeping merged image
		        		    	}
		        		    }
		        		 
							break;
		        	   case 3:
							MergeImageOR mergeImage2 = new MergeImageOR(AddDirectory.all_chosen);
							
							merged_image = MergeImageOR.returnImage();
							
		        		    if (quest_see_the_result.GetSelectedOption() == JOptionPane.YES_OPTION) {
		        		    	
		        		    File three= MergeImageOR.getF().getAbsoluteFile();
		        		    
		        		    disp.createFrame(center, three,merged_image); //create new frame with image
		        		    } 
		        		    else
		        		    {	
		        		    	OptionFrame2.main(null);
		        				OptionFrame2 quest_save_the_result= new OptionFrame2();
		        		    	if(quest_save_the_result.GetSelectedOption()==JOptionPane.YES_OPTION)
		        		    		{
		        		    		SaveImage.main(merged_image,0);
		        		    		merged_image = null;				// free variable keeping merged image
		        		    		}
		        		    }
							break;
		           }
					
					//LayeredImage mergeImage = new LayeredImage(all_chosen_images); 
					//z ostatniego folderu
					
				}
				else
				{
					
					  Object[] options = {"OK"};
					    int n = JOptionPane.showOptionDialog(frame,
					    		"Please first select images to merge","Empty work list",
					                   JOptionPane.PLAIN_MESSAGE,
					                   JOptionPane.QUESTION_MESSAGE,
					                   null,
					                   options,
					                   options[0]);
				}
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
	
	
	JPanel panel_2c = new JPanel();
	panel_2c.setOpaque(true);
	panel_2c.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "Cutouts options"));
	panel_2c.setLayout(new BoxLayout(panel_2c, BoxLayout.Y_AXIS));
	
	JPanel MergeButtons =new JPanel(); // Panel for buttons from merge options TUTAJ KUBA
	MergeButtons.setLayout(new BoxLayout(MergeButtons, BoxLayout.X_AXIS));
	
	JPanel MergeButtons_left=new JPanel();
	MergeButtons_left.setLayout(new BoxLayout(MergeButtons_left, BoxLayout.Y_AXIS));
	
	JPanel mergepanel =new JPanel();
	mergepanel.setLayout(new BoxLayout(mergepanel, BoxLayout.Y_AXIS));
	
	JRadioButton Merge_Button = new JRadioButton("HORIZONTAL");
	Merge_Button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			horizontal_or_vertical = "H";

		}
	});
	Merge_Button.setBounds(78, 67, 47, 23);
	mergepanel.add(Merge_Button);
	
	JRadioButton Merge_Button_1 = new JRadioButton("VERTICAL");
	Merge_Button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			horizontal_or_vertical = "V";
		}
	});
	Merge_Button_1.setBounds(78, 67, 47, 23);
	mergepanel.add(Merge_Button_1);
	ButtonGroup group1 = new ButtonGroup();
	group.add(Merge_Button);
	group.add(Merge_Button_1);
	
	
	JPanel Stripes =new JPanel();
	//Stripes.setLayout(new BoxLayout(Stripes, BoxLayout.Y_AXIS));
	
	JLabel Set1  = new JLabel("Number of stripes");
	JTextField inSet1  = new JTextField( 7 );
	
	JButton SetButton1 = new JButton("SET");
	
	SetButton1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                int mode =0; // enlarge or shrink
               
               
                n_of_stripes = Integer.parseInt(inSet1.getText());
               
                ObligatoryFunction3  fun = new ObligatoryFunction3(AddDirectory.all_chosen,n_of_stripes,horizontal_or_vertical,mode);
                merged_image = fun.returnImage();
                OptionFrame.main(null); // ask if user wants to see?
                OptionFrame quest_see_the_result=new OptionFrame(); // get answer from OptionFrame class
                Display disp= new Display(); // invoke Display class
               
                if (quest_see_the_result.GetSelectedOption() == JOptionPane.YES_OPTION) {
            File one= ObligatoryFunction3.getF().getAbsoluteFile();
           
            disp.createFrame(center, one,merged_image); //create new frame with image
            }
            else
            {  
                OptionFrame2.main(null);
                        OptionFrame2 quest_save_the_result= new OptionFrame2();
                if(quest_save_the_result.GetSelectedOption()==JOptionPane.YES_OPTION)
                        {
                        SaveImage.main(merged_image,0);
                        merged_image = null;                            // free variable keeping merged image
                        }
            }
               
               
        }
});
	
	Stripes.add(Set1);
	Stripes.add(inSet1);
	//Stripes.add(SetButton1);
	MergeButtons_left.add(mergepanel);
	MergeButtons_left.add(Stripes);
	
	  SetButton1.setBounds(28, 158, 89, 23);
	  MergeButtons_left.add(SetButton1);

	MergeButtons.add(MergeButtons_left);

	panel_2c.add(MergeButtons); //end of Panel for buttons from merge
	panel_2.add(panel_2c);

	
	// koniec
	
	// Refresh button
	JPanel panel_2d = new JPanel();
	JButton btnRefresh = new JButton("Refresh");
	btnRefresh.setBounds(28, 158, 89, 23);
	panel_2d.add(btnRefresh);
	JButton btnClear = new JButton("Clear");
	btnRefresh.setBounds(28, 158, 89, 23);
	panel_2d.add(btnClear);
	
	panel_2.add(panel_2d);
	
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
	//Clearing whole left panel when clicking "Clear All"
	btnClearAll.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  for (int i=licznik;i>=0;i--)
	    	  {
	    		  buttonList.clear();
	    			  directory_counter=0;
	    			  licznik=0;
	    			  AddDirectory.iter=0;
	    			   left_panel_1.removeAll(); 
	    			   left_panel_1.updateUI();	
	    			  
	    	  }
	    		 
	    	  
	      }
	    });
	
	}
	
	
}
