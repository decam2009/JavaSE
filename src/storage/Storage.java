package storage;

import model.Resume;

import java.util.List;

/**
 * Created by BORIS on 03.01.17.
 */
public interface Storage
  {
	void clear();
	void save(Resume r);
	Resume get(String uuid);
	void delete(String uuid);
	Resume[] getAll();
	int size();
	void update (Resume uuid, Resume newUuid);
	List<Resume> getAllResumes();
  }
