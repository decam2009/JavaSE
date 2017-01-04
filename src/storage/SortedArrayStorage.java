package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Created by BORIS on 03.01.17.
 */
public class SortedArrayStorage extends AbstractArrayStorage
{
  @Override
  protected boolean isNew(String uuid)
    {
	  return false;
    }

  @Override
  protected int getIndex(String uuid)
    {
	  Resume searchKey = new Resume();
	  searchKey.setUuid(uuid);
	  return Arrays.binarySearch(storage, 0, size, searchKey);
    }

  @Override
  public void delete(String uuid)
    {
      int index = getIndex(uuid);
      Resume tmp1 [] =  Arrays.copyOfRange(storage, 0, index);
      Resume tmp2 [] = Arrays.copyOfRange(storage, index + 1, size);
      for (int i = 0; i <= tmp1.length - 1; i ++)
	    {
	      storage [i] = tmp1 [i];
		}

	  for (int i = 0; i <= tmp2.length - 1; i ++)
	    {
		  storage [tmp1.length + i] = tmp2 [i];
	    }
	  size --;

    }

  @Override
  public void update(String uuid, String newUuid)
    {

    }

  public SortedArrayStorage()
    {
      Arrays.sort(Arrays.copyOfRange(storage,0,size));
    }
}
