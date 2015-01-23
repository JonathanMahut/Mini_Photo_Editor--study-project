import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
     
     
   /**
    * The main class with the gui of the program.
    * @author Fantomas
    *
    */
     
    public class PhotoEdit{
    	
    	//Save Image
    static int licznik_save;
	static String digitss="";
    static Vector<Integer> digits_vector=new Vector<Integer>();
	static String nazwaa="";
	static String first_part="";
	static String second_part="";
	static int step_value;
   	static JFileChooser save = new JFileChooser();
   	static NumberFormat numberFormat = NumberFormat.getInstance();
   	static JTextField name = new JTextField();
   	static JTextField start_value = new JTextField();
   	static JTextField step = new JTextField();
   	static JTextField digits = new JTextField();
   	static JLabel name_label=new JLabel("Name");
   	static JFormattedTextField where = new JFormattedTextField(numberFormat);
   	static JLabel where_label=new JLabel("Appearance of counter");
   	static JLabel start_value_label=new JLabel("Start value");
   	static JLabel step_label=new JLabel("Step");
   	static JLabel digits_label=new JLabel("Digits");
    static JButton Save1_save = new JButton("Save");
    static JLabel Nazwa  = new JLabel();
    static JButton Option_1_set = new JButton("Set");
//end of Save Image
    	static File dir=new java.io.File(".");//variable handling current directory
    	static int deg=180;
    	static Vector<String> pathToImage=new Vector<String>();
            JDesktopPane center= new JDesktopPane();
            /*{
                ImageIcon backImage = new ImageIcon("PixelPro.jpg"); //logo
                Image image = backImage.getImage();
                {setOpaque(false);}
                //Override
                public void paintComponent (Graphics g) {
                	g.drawImage(image, 0, 0, 300, 300, null);
                  super.paintComponent(g);
                }
              };*/
            int flag=0;
            private JFrame frame;
            int licznik=0;
            static int n_of_stripes=0;
            static String horizontal_or_vertical=null;
            int directory_counter=0;
            static HashMap<JButton,File> all_chosen_images = new HashMap<JButton,File>(); //storing path to all images
            static int which_merge_mode_was_chose =0;
            static BufferedImage merged_image=null;
            ArrayList<JButton> buttonList = new ArrayList<JButton>();   
            static int black_or_white = 0;// list where all loaded images are as buttons
            static int different_sizes = 0; // store user choice about option for merge when images are different sizes
            static int different_sizes1 = 0; // same what above but for obligatory function 3 
            static boolean check = true;
            static boolean black = false;
            static ImageIcon logo = new ImageIcon("Logo/PixelPro.jpg");
            static ImageIcon logoback = new ImageIcon("Logo/PixelPro-back.jpg");
            static Image backgroundImage = logoback.getImage();
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
            	/*try {
                    // Set System L&F
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } 
            catch (UnsupportedLookAndFeelException e) {
               // handle exception
            }
            catch (ClassNotFoundException e) {
               // handle exception
            }
            catch (InstantiationException e) {
               // handle exception
            }
            catch (IllegalAccessException e) {
               // handle exception
            }*/
            	
                    frame = new JFrame("Pixel Pro");
                    frame.setBounds(200, 200, 1300, 800);
            
                    frame.setIconImage(logo.getImage());                   
                   
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
                   
                            frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.X_AXIS));                       JPanel panel = new JPanel();
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
                   
                    JMenu mnHelp = new JMenu("Help");
                                       menuBar.add(mnHelp);
                                       
                                        JMenuItem mntmAbout = new JMenuItem("About");
                                        
                                        mnHelp.add(mntmAbout);
                                        mntmAbout.addActionListener(new ActionListener()
                                        {
                                        	 public void actionPerformed(ActionEvent e) {
                                        		 JOptionPane.showMessageDialog(panel, "This photo application was made by: \n\nMagdalena Baracz\nJakub Mank\nNatalia Kazimierczak\nMaciej Miskiewicz\nAleksander Lipka \n\nVersion 1.5\n\nCopyright "+"\u00A9" +" 2015", "About", JOptionPane.INFORMATION_MESSAGE);
                                        	 }
                                        });
                    
                    JMenuItem mntmOpen = new JMenuItem("Open");
                     JFileChooser chooser=new JFileChooser(dir);
                     chooser.addChoosableFileFilter(new FileFilter() {
             			public boolean accept(File file) {
             				return file.isDirectory();
             			}

							@Override
							public String getDescription() {
								
								return "Directories only";
							}
             		});
                     chooser.addChoosableFileFilter(new FileFilter() {
                         public boolean accept(File f) {
                             return f.getName().toLowerCase().endsWith(".zip")
                                 || f.isDirectory();
                           }

                           public String getDescription() {
                             return "ZIP Files";
                           }
                         });
                     
                    mntmOpen.addActionListener(new ActionListener()
                    {
                                          
                                            String chooser_title;
                                            File file_from_given_directory[];
                                           
                                           
                                    public void actionPerformed(ActionEvent e) {
                                           
                                           
                                        FileFilter imageFilter = new FileNameExtensionFilter(
                                                "Image files", ImageIO.getReaderFileSuffixes());
                               
                                       
                                        //chooser.setCurrentDirectory(new java.io.File("."));
                                        chooser.setDialogTitle(chooser_title);
                                        chooser.setAcceptAllFileFilterUsed(false);

                                       
                                        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                                        //
                                        // disable the "All files" option.
                                        //
                                        
                                            
                                        if (chooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {                                
                                          System.out.println("getCurrentDirectory(): "                                      // delete it in final version
                                             +  chooser.getCurrentDirectory());
                                          System.out.println("getSelectedFile() : "
                                             +  chooser.getSelectedFile());
                                          
                                          if (chooser.getSelectedFile().getName().toLowerCase().endsWith("zip"))
                                          {
                                        	String zipFilePath = chooser.getSelectedFile().getAbsolutePath();
                                  	        String destDirectory = chooser.getSelectedFile().getAbsolutePath()+" ";
                                  	        UnzipUtility unzipper = new UnzipUtility();
                                  	        try {
                                  	            unzipper.unzip(zipFilePath, destDirectory);
                                  	            File f = new File(destDirectory);
                                  	            file_from_given_directory=f.listFiles(new ImageFileFilter());
                                  	        } catch (Exception ex) {
                                  	            // some errors occurred
                                  	            ex.printStackTrace();
                                  	        }
                                          }
                                          else
                                          {file_from_given_directory=chooser.getSelectedFile().listFiles(new ImageFileFilter());}                             //save list of all files from chosen directory
                                         chooser.removeChoosableFileFilter(imageFilter);
                                         
                                          
                                          File dir = chooser.getSelectedFile().getParentFile(); // variable handling current directory
                                          chooser.setCurrentDirectory(dir); //setting directory to current one
                                          
                                          
                                          if(file_from_given_directory.length == 0)
                                          {
                                              JOptionPane.showMessageDialog(frame,
                                                                "The directory has not got any images.",
                                                                "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                              flag++;
                                          }
                                          else
                                          { flag=0;
                                              directory_counter++;
                                          licznik=directory_counter-AddDirectory.iter;
                                         }  // increase counter by one
     
                                         if(licznik<=5&&flag==0)
                                         {
                                          for(int i =0; i<file_from_given_directory.length; i++)
                                          { System.out.println(file_from_given_directory.length);
                                        	  
                                              Image image = null;
                                                                    try {
                                                                            image = ImageIO.read(file_from_given_directory[i]);
                                                                    } catch (IOException e1) {
                                                                            // TODO Auto-generated catch block
                                                                            e1.printStackTrace();
                                                                    }
                                                                            image =image.getScaledInstance( 40, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
                                                                            ImageIcon icon = new ImageIcon(image);
                                        	  			
                                        	  			String path=file_from_given_directory[i].getAbsolutePath();
                                                            JButton myButton = new JButton(icon);
                                                            myButton.setOpaque(false);
                                                            myButton.setContentAreaFilled(false);
                                                            myButton.setBorderPainted(false);
                                                            myButton.setPreferredSize(new Dimension(40, 40));               //size of the image icon
                                                           
                                                            all_chosen_images.put(myButton,file_from_given_directory[i]); // path of image -->the button
                                                            buttonList.add(myButton);
                                                            myButton.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent e) {
                                                                           
                                                            final JPopupMenu menu = new JPopupMenu("Menu");
                                                           
                                                           
                                                            ActionListener menuListener;
                                                            menuListener = new ActionListener()             // action listener for items on JPopMenu
                                                            {
                                                                        @Override
                                                                        public void actionPerformed(ActionEvent event) {
     
                                                                               if (event.getActionCommand()=="View")
                                                                               {
                                                                                       
                                                                                       ShowImage image = new ShowImage(frame, path, path);  // enlarge chosen image
                                                                                       
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
                                              first.AddDirect(left_panel_1, file_from_given_directory,left_panel_2,all_chosen_images,all_chosen_temp,btnClearAll2,buttonList,frame,delete1,clear,buttonListTemp);  
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
                                    {
                                    	SaveImage.main(merged_image,0);
                                                    
                                    merged_image=null;
                                    Display.imgglobal=null;}
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
                    mnFile.addSeparator();
                    mnFile.add(mntmExit);
                   
                    //JDesktopPane center= new JDesktopPane();
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
                    panel_2a.setMaximumSize(new Dimension(600, 120));
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
                   
                   //
                    JPanel radiopanel_first =new JPanel();
                    radiopanel_first.setLayout(new BoxLayout(radiopanel_first, BoxLayout.Y_AXIS));
                    
              
                    JRadioButton rdbtnEnlarge = new JRadioButton("Enlarge");
                    rdbtnEnlarge.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                    //
                            	different_sizes = 2;
                            }
                    });
                    rdbtnEnlarge.setBounds(78, 92, 47, 23);
                    radiopanel_first.add(rdbtnEnlarge);
                   
                    JRadioButton rdbtnShrink = new JRadioButton("Shrink");
                    rdbtnShrink.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                            	different_sizes = 3;
                            }
                    });
                   
                    rdbtnShrink.setBounds(78, 41, 60, 23);
                    radiopanel_first.add(rdbtnShrink);
                    
                    String nazwa="Cut to smaller";
                    JRadioButton rdbtnCut = new JRadioButton(nazwa);
                    rdbtnCut.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                   //
                            	different_sizes = 4;
                            }
                    });
                   
                    rdbtnCut.setBounds(78, 41, 60, 23);
                    radiopanel_first.add(rdbtnCut);

                    
                    ButtonGroup group_first = new ButtonGroup();
                    group_first.add(rdbtnEnlarge);
                    group_first.add(rdbtnShrink);
                    group_first.add(rdbtnCut);
                    
                    
                   //
                    JButton btnMerge = new JButton("Merge");
                    //btnMerge.setBackground(Color.DARK_GRAY);
                    //btnMerge.setForeground(Color.WHITE);
                    btnMerge.setBounds(28, 192, 89, 23);
                   
                    JPanel BasicMerge=new JPanel();
                    BasicMerge.add(radiopanel_first);
                    BasicMerge.add(radiopanel);
                    BasicMerge.add(btnMerge);
                   
                    panel_2a.add(BasicMerge);
                   
                   
                   
                    btnMerge.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
     
                                    if(AddDirectory.all_chosen.size()>=2)
                                    {
                                       if(which_merge_mode_was_chose!=0)
                                       {  
                                            OptionFrame.main(null); // ask if user wants to see?
                                            OptionFrame quest_see_the_result=new OptionFrame(); // get answer from OptionFrame class
                                            Display disp= new Display(); // invoke Display class
                                           
                                           
                                           
                               switch(which_merge_mode_was_chose)
                               {
                                       case 1:
                                                MergeImageAND mergeImage1 = new MergeImageAND(AddDirectory.all_chosen,different_sizes);
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
                                                            merged_image = null;                            // free variable keeping merged image
                                                            }
                                                }
                                                            break;
                                       case 2:
                                                            MergeImageXOR mergeImage = new MergeImageXOR(AddDirectory.all_chosen,different_sizes);
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
                                                            merged_image = null;                            // free variable keeping merged image
                                                    }
                                                }
                                             
                                                            break;
                                       case 3:
                                                            MergeImageOR mergeImage2 = new MergeImageOR(AddDirectory.all_chosen,different_sizes);
                                                           
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
                                                            merged_image = null;                            // free variable keeping merged image
                                                            }
                                                }
                                                            break;
                               }
                                           
                                            //LayeredImage mergeImage = new LayeredImage(all_chosen_images);
                                            //z ostatniego folderu
                                    }
                                       else
                                       {  Object[] options = {"OK"};
                                       int n = JOptionPane.showOptionDialog(frame,
                                               "Please choose AND, OR or XOR.","Mode not chosen.",
                                                  JOptionPane.PLAIN_MESSAGE,
                                                  JOptionPane.QUESTION_MESSAGE,
                                                  null,
                                                  options,
                                                  options[0]);}
                                    }
                                    else
                                    {
                                           
                                              Object[] options = {"OK"};
                                                int n = JOptionPane.showOptionDialog(frame,
                                                            "Please choose at least 2 images to merge.","Empty work list",
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
            panel_2b.setMaximumSize(new Dimension(600, 200));
            panel_2.add(panel_2b);
            
            JPanel radio_buttons_frames=new JPanel();
            radio_buttons_frames.setLayout(new BoxLayout(radio_buttons_frames, BoxLayout.Y_AXIS));
            JButton frame_button = new JButton("Cut frame"); 
            //frame_button.setBackground(Color.DARK_GRAY);
            //frame_button.setForeground(Color.WHITE);
            JRadioButton black_radiobutton = new JRadioButton("Black background");
            black_radiobutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                           //
                    	black_or_white = 1;
                        frame_button.setBackground(Color.BLACK);
                        frame_button.setForeground(Color.WHITE);
                    }
            });

           
            black_radiobutton.setBounds(78, 41, 60, 23);
            radio_buttons_frames.add(black_radiobutton);

            
            JRadioButton white_radiobutton = new JRadioButton("White background");
            white_radiobutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                           //
                    	black_or_white = 2;
                        frame_button.setBackground(Color.WHITE);
                        frame_button.setForeground(Color.BLACK);
                    }
            });

           
            white_radiobutton.setBounds(78, 41, 60, 23);
            radio_buttons_frames.add(white_radiobutton);
           
            ButtonGroup group_frame_buttons = new ButtonGroup();
            group_frame_buttons.add(black_radiobutton);
            group_frame_buttons.add(white_radiobutton);
            
            NumberFormat numberFormat = NumberFormat.getInstance();
            JFormattedTextField inSet = new JFormattedTextField(numberFormat);
            frame_button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                if(AddDirectory.all_chosen.size()>=1)
                     {
                	int treshold=0;
                	ObligatoryFunction1 fun = new ObligatoryFunction1(black_or_white,treshold); 
                	List<File> all_images_as_file  = fun.cutOut();
                	List<BufferedImage> all_images_as_buffered = fun.cutOut1();
                	OptionFrame.main(null); // ask if user wants to see?
                    OptionFrame quest_see_the_result=new OptionFrame(); // get answer from OptionFrame class
                    
                	Display disp = new Display();
                	if (quest_see_the_result.GetSelectedOption() == JOptionPane.YES_OPTION)
                	for(int i = 0; i<all_images_as_file.size();i++)
                	{
                		disp.createFrame(center, all_images_as_file.get(i), all_images_as_buffered.get(i));
                	}
                	all_images_as_file.clear();
                	all_images_as_buffered.clear();
                	
                }
                	 else
                	 {  Object[] options = {"OK"};
                     int n = JOptionPane.showOptionDialog(frame,
                             "Please choose at least 1 image to cut.","Empty work list",
                                JOptionPane.PLAIN_MESSAGE,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);}
                }
        });
           
            JPanel Frames_black_white=new JPanel();
            Frames_black_white.add(radio_buttons_frames);
            Frames_black_white.add(frame_button);

            panel_2b.add(Frames_black_white);
          
            JLabel Set  = new JLabel("Set threshold");
            JPanel GrayscalePanel   = new JPanel();
            GrayscalePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "Grayscale"));
            
           
            inSet.setColumns(3);
            inSet.setFocusLostBehavior(JFormattedTextField.PERSIST);
            inSet.setText("0");
            JButton SetButton = new JButton("Cut frame");
            GrayscalePanel.add(Set);
            GrayscalePanel.add(inSet);
            GrayscalePanel.add(SetButton);
            panel_2b.add(GrayscalePanel);
            
            //SetButton.setBackground(Color.DARK_GRAY);
            //SetButton.setForeground(Color.WHITE);
           
            SetButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                	
                	if(AddDirectory.all_chosen.size()>=1)
                	{
                	double treshold = 0;
                	try{
                     	treshold = Double.parseDouble(inSet.getText() );
                     	}
                     	 
                     	catch ( NumberFormatException e1 ) {
                     	JOptionPane.showMessageDialog(null, "The value must be a numeric value. " );
                     	}
                	ObligatoryFunction1 fun = new ObligatoryFunction1(black_or_white,treshold); 
                	List<File> all_images_as_file  = fun.cutOut();
                	List<BufferedImage> all_images_as_buffered = fun.cutOut1();
                	OptionFrame.main(null); // ask if user wants to see?
                    OptionFrame quest_see_the_result=new OptionFrame(); // get answer from OptionFrame class
                    
                	Display disp = new Display();
                	if (quest_see_the_result.GetSelectedOption() == JOptionPane.YES_OPTION)	
                	for(int i = 0; i<all_images_as_file.size();i++)
                	{
                		disp.createFrame(center, all_images_as_file.get(i), all_images_as_buffered.get(i));
                	}
                	all_images_as_file.clear();
                	all_images_as_buffered.clear();
                	//funkcja
                	
                	}
                	 else
                	 {  Object[] options = {"OK"};
                     int n = JOptionPane.showOptionDialog(frame,
                             "Please choose at least 1 image to cut.","Empty work list",
                                JOptionPane.PLAIN_MESSAGE,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);}
                }
            });
            
            JPanel panel_2c = new JPanel();
            panel_2c.setOpaque(true);
            panel_2c.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "Cutouts options"));
            panel_2c.setLayout(new BoxLayout(panel_2c, BoxLayout.Y_AXIS));
           //
            JPanel Function3=new JPanel();

            JPanel radiopanel_second =new JPanel();
            radiopanel_second.setLayout(new BoxLayout(radiopanel_second, BoxLayout.Y_AXIS));
            
      
            JRadioButton rdbtnEnlarge1 = new JRadioButton("Enlarge");
            rdbtnEnlarge1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	different_sizes1 = 1;
                    	
                            //
                    }
            });
            rdbtnEnlarge1.setBounds(78, 92, 47, 23);
            radiopanel_second.add(rdbtnEnlarge1);
           
            JRadioButton rdbtnShrink1 = new JRadioButton("Shrink");
            rdbtnShrink1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                    	different_sizes1 = 2;
                           //
                    }
            });
           
            rdbtnShrink1.setBounds(78, 41, 60, 23);
            radiopanel_second.add(rdbtnShrink1);
            
            JRadioButton rdbtnCut1 = new JRadioButton("Cut to smaller");
            rdbtnCut1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                    	different_sizes1 = 3;
                           //
                    }
            });
           
            rdbtnCut1.setBounds(78, 41, 60, 23);
            radiopanel_second.add(rdbtnCut1);

            
            ButtonGroup group_second = new ButtonGroup();
            group_second.add(rdbtnEnlarge1);
            group_second.add(rdbtnShrink1);
            group_second.add(rdbtnCut1);
            Function3.add(radiopanel_second);
            
           //
            
            
            //
            JPanel MergeButtons =new JPanel(); // Panel for buttons from merge options TUTAJ KUBA - ok,thx ;)
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
            group1.add(Merge_Button);
            group1.add(Merge_Button_1);
            //Function3.add(mergepanel);
           
            FlowLayout experimentLayout = new FlowLayout();
            JPanel Stripes =new JPanel(experimentLayout);
            //Stripes.setLayout(FlowLayout);
           
            JLabel Set1  = new JLabel("Number of stripes");
            JFormattedTextField inSet1 = new JFormattedTextField(numberFormat);
            inSet1.setColumns(3);
            inSet1.setFocusLostBehavior(JFormattedTextField.PERSIST);
            inSet1.setBounds(28, 158, 89, 23);
            Stripes.add(inSet1);
       
            
            
            JButton SetButton1 = new JButton("SET");
            //SetButton1.setBackground(Color.DARK_GRAY);
            //SetButton1.setForeground(Color.WHITE);

            SetButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                   
                    if(AddDirectory.all_chosen.size()>=2)
     {                    try{
     	n_of_stripes = Integer.parseInt(inSet1.getText() );
     	}
     	 
     	catch ( NumberFormatException e1 ) {
     	JOptionPane.showMessageDialog(null, "The value must be a numeric value bigger than 0. " );
     	}
                  System.out.println(different_sizes1 + "Mode w photoedit");
                    inSet1.setText("");
                    	if(horizontal_or_vertical!=null&&n_of_stripes!=0)
                    	{
                     // enlarge or shrink
                  //  AskUser.main(null);
                   // AskUser.returnAnswer();

                   
                    ObligatoryFunction3  fun = new ObligatoryFunction3(AddDirectory.all_chosen,n_of_stripes,horizontal_or_vertical,different_sizes1);
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
                    	else
                    	{Object[] options = {"OK"};
                        int n = JOptionPane.showOptionDialog(frame,
                                "Please choose horizontal or vertical and put number of stripes.","Empty work list",
                                   JOptionPane.PLAIN_MESSAGE,
                                   JOptionPane.QUESTION_MESSAGE,
                                   null,
                                   options,
                                   options[0]);}
      }          
                    else
                    { Object[] options = {"OK"};
                        int n = JOptionPane.showOptionDialog(frame,
                                    "Please choose at least 2 images to combine.","Empty work list",
                                       JOptionPane.PLAIN_MESSAGE,
                                       JOptionPane.QUESTION_MESSAGE,
                                       null,
                                       options,
                                       options[0]);}
            }
    });
           
            Stripes.add(Set1);
            Stripes.add(inSet1);
            Function3.add(mergepanel);
            MergeButtons_left.add(Function3);
            MergeButtons_left.add(Stripes);
           
     
            MergeButtons.add(MergeButtons_left);
     
            panel_2c.add(MergeButtons); //end of Panel for buttons from merge
            panel_2c.setMaximumSize(new Dimension(600, 100));
            panel_2.add(panel_2c);
            JPanel SetButtonPanel=new JPanel();
            SetButtonPanel.add(SetButton1);
            SetButtonPanel.setLayout(new GridLayout(1,0));
            SetButtonPanel.setMaximumSize(new Dimension(100, 100));
            panel_2c.add(SetButtonPanel);
     
           
           
           
            //Alek panel
            JPanel panel_2e = new JPanel();
            panel_2e.setOpaque(true);
            panel_2e.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.GRAY, Color.DARK_GRAY), "Rotate options"));
            panel_2e.setLayout(new BoxLayout(panel_2e,BoxLayout.Y_AXIS));
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 360, 180);

            slider.setMinorTickSpacing(10);
            slider.setMajorTickSpacing(90);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.setLabelTable(slider.createStandardLabels(45));
            
            JPanel radio_buttons_rotate=new JPanel();
            radio_buttons_rotate.setLayout(new BoxLayout(radio_buttons_rotate, BoxLayout.Y_AXIS));
            
            JCheckBox cut_frame = new JCheckBox("Cut edges");
            cut_frame.setSelected(true);
            cut_frame.addItemListener(new ItemListener()  {
            		public void itemStateChanged(ItemEvent e) {
            			if(e.getStateChange() == ItemEvent.SELECTED)
            			{check = true;
            				System.out.println("Cut-edge mode selected");}
            			else
            			{check = false;
            				System.out.println("Cut-edge mode deselected");}
            		}
            });
            cut_frame.setBounds(78, 41, 60, 23);
            radio_buttons_rotate.add(cut_frame);
            
            JRadioButton black_rdbutton = new JRadioButton("Black background");
            black_rdbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                           black = true;
                    }
            });

           
            black_rdbutton.setBounds(78, 41, 60, 23);
            radio_buttons_rotate.add(black_rdbutton);
           
            
            JRadioButton white_rdbutton = new JRadioButton("White background");
            white_rdbutton.setSelected(true);
            white_rdbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                          black = false;
                    }
            });

           
            white_rdbutton.setBounds(78, 41, 60, 23);
            radio_buttons_rotate.add(white_rdbutton);
           
            ButtonGroup group_frame_rotate = new ButtonGroup();
            group_frame_rotate.add(black_rdbutton);
            group_frame_rotate.add(white_rdbutton);
            
            panel_2e.add(radio_buttons_rotate);
            
            JLabel Rotate  = new JLabel();
            Rotate.setText(deg+"\u00b0");
            panel_2e.add(slider);
            JButton btnRotate = new JButton("Rotate");
            JPanel RotatePanel=new JPanel(); 
            RotatePanel.add(Rotate);
            RotatePanel.add(btnRotate);
            RotatePanel.setLayout(new FlowLayout());
            btnRotate.setBounds(28, 158, 89, 23);
            //btnRotate.setBackground(Color.DARK_GRAY);
            //btnRotate.setForeground(Color.WHITE);
            panel_2e.add(RotatePanel);
           
           
          
            
            panel_2.add(panel_2e);
            slider.addChangeListener(new ChangeListener() {

            	 public void stateChanged(ChangeEvent ce) {
                     deg=((JSlider) ce.getSource()).getValue(); 
                    
                     Rotate.setText(deg+"\u00b0");                 }
           
            });
            btnRotate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                	if(AddDirectory.all_chosen.isEmpty())
                	{Object[] options = {"OK"};
                    int n = JOptionPane.showOptionDialog(frame,
                            "Please choose at least 1 image to rotate.","Empty work list",
                               JOptionPane.PLAIN_MESSAGE,
                               JOptionPane.QUESTION_MESSAGE,
                               null,
                               options,
                               options[0]);}
                	else
                	{RotateFunction rot = new RotateFunction();
                    rot.main();
                    BufferedImage kk = null;
                            Display disp= new Display(); // invoke Display class
                           
                            for(File i : all_chosen_images.values()){
                                    try{
                                    kk = ImageIO.read(new File(i.getAbsolutePath()));
                                    disp.createFrame(center, i, kk); //create new frame with image 
                                    }
                                    catch (IOException es){
                                            es.printStackTrace();
                                    }
                    
                            }
                            }
                }
            });
            
            //koniec panelu Alka
            
             // koniec
           
            // Refresh button
            JPanel panel_2d = new JPanel();
            JButton btnRefresh = new JButton("Refresh");
            btnRefresh.setBounds(28, 158, 89, 23);
           // btnRefresh.setBackground(Color.WHITE);
           // btnRefresh.setForeground(Color.BLACK);
            panel_2d.add(btnRefresh);
            JButton btnClear = new JButton("Clear desktop");
           // btnClear.setBackground(Color.RED);
           // btnClear.setForeground(Color.WHITE);
            btnRefresh.setBounds(28, 158, 89, 23);
            panel_2d.add(btnClear);
           
            panel_2.add(panel_2d);
            
            btnClear.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	if(Display.where_global.getComponents().length>0)
                        {
                       
                                 String ObjButtons[] = {"Yes","No"};
                                 int PromptResult = JOptionPane.showOptionDialog(null,"You have unsaved images. \n Do you want to clear?","Question",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                                  if(PromptResult==JOptionPane.YES_OPTION)
                                  {
                       //remove all components in panel.
                            center.removeAll();
                            // refresh the panel.
                            center.updateUI();
                                  }      
                        }
     
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