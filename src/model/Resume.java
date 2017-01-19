package model;

import java.util.UUID;

/**
 * Created by BORIS on 19.01.17.
 */
public class Resume implements Comparable<Resume>
  {

	// Unique identifier
	private final String uuid;
	private String fullName;

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
