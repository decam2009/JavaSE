package storage;

import model.Resume;

import java.util.*;

/**
 * Created by BORIS on 13.01.17.
 */
public abstract class AbstractStorage extends AbstractArrayStorage
  {

    protected List <Resume> resumeList = new ArrayList<Resume>();
    protected HashMap<String, Resume> resumeHashMap = new HashMap<String, Resume>();

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
	public abstract int getIndex(String uuid);

  }
