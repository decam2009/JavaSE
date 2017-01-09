package storage;

import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;


/**
 * Created by BORIS on 08.01.17.
 */
public class AbstractArrayStorageTest
  {

    private Storage storage = new ArrayStorage();

    private static final String UUID1 = "uuid1";
	private static final String UUID2 = "uuid2";
	private static final String UUID3 = "uuid3";

    @Before
	public void setUp ()
	  {
	    storage.clear();
	    storage.save(new Resume(UUID1));
		storage.save(new Resume(UUID2));
		storage.save(new Resume(UUID3));
	  }

    @Test
    public void clear() throws Exception
      {
      }

    @Test
    public void getAll() throws Exception
	  {

      }

    @Test
    public void save() throws Exception
	  {

      }

    @Test
    public void size() throws Exception
	  {
        Assert.assertEquals(3, storage.size());
      }

    @Test
    public void get() throws Exception
	  {

      }

  }