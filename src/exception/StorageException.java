package exception;

/**
 * Created by BORIS on 09.01.17.
 */
public class StorageException extends RuntimeException
  {
    private final String uuid;

	public StorageException(String message, String uuid)
	  {
	    super(message);
	    this.uuid = uuid;
	  }

	public String getUuid()
	  {
	    return uuid;
	  }
  }
