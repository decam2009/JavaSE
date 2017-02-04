package model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by BORIS on 19.01.17.
 */
public class Resume implements Comparable<Resume>, Serializable
  {
 	private static final long serialVersionUID = 1L;

	// Unique identifier
	private final String uuid;
	private final String fullName;
	private  final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
	private  final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

	public Resume(String fullName)
	{
	  this (UUID.randomUUID().toString(), fullName);
	}

	public Resume(String uuid , String fullName)
	{
	  Objects.requireNonNull(uuid, "Uuid can not be empty.");
	  Objects.requireNonNull(fullName, "Name can not be empty.");
	  this.uuid = uuid;
	  this.fullName = fullName;
	}

	public String getUuid()
	{
	  return uuid;
	}

	public void addContact (String mobile, String skype, String vk, String linkedin, String github)
	  {
	    contacts.put(ContactType.MOBILE, mobile);
		contacts.put(ContactType.SKYPE, skype);
		contacts.put(ContactType.VK, vk);
		contacts.put(ContactType.LINKEDIN, linkedin);
		contacts.put(ContactType.GITHUB, github);
	  }

	public void addSection (SectionType sectionType, Section section)
	  {
	    sections.put(sectionType, section);
	  }

	public String getContacts (ContactType type)
	{
  	  return contacts.get(type);
	}

	public Section getSection (SectionType type)
	{
	  return sections.get(type);
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

	@Override
	public boolean equals(Object o) {
	  if (this == o) return true;
	  if (o == null || getClass() != o.getClass()) return false;

	  Resume resume = (Resume) o;

	  if (!uuid.equals(resume.uuid)) return false;
	  return fullName.equals(resume.fullName);
	}

	@Override
	public int hashCode() {
	  int result = uuid.hashCode();
	  result = 31 * result + fullName.hashCode();
	  return result;
	}
  }
