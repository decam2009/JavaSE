package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by BORIS on 13.01.17.
 */
public class ListStorage extends AbstractStorage
  {


	@Override
	public void clear()
	{
	  resumeList.removeAll(getAllResumes());
	}

	@Override
	public void save(Resume r)
	  {
  		resumeList.add(r);
	  }

	@Override
	public Resume get(String uuid)
	  {
	    return resumeList.get(getIndex(uuid));
	  }

	@Override
	public void delete(String uuid)
	  {
		resumeList.remove(getIndex(uuid));
	  }

	@Override
	public Resume[] getAll() {
	  return new Resume[0];
	}

	@Override
	public List<Resume> getAllResumes()
	  {
	    //TODO;
		return resumeList;
	  }

	@Override
	public int size()
	  {
	    return resumeList.size();
	  }

	@Override
	public void update(Resume uuid, Resume newUuid)
	  {
 		resumeList.set(getIndex(uuid.getUuid()), newUuid);
	  }
  }
