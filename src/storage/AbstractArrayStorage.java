package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Created by BORIS on 03.01.17.
 */

public abstract class AbstractArrayStorage implements Storage
  {
    private static final int STORAGE_LIMIT = 10000;
    public Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

	public void clear()
	  {
		Arrays.fill(storage, 0, size, null);
		size = 0;
	  }

	protected abstract boolean isNew(String uuid);

	@Override
	public Resume[] getAll()
	  {
	    return Arrays.copyOfRange(storage, 0, size);
	  }

	abstract public void save(Resume r);

	public int size()
	  {
	    return size;
	  }

	public Resume get(String uuid)
	{
	  int index = getIndex (uuid);
	  if (index == -1)
	    {
		  System.out.println("Resume " + uuid + " doesn't exist");
		  return null;
		}
		return storage [index];
	}

	protected abstract int getIndex(String uuid);

	protected boolean overLimit ()
	  {
	    if (size > STORAGE_LIMIT)
		  {
			System.out.println("Your storage is full. Delete unusefull resumes.");
			return true;
		  }
		return false;
	  }
  }
