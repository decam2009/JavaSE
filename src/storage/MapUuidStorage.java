package storage;

import model.Resume;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by BORIS on 19.01.17.
 */
public class MapUuidStorage extends AbstractStorage
  {

    HashMap<String, Resume> resumeHashMap = new HashMap<String, Resume>();

	@Override
	public int size()
	  {
	    return 0;
	  }

	@Override
	public void clear()
	  {

	  }

	@Override
	protected void doSave(Resume r, Object searchKey)
	  {

	  }

	@Override
	public List<Resume> getAllSorted()
	  {
	    return null;
	  }

	@Override
	protected void doUpdate(Resume r, Object searchKey)
	  {

	  }

	@Override
	protected Objects getSearchKey(String uuid)
	  {
	    return null;
	  }

	@Override
	protected Resume doGet(Object searchKey)
	  {
	    return null;
	  }

	@Override
	protected boolean isExist(Object searchKey)
	  {
	    return false;
	  }

	@Override
	protected void doDelete(Object searchKey)
	  {

	  }
  }
