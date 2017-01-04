import model.Resume;
import storage.SortedArrayStorage;

/**
 * Created by BORIS on 04.01.17.
 */
public class MainTestSortedArrayStorage
  {
    static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args)
      {
	    Resume r1 = new Resume();
	    r1.uuid = "uuid1";
	    Resume r2 = new Resume();
	    r2.uuid = "uuid2";
	  	Resume r3 = new Resume();
	  	r3.uuid = "uuid3";
	  	Resume r4 = new Resume();
		r4.uuid = "uuid4";
		Resume r5 = new Resume();
		r5.uuid = "uuid5";

		SORTED_ARRAY_STORAGE.save(r1);
		SORTED_ARRAY_STORAGE.save(r4);
		SORTED_ARRAY_STORAGE.save(r2);
		SORTED_ARRAY_STORAGE.save(r3);
		SORTED_ARRAY_STORAGE.save(r5);
		SORTED_ARRAY_STORAGE.save(r2);
		SORTED_ARRAY_STORAGE.save(r5);
		SORTED_ARRAY_STORAGE.save(r1);
		printAll();

		SORTED_ARRAY_STORAGE.delete("uuid3");
		SORTED_ARRAY_STORAGE.delete("uuid5");
		SORTED_ARRAY_STORAGE.delete("uuid4");
		printAll();

		SORTED_ARRAY_STORAGE.update("uuid1", "uuid1_new");
		printAll();

		System.out.println("Get r2: " + SORTED_ARRAY_STORAGE.get("uuid2"));
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

