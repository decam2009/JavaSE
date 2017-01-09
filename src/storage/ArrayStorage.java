package storage;

import exception.ExistStorageException;
import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage
  {

    protected int getIndex (Resume uuid)
	  {
    	for (int i = 0; i <= storage.length - 1; i ++)
		  {
		    if (uuid.equals(storage[i]))
			  {
			    return i;
			  }
		  }
	    return -1;
	  }

	@Override
	public void clear()
	  {
	    super.clear();
	  }

	@Override
	public void save(Resume r)
	  {
	    int index = getIndex(r);
	    if (index > 0)
		  {
		    throw new ExistStorageException (r.getUuid());
		  }
		else
		  {
		    storage[size] = r;
		    size++;
		  }
	  }

	@Override
	public Resume get(Resume uuid)
	  {
	    return super.get(uuid);
	  }

	@Override
	public void delete(Resume uuid)
	{
	  int i = 0, j = 0, k;
	  while (storage[i] != null)
	    {
		  if (storage[i] == uuid)
		  {
			k = i;
			while (storage[j] != null)
			{
			  storage[k] = storage[k + 1];
			  j++;
			  k++;
			}
			break;
		  }
		  i++;
		}
	  size --;
	}

	@Override
	public Resume[] getAll()
	  {
	    return super.getAll();
	  }

	/**
     * @return array, contains only Resumes in storage (without null)
     */

	@Override
    public int size()
      {
        return size;
      }
    // HW2


	@Override
	public void update (Resume uuid, Resume newUuid)
	  {
	    int i = 0;
	    while (storage[i] != null)
		  {
		    if (storage[i] == uuid)
		      {
			    storage[i] = newUuid;
			    break;
		      }
		    i++;
		  }
	  }

  }
