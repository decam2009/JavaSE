package model;

/**
 * com.urise.webapp.model.model.Resume class
 */
public class Resume implements Comparable <Resume> {

    // Unique identifier
    public String uuid;

    public String getUuid()
      {
	    return uuid;
      }

    public void setUuid(String uuid)
	  {
	    this.uuid = uuid;
      }

  @Override
    public String toString() {
        return uuid;
    }

  @Override
  public int compareTo(Resume o)
    {
	  return uuid.compareTo(o.uuid);
    }
}
