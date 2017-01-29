package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;
import model.SectionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * gkislin
 * 12.06.2016
 */
public abstract class AbstractStorageTest
{

    protected Storage storage;

    protected AbstractStorageTest(Storage storage)
      {
	    this.storage = storage;
      }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final String FULLNAME_1 = "AAAAA";
  	private static final String FULLNAME_2 = "BBBBB";
  	private static final String FULLNAME_3 = "CCCCC";
  	private static final String FULLNAME_4 = "DDDDD";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static
	{
	  RESUME_1 = new Resume(UUID_1, FULLNAME_1);
	  RESUME_2 = new Resume(UUID_2, FULLNAME_2);
	  RESUME_3 = new Resume(UUID_3, FULLNAME_3);
	  RESUME_4 = new Resume(UUID_4, FULLNAME_4);

	  RESUME_1.getSection(SectionType.PERSONAL);
	  RESUME_1.getSection(SectionType.OBJECTIVE);
	  RESUME_1.getSection(SectionType.ACHIEVEMENT);
	  RESUME_1.getSection(SectionType.QUALIFICATIONS);
	  RESUME_1.getSection(SectionType.EXPERIENCE);
	  RESUME_1.getSection(SectionType.EDUCATION);
    }

    @Before
    public void setUp() throws Exception
	{
	  storage.clear();
	  storage.save(RESUME_1);
	  storage.save(RESUME_2);
	  storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception
	{
	  assertSize(3);
    }

    @Test
    public void clear() throws Exception
	{
	  storage.clear();
	  assertSize(0);
    }

    @Test
    public void update() throws Exception
	{
	  Resume newResume = new Resume(UUID_4,FULLNAME_4);
	  storage.update(RESUME_2, newResume);
	  Assert.assertEquals("uuid4", UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception
	{
        storage.get("dummy");
    }

    @Test
    public void getAll() throws Exception
	{
	  Resume[] array = storage.getAll();
	  assertEquals(3, array.length);
	  assertEquals(RESUME_1, array[0]);
	  assertEquals(RESUME_2, array[1]);
	  assertEquals(RESUME_3, array[2]);
    }

    @Test
    public void save() throws Exception
	{
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}