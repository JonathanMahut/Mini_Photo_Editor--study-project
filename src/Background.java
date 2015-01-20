import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JDesktopPane;


public class Background extends JDesktopPane{
    protected void paintComponent (Graphics g){
    	try{
    		Graphics2D g2d = (Graphics2D)g;
    		double x = PhotoEdit.backgroundImage.getWidth(null);
    		double y = PhotoEdit.backgroundImage.getHeight(null);
    		g2d.scale(getWidth()/x, getHeight()/y);
    		g2d.drawImage(PhotoEdit.backgroundImage, 0, 0, this);
    		
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
