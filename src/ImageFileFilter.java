import java.io.File;
import java.io.FileFilter;

/**
 *The  filter for JFileChooser to show only files with the format written below.
 * @author Fantomas
 *
 */
	public class ImageFileFilter implements FileFilter
	{
	  private final String[] okFileExtensions =							
	    new String[] {"jpg", "png", "jpeg","tiff","tif","bmp"};			// remember to add later compressed images
	 
	  public boolean accept(File file)
	  {
	    for (String extension : okFileExtensions)
	    {
	      if (file.getName().toLowerCase().endsWith(extension))
	      {
	        return true;
	      }
	    }
	    return false;
	  }
	}

