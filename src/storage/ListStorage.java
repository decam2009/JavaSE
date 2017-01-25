package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by BORIS on 13.01.17.
 */
public class ListStorage extends AbstractStorage
  {

	private List<Resume> list = new ArrayList<>();
	private static final Comparator<Resume> RESUME_COMPARATOR = ((o1, o2) -> o1.getUuid().compareTo(o2.getUuid()));

	@Override
	protected Object getSearchKey(String uuid)
	  {
		for (int i = 0; i < list.size(); i++) {
		  if (list.get(i).getUuid().equals(uuid)) {
			return i;
		  }
		}
		return null;
	  }

	@Override
	protected void doSave(Resume r, Object searchKey)
	  {
		list.add(r);
	  }

	@Override
	protected void doUpdate(Resume r, Object searchKey)
	  {
	    list.set((Integer)searchKey, r);
	  }

	@Override
	protected Resume doGet(Object searchKey)
	  {
		return list.get((Integer) searchKey);
	  }

	@Override
	protected List<Resume> doGetAllSorted()
	  {
	    list.sort(RESUME_COMPARATOR);
	    return list;
	  }

	@Override
	protected boolean isExist(Object searchKey)
	  {
		return searchKey != null;
	  }

	@Override
	protected void doDelete(Object searchKey)
	  {
		list.remove(((Integer) searchKey).intValue());
	  }

	@Override
	public int size()
	  {
		return list.size();
	  }

	@Override
	public void clear()
	  {
		list.clear();
	  }

	@Override
	public Resume[] getAll()
	{
	  Resume a [] = new Resume[0];
	  return list.toArray(a);
	}


  }

