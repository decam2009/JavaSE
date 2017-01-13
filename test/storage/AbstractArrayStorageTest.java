package storage;

import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by BORIS on 08.01.17.
 */
public class AbstractArrayStorageTest
  {

    public ArrayStorage storage;
    public SortedArrayStorage sortedStorage;

    protected static final String UUID1 = "uuid1";
	protected static final String UUID2 = "uuid2";
	protected static final String UUID3 = "uuid3";

	public AbstractArrayStorageTest()
	  {
		storage = new ArrayStorage();
		sortedStorage = new SortedArrayStorage();
	  }

	@Before
	public void setUp ()
	  {
	    storage.clear();
	    storage.save(new Resume(UUID1));
	    storage.save(new Resume(UUID2));
	    storage.save(new Resume(UUID3));
		sortedStorage.clear();
		sortedStorage.save(new Resume(UUID3));
		sortedStorage.save(new Resume(UUID2));
		sortedStorage.save(new Resume(UUID1));

	  }

	@Test
    public void clear()
      {
		storage.clear();
		Assert.assertEquals(0, storage.size());
      }

	@Test
	public void clearSorted ()
	  {
 		sortedStorage.clear();
 		Assert.assertEquals(0, sortedStorage.size());
	  }

	@Test
    public void getAll()
	  {
		int elemCount = 0;
		for (Resume r: storage.getAll())
		{
		  elemCount++;
		}
		Assert.assertEquals(elemCount, storage.size());
	  }

	@Test
	public void getAllSorted()
	{
	  int elemCount = 0;
	  for (Resume r: sortedStorage.getAll())
	  {
		elemCount++;
	  }
	  Assert.assertEquals(elemCount, sortedStorage.size());
	}

    @Test
    public void save()
	  {
		final String UUID_4 = "uuid4";
        storage.save(new Resume(UUID_4));
        Assert.assertEquals(4, storage.size());
	  }

	@Test
	public void saveSorted ()
	{
	  final String UUID_4 = "uuid4";
	  sortedStorage.save(new Resume(UUID_4));
	  Assert.assertEquals(4, sortedStorage.size());
	}

    @Test
    public void size()
	  {
        Assert.assertEquals(3, storage.size());
      }

	@Test
	public void sizeSorted ()
	  {
		Assert.assertEquals(3, sortedStorage.size());
	  }

	@Test
    public void get()
	  {
        Assert.assertEquals("uuid1", storage.get("uuid1").getUuid());
		Assert.assertEquals("uuid2", storage.get("uuid2").getUuid());
		Assert.assertEquals("uuid3", storage.get("uuid3").getUuid());
      }

	@Test
	public void getSorted ()
	{
	  Assert.assertEquals("uuid1", sortedStorage.get("uuid1").getUuid());
	  Assert.assertEquals("uuid2", sortedStorage.get("uuid2").getUuid());
	  Assert.assertEquals("uuid3", sortedStorage.get("uuid3").getUuid());
	}

	@Test
	public void delete()
	{
	  storage.delete("uuid1");
	  Assert.assertEquals(2,storage.size());
	}

	@Test
	public void deleteSorted()
	{
	  sortedStorage.delete("uuid1");
	  Assert.assertEquals(2,sortedStorage.size());
	}

  }