package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Config;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest
{
    protected static final File STORAGE_DIR = Config.getInstance().getStorageDir();
    //new File ("./storage");
    protected static final String STORAGE_PATH = "./storage";
    protected Storage storage;

    protected AbstractStorageTest(Storage storage)
      {
	    this.storage = storage;
      }

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

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
	  RESUME_1.addContact(ContactType.MOBILE, "79000000000");
	  RESUME_1.addContact(ContactType.MAIL, "google@google.com");
	  RESUME_1.addContact(ContactType.HOME_PAGE, "https://google.com");
	  RESUME_1.addContact(ContactType.VK, "https://vk.com");
	  RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
	  RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
	  RESUME_1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievment1", "Achievmen2", "Achievment3"));
	  RESUME_1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
	/*  RESUME_1.addSection(SectionType.EXPERIENCE,
			  new OrganizationSection(new Organization("Gazprom", "http://google.com",
					  new Organization.Position(1996, Month.JANUARY, "Engeneer", "Programmer"),
					  new Organization.Position(2001, Month.JANUARY, 2018, Month.AUGUST, "Boss", "IT"))));
	  RESUME_1.addSection(SectionType.EDUCATION,
			  new OrganizationSection(new Organization("University", "",
					  new Organization.Position(1996, Month.JANUARY, "Student", "IT"))));*/
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
	  newResume.setUuid(UUID_1);
	  newResume.addContact(ContactType.MOBILE, "79000000000");
	  newResume.addContact(ContactType.MAIL, "google@mail.ru");
	  newResume.addContact(ContactType.HOME_PAGE, "https://mail.ru");
	  newResume.addContact(ContactType.VK, "https://fb.com");
	  storage.update(RESUME_1, newResume);
	  Assert.assertTrue(newResume.equals(storage.get(UUID_1)));
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
	  assertEquals(12, array.length);
	  //Проверка актуальна только когда uuid не генерятся автоматически
	//  assertEquals(RESUME_1, array[0]);
	//  assertEquals(RESUME_2, array[1]);
	//  assertEquals(RESUME_3, array[2]);
    }

    @Test
    public void save() throws Exception
	{
        storage.save(RESUME_4);
        assertSize(4);
      //  assertGet(RESUME_4);
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
      //  assertGet(RESUME_2);
      //  assertGet(RESUME_3);
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