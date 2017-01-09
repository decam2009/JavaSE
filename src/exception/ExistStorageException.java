package exception;

/**
 * Created by BORIS on 09.01.17.
 */
public class ExistStorageException extends StorageException
  {

	public ExistStorageException(String uuid)
	  {
	    super("Resume " + uuid + " already exists", uuid);
	  }
  }
