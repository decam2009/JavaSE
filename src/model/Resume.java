package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * Created by BORIS on 19.01.17.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable
  {
	public Resume()
	  {
	  }

	private static final long serialVersionUID = 1L;

	// Unique identifier
	private String uuid;
	private String fullName;
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

	public String getFullName()
	  {
	    return fullName;
	  }


	public void setContact(ContactType contactType, String value)
	  {
	    contacts.put(contactType, value);
	  }

	public void setSection(SectionType sectionType, Section section)
	  {
	    sections.put(sectionType, section);
	  }

	public Map <ContactType, String> getContacts ()
	{
  	  return contacts;
	}

	public String getContact(ContactType type)
	{
	  return contacts.get(type);
	}

	public Map <SectionType, Section> getSections ()
	{
	  return sections;
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
	  return Objects.equals(uuid, resume.uuid) &&
			  Objects.equals(fullName, resume.fullName) &&
			  Objects.equals(contacts, resume.contacts) &&
			  Objects.equals(sections, resume.sections);
	}

	@Override
	public int hashCode() {
	  return Objects.hash(uuid, fullName, contacts, sections);
	}

	public void setUuid(String uuid)
	{
	  this.uuid = uuid;
	}

	public void setFullName(String fullName)
	  {
	    this.fullName = fullName;
 	  }
  }
