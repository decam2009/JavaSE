package serializer;

import model.*;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by BORIS on 11.02.17.
 */
public class DataStreamSerializer implements StreamSerializer
  {
	@Override
	public void doWrite(Resume r, OutputStream os) throws IOException
	  {

	    try (DataOutputStream dos = new DataOutputStream(os))
		  {
			dos.writeUTF(r.getUuid());
			dos.writeUTF(r.getFullName());
			Map<ContactType, String> contacts = r.getContacts();
			Map<SectionType, Section> sections = r.getSections();
			Section a;
			dos.writeInt(contacts.size());
			dos.writeInt(sections.size());
			for (Map.Entry<ContactType, String> entry: r.getContacts().entrySet())
			  {
			    dos.writeUTF(entry.getKey().name());
			    dos.writeUTF(entry.getValue());
			  }
			// TODO implements Section
			for (Map.Entry<SectionType, Section> sectionEntry: r.getSections().entrySet())
			  {
			    dos.writeUTF(sectionEntry.getKey().name());
			    a = sectionEntry.getValue();
			    if (a.getClass().getSimpleName().equals("TextSection"))
			      {
				    dos.writeUTF(((TextSection) a).getContent());
				  }
				else if (a.getClass().getSimpleName().equals("ListSection"))
				  {
					dos.writeUTF(((ListSection) a).getItems().toString());
				  }
				else if (a.getClass().getSimpleName().equals("OrganizationSection"))
				  {
					List<Organization> organizations = ((OrganizationSection) a).getOrganizations();
					for (Organization o: organizations)
					  {
					    dos.writeUTF(o.toString());
					    dos.writeUTF(o.getPositions().toString());
					  }
				  }
			  }
		  }
	  }

	@Override
	public Resume doRead(InputStream is) throws IOException
	  {
	    try (DataInputStream dis = new DataInputStream(is))
		  {
			String textSection;
		    String uuid = dis.readUTF();
			String fullName = dis.readUTF();
			Resume resume = new Resume(uuid, fullName);
			int size = dis.readInt();
			for (int i = 0; i < size; i++)
			  {
				//resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
			  }
			int sectionSize = dis.readInt();

			for (int i = 0; i < sectionSize; i++)
			  {
				textSection = dis.readUTF();
			    //textSection.setContent(dis.readUTF());
			    //resume.addSection(SectionType.valueOf(dis.readUTF()), );
			  }
			return resume;
		  }
		// TODO implements Section
	  }
  }
