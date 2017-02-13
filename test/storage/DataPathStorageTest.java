package storage;

import serializer.DataStreamSerializer;

/**
 * Created by BORIS on 05.02.17.
 */
public class DataPathStorageTest extends AbstractStorageTest
  {
	public DataPathStorageTest()
	{
	  super(new PathStorage(STORAGE_PATH, new DataStreamSerializer()));
	}
  }
