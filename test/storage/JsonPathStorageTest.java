package storage;

import serializer.JsonStreamSerializer;

/**
 * Created by BORIS on 05.02.17.
 */
public class JsonPathStorageTest extends AbstractStorageTest
  {
	public JsonPathStorageTest()
	{
	  super(new PathStorage(STORAGE_PATH, new JsonStreamSerializer()));
	}
  }
