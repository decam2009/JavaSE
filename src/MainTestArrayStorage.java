import model.Resume;
import storage.ArrayStorage;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

/**
 * Test for com.urise.webapp.storage.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
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

        ARRAY_STORAGE.save(r1);
	    ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r5);

       // ARRAY_STORAGE.update("uuid123", "uuid41");
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get("uuid1"));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
	    System.out.println("Index of r2: " + Arrays.binarySearch(ARRAY_STORAGE.storage, 0, ARRAY_STORAGE.size(), r5));
	    printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());
        //ARRAY_STORAGE.delete("uuid123");
        ARRAY_STORAGE.delete(r4.uuid);
        ARRAY_STORAGE.delete(r3.uuid);
        ARRAY_STORAGE.delete(r2.uuid);
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
