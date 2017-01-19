package exception;

/**
 * Created by BORIS on 19.01.17.
 */
public class StorageException extends RuntimeException
  {
    String uuid;

	public String getUuid()
	  {
	    return uuid;
	  }

	public StorageException (String message, String uuid)
	  {
	     super(message);
	     this.uuid = uuid;
	  }
  }
