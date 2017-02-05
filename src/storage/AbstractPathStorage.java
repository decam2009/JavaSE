package storage;

import exception.StorageException;
import model.Resume;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by BORIS on 04.02.17.
 */
public abstract class AbstractPathStorage extends AbstractStorage<Path>
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

  protected abstract void doWrite(Resume r, Path file) throws IOException;
  protected abstract Resume doRead(Path file) throws IOException;

  @Override
  protected Path getSearchKey(String uuid)
    {
	  return directory.resolve(uuid);
    }

  @Override
  protected void doSave(Resume r, Path searchKey)
    {
	  try
	    {
		  Path fileName = Paths.get(searchKey.toString());
	      Files.createFile(fileName);
	      doWrite(r, searchKey);
	    }
	    catch (IOException e)
		{
		 throw new StorageException("File already exists", null);
	  }
	}

  @Override
  protected void doUpdate(Resume oldR, Path searchKey)
    {
	  try
	    {
		  doWrite(oldR, searchKey);
	    }
	  catch (IOException e)
	    {
	      throw new StorageException("", null);
	    }
	}

  @Override
  protected Resume doGet(Path searchKey)
    {
	  try
	    {
		  return doRead(searchKey);
	    }
	  catch (IOException e)
	    {
		  throw new StorageException("File can not be empty", null);
	    }
	}

  @Override
  protected List<Resume> doGetAllSorted()
    {
	  return null;
    }

  @Override
  protected boolean isExist(Path searchKey)
    {
	  return Files.exists(searchKey);
    }

  @Override
  protected void doDelete(Path searchKey)
    {
	  try
	    {
		  Files.delete(searchKey);
	    }
	  catch (IOException e)
	    {
		  throw new StorageException("File must exist", null);
	    }
	}

  @Override
  public int size()
    {
	  try
	    {
		 List<File> files =  Files.walk(directory).filter(Files::isRegularFile).map(Path::toFile).collect(Collectors.toList());
		 return files.size();
	    }
	  catch (IOException e)
		{
	  	  throw new StorageException("File mus exist", null);
	    }
	}

  @Override
  public void clear()
    {
	  try
	    {
		  Files.list(directory).forEach(this::doDelete);
	    }
	  catch (IOException e)
	    {
		  throw new StorageException("Path delete error", null);
	    }
    }

  @Override
  public Resume[] getAll() {
	Resume[] tmp = new Resume[this.size()];
	try
	  {
	    Files.list(directory).forEach(new Consumer<Path>()
		  {
		    @Override
		    public void accept(Path path)
			  {
				int i = 0;
				tmp[i] = doGet(path);
				i++;
		      }
		  });
	  }
	catch (IOException e)
	  {
	    throw new StorageException("", null);
	  }
	return tmp;
  }
}
