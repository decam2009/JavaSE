package storage;

import model.Resume;

/**
 * Created by BORIS on 03.01.17.
 */
public interface Storage
  {
	void clear();
	void save(Resume r);
	Resume get(String uuid);
	void delete(Resume uuid);
	Resume[] getAll();
	int size();
	void update (Resume uuid, Resume newUuid);
  }
