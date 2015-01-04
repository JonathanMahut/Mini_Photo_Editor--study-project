import java.io.File;
import java.io.FileFilter;

/*Include only those images which are here  */
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

