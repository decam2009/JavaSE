package storage;

/**
 * Created by BORIS on 19.01.17.
 */
public class MapUuidStorageTest extends AbstractStorageTest
  {

	public MapUuidStorageTest()
	{
	  super(new MapUuidStorage());
	}

  /*MapUuidStorage storage = new MapUuidStorage();

  private static final String UUID_1 = "uuid1";
  private static final String UUID_2 = "uuid2";
  private static final String UUID_3 = "uuid3";

  private static final Resume RESUME_1 = new Resume(UUID_1);
  private static final Resume RESUME_2 = new Resume(UUID_2);
  private static final Resume RESUME_3 = new Resume(UUID_3);

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
  public void clear()
    {
  	  storage.clear();
      Assert.assertEquals(0, storage.size());
    }

  @Test
  public void get()
    {
   	  Assert.assertEquals("uuid2", storage.get("uuid2").getUuid());
    }

  @Test
  public void delete ()
    {
	  storage.delete("uuid3");
	  Assert.assertEquals(2, storage.size());
    }

  @Test
  public void update()
    {
		storage.update(RESUME_1, RESUME_3);
		Assert.assertEquals("uuid3", storage.get("uuid1").getUuid());
    }

  @Test
  public void save()
    {
	  storage.save(new Resume());
	  Assert.assertEquals(4, storage.size());
    }

  @Test
  public void getAllSorted()
    {
      storage.getAllSorted();
    }*/
}