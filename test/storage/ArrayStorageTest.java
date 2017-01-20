package storage;

import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by BORIS on 20.01.17.
 */
public class ArrayStorageTest

  {

    private static final String UUID_1 = "uuid1";
	private static final String UUID_2 = "uuid2";
	private static final String UUID_3 = "uuid3";

	private static final Resume RESUME_1 = new Resume(UUID_1);
	private static final Resume RESUME_2 = new Resume(UUID_2);
	private static final Resume RESUME_3 = new Resume(UUID_3);

    SortedArrayStorage storage = new SortedArrayStorage();

    @Before
	public void setUp()
	  {
		storage.clear();
		storage.save(RESUME_1);
		storage.save(RESUME_2);
		storage.save(RESUME_3);
	  }

	@Test
	public void save()
	  {
		storage.save(new Resume());
		Assert.assertEquals(4, storage.size());
	  }
  }
