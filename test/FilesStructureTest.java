import org.junit.Test;

/**
 * Created by BORIS on 29.01.17.
 */
public class FilesStructureTest {


  FilesStructure ds = new FilesStructure();

  @Test
  public void getAllFiles()
    {
      ds.getAllFiles(".");
    }

}