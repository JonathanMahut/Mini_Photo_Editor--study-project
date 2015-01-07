import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Display extends JFrame {
	
	private class MyInternalFrame extends JInternalFrame {
		 
        public MyInternalFrame() {
            super("MyInternalFrame",
                    true, //resizable
                    true, //closable
                    true, //maximizable
                    true);//iconifiable
 
            setSize(300, 200);
        }    
	}
	
	void createFrame(JDesktopPane where, File what) {
        MyInternalFrame frame = new MyInternalFrame();
        BufferedImage img = null;
		try {
			img = ImageIO.read(what);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	        ImageIcon icon=new ImageIcon(img);;
    	        JLabel lbl=new JLabel();
    	        lbl.setIcon(icon);
    	        frame.add(lbl);
        frame.setVisible(true);
        where.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
        frame.repaint(); 
		frame.validate();
    }
}
