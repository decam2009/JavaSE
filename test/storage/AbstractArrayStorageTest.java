package storage;

import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Test;

/**
 * gkislin
 * 12.06.2016
 */
public abstract class AbstractArrayStorageTest extends AbstractStorageTest
  {

    public AbstractArrayStorageTest(Storage storage)
	{
	  super(storage);
	}

	@Test
	public void saveOverflow ()
	  {
		try
		  {
			for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT - 1; i++)
			  {
			    storage.save(new Resume("Boris Kaloshin"));
			  }
		  }
		catch (StorageException e)
		  {
			Assert.fail();
		  }
		storage.save(new Resume("Boris Kaloshin"));
	  }
  }