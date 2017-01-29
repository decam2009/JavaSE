package storage;

import exception.StorageException;
import model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by BORIS on 29.01.17.
 */
public abstract class AbstractFileStorage extends AbstractStorage<File>
  {
	private File directory;

    protected AbstractFileStorage(File directory)
	  {
		Objects.requireNonNull(directory, "Directory must not be empty");
		if (!directory.isDirectory())
		  throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
		if (!directory.canRead() || !directory.canWrite())
		  throw new IllegalArgumentException(directory.getAbsolutePath() + " no read/write privelege");
		this.directory = directory;
	  }

	protected abstract void doWrite(Resume r, File file) throws IOException;
	protected abstract Resume doRead(File file) throws IOException;

	@Override
	public int size()
	  {
	    return directory.listFiles().length;
	  }

	@Override
	public void clear()
	  {
		for (File f: directory.listFiles())
		  directory.delete();
	  }

	@Override
	public Resume[] getAll()
	  {
	    return new Resume[0];
	  }

	@Override
	protected File getSearchKey(String uuid)
	  {
	    return new File(directory, uuid);
	  }

	@Override
	protected void doSave(Resume r, File file)
	  {
		try
		  {
		    file.createNewFile();
		    doWrite(r, file);
		  }
		catch (IOException e)
		  {
		    throw new StorageException("IO error", file.getName(), e);
		  }
	  }

	@Override
	protected void doUpdate(Resume oldR, File file)
	  {
		try
		  {
		    doWrite(oldR, file);
		  }
		catch (IOException e)
		  {
		    throw new StorageException("IO error", file.getName(), e);
		  }
	  }

	@Override
	protected Resume doGet(File file)
	  {
		try
		  {
		    return doRead (file);
		  }
		catch (IOException e)
		  {
		    throw new StorageException("IO error", file.getName(), e);
		  }
	  }

	@Override
	protected List<Resume> doGetAllSorted()
	{
	  List<Resume> tmp = new ArrayList<>();

	  for (File f: directory.listFiles())
	    {
		  try
		    {
			  tmp.add(doRead(f));
		    }
		  catch (IOException e)
		    {
		   	  throw new StorageException("IO error", f.getName(), e);
		    }
		}
	  return tmp;
	}

	@Override
	protected boolean isExist(File file)
	  {
	    return file.exists();
	  }

	@Override
	protected void doDelete(File file)
	  {
   		for (File f: directory.listFiles())
		  {
		  	if (f.getName().equals(file.getName()))
			  {
			     f.delete();
			  }
		  }
	  }
  }
