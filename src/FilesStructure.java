import java.io.File;

/**
 * Created by BORIS on 29.01.17.
 */
public class FilesStructure
  {

    private int level = 0;

    public void getAllFiles (String directoryPath)
	  {
		File file = new File(directoryPath);
		  for (File f: file.listFiles())
		    {
		      if (f.isDirectory() && !f.isHidden())
			    {
				  System.out.print("Folder level " + ++level + ": ");
				  System.out.println(f);
				  getAllFiles(f.getPath());
			    }
			  if (f.isFile() && !f.isHidden())
			  {
				System.out.println("File in Folder level " + level + ": " + f.getName());
			  }
			}
		  level --;
	  }

  }
