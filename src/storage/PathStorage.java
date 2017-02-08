package storage;

import exception.StorageException;
import model.Resume;
import serializer.StreamSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
public class PathStorage extends AbstractStorage<Path>
{
  protected Path directory;
  private StreamSerializer streamSerializer;

  public PathStorage(String dir, StreamSerializer streamSerializer)
    {
	  this.streamSerializer = streamSerializer;
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
	  return directory.resolve(uuid);
    }

  @Override
  protected void doSave(Resume r, Path searchKey)
    {
	  try
	    {
		  Path fileName = Paths.get(searchKey.toString());
	      Files.createFile(fileName);
	      streamSerializer.doWrite(r, new BufferedOutputStream(Files.newOutputStream(searchKey)));
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
		  streamSerializer.doWrite(oldR, new BufferedOutputStream(Files.newOutputStream(searchKey)));
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
		  return streamSerializer.doRead(new BufferedInputStream(Files.newInputStream(searchKey)));
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
			int i = 0;
		    @Override
		    public void accept(Path path)
			  {
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
