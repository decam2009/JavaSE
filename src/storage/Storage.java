package storage;

import model.Resume;

import java.util.List;

/**
 * Created by BORIS on 19.01.17.
 */
public interface Storage
  {
    void save(Resume r);
    Resume get (String uuid);
	void delete(String uuid);
	int size();
	void clear();
	void update (Resume oldR, Resume newR);
	List<Resume> getAllSorted();
	Resume [] getAll();
  }
