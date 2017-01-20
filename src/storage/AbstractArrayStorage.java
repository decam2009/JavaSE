package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;

import java.util.Arrays;
import java.util.List;

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

	@Override
	public List<Resume> getAllSorted()
	  {
	    return Arrays.asList(storage);
	  }

	@Override
	public void delete(String uuid)
	  {
	    int index = getIndex(uuid);
	    if (index < 0)
		  {
		    throw new NotExistStorageException(uuid);
		  }
		else
		  {
		    Arrays.copyOfRange(storage, 0, index - 1);
		    Arrays.copyOfRange(storage, index + 1, size);
		    size--;
		  }
	  }

	@Override
	public void update(Resume oldR, Resume newR)
	  {
        int index = getIndex(oldR.getUuid());
		if (index < 0)
		  {
		    throw new NotExistStorageException(oldR.getUuid());
		  }
		else
		  {
			Arrays.fill(storage, index, index + 1, newR);
		  }
	  }

	public void save(Resume r)
	  {
		if (!overLimit())
		  {
		    int index = getIndex(r.getUuid());
		    if (index > 0)
			  {
			    throw new ExistStorageException(r.getUuid());
			  }
			else
			  {
			    Arrays.fill(storage, size, size + 1, r);
				size ++;
			  }
		  }
	  }

	public int size()
	  {
	    return size;
	  }

	public Resume get(String uuid)
	  {
	    int index = getIndex (uuid);
	    if (index == -1)
	      {
		    throw new NotExistStorageException(uuid);
		  }
		return storage [index];
	  }

	protected int getIndex(String uuid)
	  {
		Resume searchKey = new Resume(uuid);
	    return Arrays.binarySearch(storage, 0, size, searchKey);
	  }

	protected boolean overLimit ()
	  {
	    if (size == STORAGE_LIMIT)
		  {
			throw new StorageException("Storage overflow", "");
		  }
		return false;
	  }
  }
