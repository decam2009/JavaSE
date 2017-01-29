package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by BORIS on 19.01.17.
 */
public abstract class AbstractStorage <SK> implements Storage
  {

	private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doSave(Resume r, SK searchKey);

	protected abstract void doUpdate(Resume newR, SK searchKey);

	protected abstract Resume doGet(SK searchKey);

	protected abstract List<Resume> doGetAllSorted();

	protected abstract boolean isExist(SK searchKey);

	protected abstract void doDelete(SK searchKey);

	@Override
	public void save(Resume r)
	{
	  LOG.info("Save " + r);
	  SK searchKey = getNotExistedSearchKey(r.getUuid());
	  doSave (r, searchKey);
	}

	@Override
	public Resume get(String uuid)
	{
	  LOG.info("Get " + uuid);
	  SK searchKey = getExistedSearchKey(uuid);
	  return doGet(searchKey);
	}

	@Override
	public void delete(String uuid)
	  {
		LOG.info("Deleted " + uuid);
	    SK searchKey = getExistedSearchKey(uuid);
		doDelete (searchKey);
	  }

	@Override
	public void update(Resume oldR, Resume newR)
	  {
		LOG.info("Updated " + oldR);
	    SK searchKey = getExistedSearchKey(oldR.getUuid());
		doUpdate (newR, searchKey);
	  }

	@Override
	public List<Resume> getAllSorted()
	  {
	    LOG.info("getAllSorted");
	    return doGetAllSorted();
	  }

	private SK getExistedSearchKey(String uuid)
	  {
		SK searchKey = getSearchKey (uuid);
	    if (!isExist (searchKey))
		  {
		    LOG.warning("Resume " + uuid + " doesn't exist");
		    throw new NotExistStorageException(uuid);
		  }
	    return searchKey;
	  }

	private SK getNotExistedSearchKey(String uuid)
	{
	  SK searchKey = getSearchKey (uuid);
	  if (isExist (searchKey))
	    {
		  LOG.warning("Resume " + uuid + "already exists");
	      throw new ExistStorageException(uuid);
	    }
	  return searchKey;
	}
  }
