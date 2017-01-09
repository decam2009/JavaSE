package model;

import java.util.UUID;

/**
 * com.urise.webapp.model.model.Resume class
 */
public class Resume implements Comparable <Resume>
  {

    // Unique identifier
    private final String uuid;

	public Resume()
	  {
		this (UUID.randomUUID().toString());
	  }

	public Resume(String uuid)
	  {

	    this.uuid = uuid;
	  }

	public String getUuid()
      {
	    return uuid;
      }

    @Override
    public String toString()
	  {
        return uuid;
      }

    @Override
    public int compareTo(Resume o)
      {
	    return uuid.compareTo(o.uuid);
      }
  }
