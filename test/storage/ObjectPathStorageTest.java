package storage;

import serializer.ObjectStreamSerializer;

/**
 * Created by BORIS on 05.02.17.
 */
public class ObjectPathStorageTest extends AbstractStorageTest
  {
	public ObjectPathStorageTest()
	{
	  super(new PathStorage(STORAGE_PATH, new ObjectStreamSerializer()));
	}
  }
