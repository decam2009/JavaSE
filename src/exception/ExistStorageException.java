package exception;

/**
 * Created by BORIS on 19.01.17.
 */
public class ExistStorageException extends StorageException
  {
	public ExistStorageException(String uuid)
	  {
	    super("Resume " + uuid + " exists", uuid);
	  }
  }
