import com.sun.org.glassfish.external.statistics.RangeStatistic;

import java.util.ArrayList;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int index = 0;
    private int notNull = 0;

    int getNotNullRecords ()
      {
        for (int i = 0; i <= this.storage.length - 1; i++)
          {
            if (this.storage [i] != null)
              {
                this.notNull ++;
              }
          }
        return this.notNull;
      }

    void clear()
      {
        storage = null;
        index = 0;
        notNull = 0;
      }

    void save(Resume r)
      {
        storage [index] = r;
        index ++;
      }

    Resume get(String uuid)
      {
        for (int i = 0; i <= notNull - 1; i ++)
          {
            if (storage [i].uuid == uuid)
              {
                return  storage [i];
              }
          }
        return null;
      }

    void delete(String uuid)
      {
        for (int i = 0; i <= notNull - 1; i ++)
          {
            if (storage[i].uuid == uuid)
              {
                storage [i].uuid = "-";
              }
          }
        index --;
      }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll()
      {
        Resume [] tmp = new Resume[notNull];
        int posTmp = 0;
        for (int i= 0; i <= notNull - 1; i ++)
          {
            if (storage [i].uuid == "-")
              {
                tmp [posTmp] = storage[i + 1];
                posTmp ++;
                i ++;
              }
            else
              {
                tmp [posTmp] = storage [i];
                posTmp ++;
              }
          }
        return tmp;
      }

    int size()
      {
        return index;
      }
}
