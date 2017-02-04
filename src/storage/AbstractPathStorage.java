package storage;

import model.Resume;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * Created by BORIS on 04.02.17.
 */
public class AbstractPathStorage extends AbstractStorage<Path>
{
  protected Path directory;

  public AbstractPathStorage(String dir)
    {
	  this.directory = Paths.get(dir);
	  Objects.requireNonNull(directory, "Directory must not be null");
	  if (!Files.isDirectory(directory) || !Files.isWritable(directory))
	    {
		  throw new IllegalArgumentException(dir + " is not a directory or is not writable");
	    }
    }

  @Override
  protected Path getSearchKey(String uuid)
    {
	  return null;
    }

  @Override
  protected void doSave(Resume r, Path searchKey)
    {

    }

  @Override
  protected void doUpdate(Resume newR, Path searchKey)
    {

    }

  @Override
  protected Resume doGet(Path searchKey)
    {
	  return null;
    }

  @Override
  protected List<Resume> doGetAllSorted()
    {
	  return null;
    }

  @Override
  protected boolean isExist(Path searchKey)
    {
	  return false;
    }

  @Override
  protected void doDelete(Path searchKey)
    {

    }

  @Override
  public int size() {
	return 0;
  }

  @Override
  public void clear() {

  }

  @Override
  public Resume[] getAll() {
	return new Resume[0];
  }
}
