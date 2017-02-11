package storage;

import serializer.XmlStreamSerializer;

/**
 * Created by BORIS on 05.02.17.
 */
public class XmlPathStorageTest extends AbstractStorageTest
  {
	public XmlPathStorageTest()
	{
	  super(new PathStorage(STORAGE_PATH, new XmlStreamSerializer()));
	}
  }
