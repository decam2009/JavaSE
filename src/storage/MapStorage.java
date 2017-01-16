package storage;

import model.Resume;

import java.util.List;

/**
 * Created by BORIS on 15.01.17.
 */
public class MapStorage extends AbstractStorage
  {
	@Override
	public int getIndex(String uuid)
	  {
	    return 0;
	  }

	@Override
	public void clear()
	  {
		resumeHashMap.clear();
	  }

	@Override
	public void save(Resume r)
	  {
		resumeHashMap.putIfAbsent (r.getUuid(), r);
	  }

	@Override
	public Resume get(String uuid)
	  {
	    return resumeHashMap.get(uuid);
	  }

	@Override
	public void delete(String uuid)
	  {
		resumeHashMap.remove(uuid);
	  }

	@Override
	public Resume[] getAll()
	  {
	    return new Resume[0];
	  }

	@Override
	public int size() {
	  return resumeHashMap.size();
	}

	@Override
	public void update(Resume uuid, Resume newUuid)
	  {
		resumeHashMap.replace(uuid.getUuid(), uuid, newUuid);
	  }

	@Override
	public List<Resume> getAllResumes()
	{
	  return (List <Resume>)resumeHashMap.values();
	}
  }
