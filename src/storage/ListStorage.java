package storage;

import model.Resume;

import java.util.List;

/**
 * Created by BORIS on 13.01.17.
 */
public class ListStorage extends AbstractStorage
  {
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
	protected Object getSearchKey(String uuid)
	  {
	    return null;
	  }

	@Override
	protected void doSave(Resume r, Object searchKey)
	  {

	  }

	@Override
	protected void doUpdate(Resume rOld, Resume rNew, Object searchKey)
	  {

	  }

	@Override
	protected Resume doGet(Object searchKey)
	  {
	    return null;
	  }

	@Override
	protected List<Resume> doGetAllSorted()
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

