package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage
  {

    protected int getIndex (String uuid)
	  {
    	for (int i = 0; i <= storage.length - 1; i ++)
		  {
		    if (uuid.equals(storage[i].getUuid()))
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
	    super.save(r);
	  }

	@Override
	public Resume get(String uuid)
	  {
	    return super.get(uuid);
	  }

	@Override
	public void delete(String uuid)
	{
	  int i = 0, j = 0, k;
	  if (!isNew(uuid))
	  {
		while (storage[i] != null)
		{
		  if (storage[i].uuid == uuid)
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

    protected boolean isNew (String uuid)
      {
		int i = 0;
        while (storage [i] != null)
		  {
		    if (storage [i].uuid == uuid)
			  {
				return false;
			  }
			i ++;
		  }
		System.out.println("ERROR: model.Resume " + uuid + " doesn't exist");
		return true;
      }


	@Override
	public void update (String uuid, String newUuid)
	{
	  int i = 0;
	  if (!isNew(uuid))
	  {
		while (storage[i] != null)
		{
		  if (storage[i].uuid == uuid)
		  {
			storage[i].uuid = newUuid;
			break;
		  }
		  i++;
		}
	  }
	}

  }
