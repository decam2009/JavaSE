import model.Resume;
import storage.SortedArrayStorage;

/**
 * Created by BORIS on 04.01.17.
 */
public class MainTestSortedArrayStorage
  {
    private static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args)
      {
	    Resume r1 = new Resume("uuid1");
	    Resume r2 = new Resume("uuid2");
	  	Resume r3 = new Resume("uuid3");
	  	Resume r4 = new Resume("uuid4");
		Resume r5 = new Resume("uuid5");

		SORTED_ARRAY_STORAGE.save(r1);
		SORTED_ARRAY_STORAGE.save(r4);
		SORTED_ARRAY_STORAGE.save(r2);
		SORTED_ARRAY_STORAGE.save(r3);
		SORTED_ARRAY_STORAGE.save(r5);
		printAll();

		SORTED_ARRAY_STORAGE.delete(r4);
		printAll();

		SORTED_ARRAY_STORAGE.update(r5, new Resume("uuid1_new"));
		printAll();

		System.out.println("Get r2: " + SORTED_ARRAY_STORAGE.get(r2.getUuid()));
		System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());

		SORTED_ARRAY_STORAGE.clear();
		printAll();
		System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());

	  }

	private static void printAll()
	  {
		System.out.println("\nGet All");
		for (Resume r : SORTED_ARRAY_STORAGE.getAll())
		  {
			System.out.println(r);
		  }
	  }
 }

