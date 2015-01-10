import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;


public class AddDirectory {
	
	void AddDirect(JPanel where, JPanel directory, JFileChooser chooser, JPanel image){
		JTextField label1 = new JTextField(); 
		label1.setPreferredSize( new Dimension( 150, 20 ) );;
		JScrollBar sb1 = new JScrollBar(JScrollBar.HORIZONTAL);  
		label1.add(sb1);
		BoundedRangeModel brm = label1.getHorizontalVisibility();
		sb1.setModel(brm);
		
		JButton select1= new JButton("Select");
		JButton delete1= new JButton("Delete");
		
		image.setLayout(new FlowLayout());
		
		  where.add(directory);
		  label1.setText(chooser.getCurrentDirectory().getAbsolutePath());
		  directory.add(label1);
		  directory.add(sb1);
		  directory.add(image);
		  directory.add(select1);
		  directory.add(delete1);
		  
	}
}
