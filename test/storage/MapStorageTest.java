package storage;

import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by BORIS on 16.01.17.
 */
public class MapStorageTest
{
  private MapStorage storage = new MapStorage();
  private static final String UUID_1 = "uuid1";
  private static final String UUID_2 = "uuid2";
  private static final String UUID_3 = "uuid3";
  private static final String UUID_4 = "uuid4";
  private static final Resume RESUME_1 = new Resume (UUID_1);
  private static final Resume RESUME_2 = new Resume (UUID_2);
  private static final Resume RESUME_3 = new Resume (UUID_3);

  @Before
  public void setUp ()
    {
      storage.clear();
      storage.save(RESUME_1);
	  storage.save(RESUME_2);
	  storage.save(RESUME_3);
    }

  @Test
  public void size()
    {
	  Assert.assertEquals(3, storage.size());
    }

  @Test
  public void save ()
    {
	  storage.save(new Resume(UUID_4));
	  Assert.assertEquals(4, storage.size());
    }

  @Test
  public void delete()
    {
		storage.delete(UUID_1);
		Assert.assertEquals(2,storage.size());
    }

  @Test
  public void get()
    {
		Assert.assertEquals("uuid1", storage.get(UUID_1).getUuid());
    }

  @Test
  public void update()
    {
		storage.update(RESUME_1, RESUME_3);
		Assert.assertEquals("uuid3", storage.get("uuid1").getUuid());
    }
}
