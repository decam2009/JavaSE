package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Created by BORIS on 03.01.17.
 */

public abstract class AbstractArrayStorage extends AbstractStorage<Integer>
  {
    protected static final int STORAGE_LIMIT = 10000;
    public Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

	public int size()
	  {
	    return size;
	  }

	public void clear() {
	  Arrays.fill(storage, 0, size, null);
	  size = 0;
	}

	@Override
	protected void doUpdate(Resume r, Integer index) {
	  storage[index] = r;
	}

	/**
	 * @return array, contains only Resumes in storage (without null)
	 */
	public Resume[] getAll() {
	  Arrays.sort(storage, 0, size);
	  return Arrays.copyOfRange(storage, 0, size);
	}

	@Override
	protected void doSave(Resume r, Integer index) {
	  if (size == STORAGE_LIMIT) {
		throw new StorageException("Storage overflow", r.getUuid());
	  } else {
		insertElement(r, index);
		size++;
	  }
	}

	@Override
	public void doDelete(Integer index) {
	  fillDeletedElement(index);
	  storage[size - 1] = null;
	  size--;
	}

	public Resume doGet(Integer index) {
	  Arrays.sort(storage,0,size);
	  return storage[index];
	}

	@Override
	protected boolean isExist(Integer index) {
	  return index >= 0;
	}

	@Override
	protected List<Resume> doGetAllSorted()
	  {
	    return Arrays.asList(storage);
	  }

	protected abstract void fillDeletedElement(int index);

	protected abstract void insertElement(Resume r, int index);

	protected abstract Integer getSearchKey(String uuid);

  }
