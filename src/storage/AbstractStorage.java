package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.List;

/**
 * Created by BORIS on 19.01.17.
 */
public abstract class AbstractStorage implements Storage
  {

	protected abstract Object getSearchKey(String uuid);

    protected abstract void doSave(Resume r, Object searchKey);

	protected abstract void doUpdate(Resume rOld, Resume rNew, Object searchKey);

	protected abstract Resume doGet(Object searchKey);

	protected abstract List<Resume> doGetAllSorted();

	protected abstract boolean isExist(Object searchKey);

	protected abstract void doDelete(Object searchKey);

	@Override
	public void save(Resume r)
	{
	  Object searchKey = getNotExistedSearchKey(r.getUuid());
	  doSave (r, searchKey);
	}

	@Override
	public Resume get(String uuid)
	{
	  Object searchKey = getExistedSearchKey(uuid);
	  return doGet(searchKey);
	}

	@Override
	public void delete(String uuid)
	  {
		Object searchKey = getExistedSearchKey(uuid);
		doDelete (searchKey);
	  }

	@Override
	public void update(Resume oldR, Resume newR)
	  {
		Object searchKey = getExistedSearchKey(oldR.getUuid());
		doUpdate (oldR, newR, searchKey);
	  }

	@Override
	public List<Resume> getAllSorted()
	  {
	    return doGetAllSorted();
	  }

	private Object getExistedSearchKey(String uuid)
	  {
		Object searchKey = getSearchKey (uuid);
	    if (!isExist (searchKey))
		  {
		    throw new NotExistStorageException(uuid);
		  }
	    return searchKey;
	  }

	private Object getNotExistedSearchKey(String uuid)
	{
	  Object searchKey = getSearchKey (uuid);
	  if (isExist (searchKey))
	    {
		  throw new ExistStorageException(uuid);
	    }
	  return searchKey;
	}
  }
