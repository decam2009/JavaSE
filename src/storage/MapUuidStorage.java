package storage;

import model.Resume;

import java.util.*;

/**
 * Created by BORIS on 19.01.17.
 */
public class MapUuidStorage extends AbstractStorage
  {

    private HashMap<String, Resume> storageHashMap = new HashMap<String, Resume>();
	private Map<String, Resume> sortedStorageHashMap = new TreeMap<String, Resume>();

	@Override
	public int size()
	  {
	    return storageHashMap.size();
	  }

	@Override
	public void clear()
	  {
		storageHashMap.clear();
	  }

	@Override
	protected void doSave(Resume r, Object searchKey)
	  {
		searchKey = getSearchKey(r.getUuid());
	    storageHashMap.put(searchKey.toString(), r);
	  }

	@Override
	protected List<Resume> doGetAllSorted()
	  {
	    sortedStorageHashMap.putAll(storageHashMap);
	    return new ArrayList<Resume>(sortedStorageHashMap.values());
	  }

	@Override
	protected void doUpdate(Resume r, Object searchKey)
	  {
	    storageHashMap.replace(searchKey.toString(), r);
	  }

	@Override
	protected String getSearchKey(String uuid)
	  {
	    return uuid;
	  }

	@Override
	protected Resume doGet(Object searchKey)
	  {
	    return storageHashMap.get(searchKey);
	  }

	@Override
	protected boolean isExist(Object searchKey)
	  {
	    if (storageHashMap.containsKey(searchKey))
		  {
			return true;
		  }
	    return false;
	  }

	@Override
	protected void doDelete(Object searchKey)
	  {
		storageHashMap.remove(searchKey);
	  }

	@Override
	public Resume[] getAll()
	  {
	    return new Resume[0];
	  }
  }
