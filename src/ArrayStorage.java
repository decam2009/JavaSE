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
        for (int i = 0; i <= index; i ++)
          {
            storage[i] = null;
            index = 0;
          }
      }

    void save(Resume r)
      {
        storage [index] = r;
        index ++;
      }

    Resume get(String uuid)
      {
        Resume tmp = new Resume();
        for (int i = 0; i <= index - 1; i ++)
          {
            if (storage[i].uuid.equals(uuid))
              {
                tmp = storage[i];
              }
          }
          return tmp;
      }

    void delete(String uuid)
      {
        for (int i = 0; i <= index - 1; i++)
          {
            if (storage[i].uuid.equals(uuid))
              {
                storage[i].uuid = "-";
              }
          }
      }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll()
      {
        Resume tmp [] = new Resume[index];
        int indexTmp = 0;
        int j = 0;


        for (int i = 0; i <= index - 1; i++)
          {
            tmp [i] = storage [i];
          }

        for (int i = 0; i <= tmp.length - 1; i ++)
          {
            if (!tmp [i].uuid.equals("-"))
              {
                indexTmp ++;
              }
          }
          Resume [] output = new Resume[indexTmp];
          index = 0;
          for (int i = 0; i <= tmp.length - 1; i ++)
            {
              if (!tmp[i].uuid.equals("-"))
                {
                  output[j++] = tmp [i];
                  index ++;
                }
            }
          return output;
      }

    int size()
      {
        return index;
      }
}
