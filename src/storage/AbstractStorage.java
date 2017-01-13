package storage;

import model.Resume;

import java.util.*;

/**
 * Created by BORIS on 13.01.17.
 */
public abstract class AbstractStorage extends AbstractArrayStorage
  {

    protected List <Resume> resumeList = new ArrayList<>();

	@Override
	public abstract void clear();

	@Override
	public abstract void save(Resume r);


	@Override
	public abstract Resume get(String uuid);


	@Override
	public abstract void delete(String uuid);

	@Override
	public abstract Resume[] getAll();

	@Override
	public abstract int size();


	@Override
	public abstract void update(Resume uuid, Resume newUuid);

	@Override
	protected int getIndex(String uuid)
	  {
	    Iterator<Resume> iterator = resumeList.iterator();
		while (iterator.hasNext())
		  {
		    Resume r = iterator.next();
		    if (r.getUuid().equals(uuid))
			  {
				return resumeList.indexOf(r);
			  }
		  }
		return 0;
	  }
  }
