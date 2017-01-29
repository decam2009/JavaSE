import org.junit.Test;

/**
 * Created by BORIS on 29.01.17.
 */
public class DirectoryStructureTest {


  DirectoryStructure ds = new DirectoryStructure();

  @Test
  public void getAllFiles()
    {
      ds.getAllFiles(".");
    }

}