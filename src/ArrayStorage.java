import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    //Resume[] storage = new Resume[10000];

    List <Resume> storage = new ArrayList<>();

    void clear()
      {
        storage.clear();
      }

    void save(Resume r)
      {
        storage.add(r);
      }

    Resume get(String uuid)
      {
        Resume tmp = new Resume();
          for (Resume item : storage)
            {
              if (item.uuid.equals(uuid))
                {
                  tmp = item;
                }
            }

        return tmp;
      }

    void delete(String uuid)
      {
          Iterator<Resume> iter = storage.iterator();

          while (iter.hasNext())
            {
              Resume item = iter.next();
              if (item.uuid.equals(uuid))
                {
                  iter.remove();
                }
            }
      }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    List <Resume> getAll()
      {
        return storage.subList(0, storage.size());
      }

    int size()
      {
        return storage.size();
     }
}
