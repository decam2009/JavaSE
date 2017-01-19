package exception;

/**
 * Created by BORIS on 19.01.17.
 */
public class NotExistStorageException extends StorageException
  {
	public NotExistStorageException(String uuid)
	  {
	    super("Resume " + uuid + "doesn't exist", uuid);
	  }
  }
