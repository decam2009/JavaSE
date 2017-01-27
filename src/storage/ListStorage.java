package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by BORIS on 13.01.17.
 */
public class ListStorage extends AbstractStorage<Integer>
  {

	private List<Resume> list = new ArrayList<>();
	private static final Comparator<Resume> RESUME_COMPARATOR = ((o1, o2) -> o1.getUuid().compareTo(o2.getUuid()));

	@Override
	protected Integer getSearchKey(String uuid)
	  {
		for (int i = 0; i < list.size(); i++) {
		  if (list.get(i).getUuid().equals(uuid)) {
			return i;
		  }
		}
		return null;
	  }

	@Override
	protected void doSave(Resume r, Integer searchKey)
	  {
		list.add(r);
	  }

	@Override
	protected void doUpdate(Resume r, Integer searchKey)
	  {
	    list.set(searchKey, r);
	  }

	@Override
	protected Resume doGet(Integer searchKey)
	  {
		return list.get(searchKey);
	  }

	@Override
	protected List<Resume> doGetAllSorted()
	  {
	    list.sort(RESUME_COMPARATOR);
	    return list;
	  }

	@Override
	protected boolean isExist(Integer searchKey)
	  {
		return searchKey != null;
	  }

	@Override
	protected void doDelete(Integer searchKey)
	  {
		list.remove(searchKey.intValue());
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

