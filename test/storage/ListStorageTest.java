package storage;

import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BORIS on 13.01.17.
 */
public class ListStorageTest
  {
    private ListStorage storage = new ListStorage();

	private static final String UUID_1 = "uuid1";
	private static final String UUID_2 = "uuid2";
	private static final String UUID_3 = "uuid3";
	private static final String UUID_4 = "uuid4";

	@Before
	public void setUp()
	  {
		storage.clear();
	    storage.save(new Resume(UUID_1));
		storage.save(new Resume(UUID_2));
		storage.save(new Resume(UUID_3));
	  }

	@Test
	public void get()
	  {
		Assert.assertEquals("uuid3", storage.get("uuid3").getUuid());
	  }

	@Test
	public void update()
	  {

	  }

	@Test
	public void save()
	  {
		storage.save(new Resume(UUID_4));
		Assert.assertEquals("uuid4", storage.get("uuid4").getUuid());
	  }

	@Test
	public void delete ()
	  {
	    storage.delete("uuid2");
		Assert.assertEquals(2, storage.size());
	  }

	@Test
	public void getAllResumes ()
	{
	    //TODO
	}

	@Test
	public void size()
	  {
		storage.size();
		Assert.assertEquals(3, storage.size());
	  }

  }
