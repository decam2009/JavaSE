package storage;

/**
 * Created by BORIS on 05.02.17.
 */
public class ObjectPathStorageTest extends AbstractStorageTest
  {
	public ObjectPathStorageTest()
	{
	  super(new ObjectPathStorage(STORAGE_PATH));
	}
  }
