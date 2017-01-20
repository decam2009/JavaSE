package storage;

import exception.NotExistStorageException;
import model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Created by BORIS on 03.01.17.
 */
public class SortedArrayStorage extends AbstractArrayStorage
{


  @Override
  public void save(Resume r)
    {
      Arrays.sort(storage, 0, size + 1);
	  size++;
    }

  @Override
  protected int getIndex(String uuid)
    {
	  Resume searchKey = new Resume(uuid);
	  return Arrays.binarySearch(storage, 0, size, searchKey);
    }

  @Override
  public List<Resume> getAllSorted()
    {
	  return super.getAllSorted();
    }

  @Override
  public void delete(String uuid)
    {
      int index = getIndex(uuid);
      if (index < 0)
	    {
		  throw new NotExistStorageException(uuid);
		}
	  else
	    {
		  Resume tmp1[] = Arrays.copyOfRange(storage, 0, index);
		  Resume tmp2[] = Arrays.copyOfRange(storage, index + 1, size);

		  for (int i = 0; i <= tmp1.length - 1; i++)
		    {
		      storage[i] = tmp1[i];
		    }

		  for (int i = 0; i <= tmp2.length - 1; i++)
		    {
		      storage[tmp1.length + i] = tmp2[i];
		    }
		  size--;
	    }

    }

  @Override
  public void update(Resume uuid, Resume newUuid)
    {
      int index = getIndex(uuid.getUuid());
	  if (index < 0)
	    {
		  throw new NotExistStorageException(uuid.getUuid());
	    }
      else
        {
		  storage[index] = newUuid;
	    }
    }

}
