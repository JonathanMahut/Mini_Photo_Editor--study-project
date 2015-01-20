import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class Display extends JFrame implements InternalFrameListener, ActionListener {
	static BufferedImage imgglobal=null;
	BufferedImage merge_image = null;	
	MyInternalFrame frame_global = new MyInternalFrame();
	static JDesktopPane where_global=new JDesktopPane();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private class MyInternalFrame extends JInternalFrame{
		 
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyInternalFrame() {
            super("MyInternalFrame",
                    true, //resizable
                    true, //closable
                    true, //maximizable
                    true);//iconifiable
 
            setSize(300, 200);
        }    
        
	}
	
	void createFrame(JDesktopPane where, File what, BufferedImage merged_image) {
        MyInternalFrame frame = new MyInternalFrame();
        where_global=where;
        frame.setTitle(what.getName());
        //JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //frame.setContentPane(pane);
        
        frame.addInternalFrameListener(this);
        JPanel image= new JPanel();
        JPanel container = new JPanel();
        container.add(image);
        JScrollPane jsp = new JScrollPane(container);
        frame.add(jsp);
        BufferedImage img = null;
        merge_image=merged_image;
		try {
			img = ImageIO.read(what);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	        ImageIcon icon=new ImageIcon(img);;
    	        JLabel lbl=new JLabel();
    	        lbl.setIcon(icon);
    	        image.add(lbl);
        frame.setVisible(true);
        where.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
        if (PhotoEdit.black == true){
        	container.setBackground(Color.BLACK);
        	image.setBackground(Color.BLACK);
        }
        else if (PhotoEdit.black == false){
        	container.setBackground(Color.WHITE);
        	image.setBackground(Color.WHITE);
        }
        image.repaint(); 
		image.validate();
		
    }

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		if(imgglobal!=null)
		{
		String ObjButtons[] = {"Yes","No"};
		 int PromptResult = JOptionPane.showOptionDialog(null,"Do you want to save image before exit?","Question",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
		  if(PromptResult==JOptionPane.YES_OPTION)
		  {
				frame_global.setVisible(false);
	    		SaveImage.main(merge_image,0);
	    		PhotoEdit.merged_image=null;
	    		where_global.remove(frame_global);
	    	
		  }
		  else
		  {		PhotoEdit.merged_image=null;
			  where_global.remove(frame_global);}
		}
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		
	}
		
	

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
			imgglobal = merge_image;	
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
