package storage;

import serializer.ObjectStreamSerializer;

/**
 * Created by BORIS on 03.02.17.
 */
public class ObjectFileStorageTest extends AbstractStorageTest
{
  public ObjectFileStorageTest()
  {
    super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
  }
}
