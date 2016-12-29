import com.sun.org.glassfish.external.statistics.RangeStatistic;

import java.util.ArrayList;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int index = 0;

    void clear()
      {
        int i = 0;
        while (storage [i] != null)
		  {
		    storage [i] = null;
		    i ++;
		  }
        index = 0;
      }

    void save(Resume r)
      {
        storage [index] = r;
        index ++;
      }

    Resume get(String uuid)
      {
        int i = 0;
        while (storage[i] != null)
          {
            if (storage[i].uuid == uuid)
              {
                return storage[i];
              }
            i ++;
          }
          return null;
      }

    void delete(String uuid)
      {
        int i = 0, j = 0, k;

        while (storage[i] != null)
          {
            if (storage[i].uuid == uuid)
              {
                k = i;
                while (storage [j] != null)
				  {
				    storage [k] = storage [k + 1];
				    j ++;
				    k ++;
				  }
				break;
         	  }
			i ++;
		  }
		index --;
	  }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll()
      {
		Resume tmp [] = new Resume [index];
		int i = 0;

		while (storage [i] != null)
		  {
		    tmp [i] = storage [i];
		    i ++;
		  }
	    return tmp;
      }

    int size()
      {
        return index;
      }
    // HW2

    boolean isNew ()
      {
        return true;
      }


    void update (Resume r)
      {
      }
}
