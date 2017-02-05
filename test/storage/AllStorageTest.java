package storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by BORIS on 23.01.17.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ArrayStorageTest.class,
		SortedArrayStorageTest.class,
		ListStorageTest.class,
		MapUuidStorageTest.class,
		ObjectStreamStorageTest.class,
		ObjectPathStorageTest.class
}
)

public class AllStorageTest
{
}
