package storage;

/**
 * Created by BORIS on 03.02.17.
 */
public class ObjectStreamStorageTest extends AbstractStorageTest
{
  public ObjectStreamStorageTest()
  {
    super(new ObjectStreamStorage(STORAGE_DIR));
  }
}
