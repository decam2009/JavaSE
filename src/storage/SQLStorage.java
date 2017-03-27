package storage;

import exception.NotExistStorageException;
import model.ContactType;
import model.Resume;
import model.Section;
import model.SectionType;
import sql.SQLHepler;
import util.JsonParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BORIS on 19.02.17.
 */
public class SQLStorage implements Storage {

  public SQLHepler sqlHepler;

  private final List<Resume> resumeList = new ArrayList<>();

  public SQLStorage(String dbUrl, String dbUser, String dbPassword) throws SQLException
    {
	  try {
		Class.forName("org.postgresql.Driver");
	  }
	  catch (ClassNotFoundException e)
	  {
		throw new IllegalStateException(e);
	  }
	  sqlHepler = new SQLHepler(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));

    }

  @Override
  public void save(Resume r)
    {
	  sqlHepler.transactionalExecute(conn ->
	  {
	    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)"))
	  	  {
		    ps.setString(1, r.getUuid());
		    ps.setString(2, r.getFullName());
		    ps.execute();
		  }

		insertContacts(conn, r);
	    insertSections(conn, r);
		return null;
      });
    }

  @Override
  public Resume get(String uuid)
    {

      return sqlHepler.transactionalExecute(conn ->
	  {
	   Resume r;
	   try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume WHERE uuid=?"))
	     {
		   ps.setString(1, uuid);
		   ResultSet rs = ps.executeQuery();
		   if (!rs.next())
		     {
		       throw new NotExistStorageException(uuid);
		     }
		   r = new Resume(uuid, rs.getString("full_name"));
	     }

	     try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact WHERE resume_uuid=?"))
		 {
		  ps.setString(1, uuid);
		  ResultSet rs = ps.executeQuery();
		  while (rs.next())
		    {
		      addContact(rs, r);
			}
		 }

		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section WHERE uuid_id=?"))
		{
		  ps.setString(1, uuid);
		  ResultSet rs = ps.executeQuery();
		  while (rs.next())
		  {
			addSection(rs, r);
		  }
		}

		return r;
	  });
    }

  @Override
  public void update(Resume oldR, Resume newR)
    {
	  sqlHepler.transactionalExecute
	    (conn ->
	    {
	      try (PreparedStatement ps = conn.prepareStatement("UPDATE resume set full_name = ? WHERE uuid = ?"))
		    {
		      ps.setString(1, newR.getFullName());
			  ps.setString(2, oldR.getUuid());
			  ps.execute();
			  if (ps.executeUpdate() == 0)
			    {
				  throw new NotExistStorageException("Resume doesn't exist" + oldR.getUuid());
			    }
			}
 		    deleteContact (conn, oldR);
	        deleteSection (conn, oldR);
		    insertContacts(conn, newR);
		    insertSections(conn, newR);
			return null;
		});
    }

  @Override
  public void delete(String uuid)
    {
	  sqlHepler.execute("DELETE FROM resume WHERE uuid = ?", ps ->
	  {
	    ps.setString(1, uuid);
	    if (ps.executeUpdate() == 0)
		  {
		    throw new NotExistStorageException(uuid);
		  }
	    return  null;
	  });
    }

  @Override
  public List<Resume> getAllSorted()
    {
	  return sqlHepler.transactionalExecute(conn ->
	  {
		Map <String, Resume> resumes = new LinkedHashMap<>();
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r ORDER BY r.full_name, uuid"))
		{
		  ResultSet rs = ps.executeQuery();
		  while (rs.next())
		    {
		      String uuid = rs.getString("uuid");
		      resumes.put(uuid, new Resume(uuid, rs.getString ("full_name")));
			}

		}

		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact"))
		{
		  ResultSet rs = ps.executeQuery();
		  while (rs.next())
		  {
		    Resume r = resumes.get (rs.getString("resume_uuid"));
		    addContact(rs, r);
		  }
		}

		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section"))
		{
		  ResultSet rs = ps.executeQuery();
		  while (rs.next())
		  {
			Resume r = resumes.get (rs.getString("uuid_id"));
			addSection (rs, r);
		  }
		}

		return new ArrayList<>(resumes.values());
	  }
	  );
    }

  @Override
  public int size()
    {
      return sqlHepler.execute("SELECT count(*) from resume", ps ->
	    {
		    ResultSet rs = ps.executeQuery();
		    return rs.next()?rs.getInt(1): 0;
		});
	}

  @Override
  public void clear()
    {
 	  sqlHepler.execute("DELETE FROM resume");
    }

  @Override
  public Resume[] getAll()
    {
	  sqlHepler.execute("SELECT a.uuid, a.full_name, a.type, a.value FROM  (SELECT r.uuid, r.full_name, c.type, c.value FROM resume r, contact c WHERE r.uuid = c.resume_uuid) AS A UNION ALL (SELECT r.uuid, r.full_name, s.type, s.content FROM resume r, section s WHERE r.uuid = s.uuid_id)", ps ->
	    {
		  ResultSet rs = ps.executeQuery();
	      while (rs.next())
		  {
			Resume r = new Resume(rs.getString("uuid"), rs.getString("full_name"));
		    r.setUuid(r.getUuid().replace(" ", ""));
		    resumeList.add(r);
		  }
		  return resumeList;
		});
	  return  resumeList.toArray(new Resume[0]);
    }

  private void insertContacts(Connection conn, Resume newR) throws SQLException
  {
	try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (type, value, resume_uuid) VALUES (?, ?, ?)"))
	{
	  for (Map.Entry<ContactType, String> e : newR.getContacts().entrySet())
	  {
		ps.setString(1, e.getKey().name());
		ps.setString(2, e.getValue());
		ps.setString(3, newR.getUuid());
		ps.addBatch();
	  }
	  ps.executeBatch();
	}
  }

  private void insertSections(Connection conn, Resume r) throws SQLException
  {
	try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (uuid_id, type, content) VALUES (?, ?, ?)"))
	{
	  for (Map.Entry<SectionType, Section> e : r.getSections().entrySet())
	  {
		ps.setString(1, r.getUuid());
		ps.setString(2, e.getKey().name());
		Section section = e.getValue();
		ps.setString(3, JsonParser.write(section, Section.class));
		ps.addBatch();
	  }
	  ps.executeBatch();
	}
  }

  private void deleteContact (Connection conn, Resume oldR) throws SQLException
  {
	deleteAttributes(conn, oldR, "DELETE FROM contact WHERE resume_uuid = ?");
  }

  private void deleteSection(Connection conn, Resume oldR) throws SQLException
  {
    deleteAttributes(conn, oldR, "DELETE FROM section WHERE uuid_id = ?");
  }


  private void deleteAttributes(Connection conn, Resume oldR, String sql) throws SQLException
  {
	try (PreparedStatement ps = conn.prepareStatement(sql))
	{
	  ps.setString (1, oldR.getUuid());
	  ps.execute();
	}
  }


  private void addContact (ResultSet rs, Resume r) throws SQLException
  {
	String type = rs.getString("type");
    String value = rs.getString("value");
	if (value != null)
	  {
		r.setContact(ContactType.valueOf(type), value);
	  }
  }

  private void addSection(ResultSet rs, Resume r) throws SQLException
  {
    String content = rs.getString("content");
    if (content != null)
	  {
	    SectionType type = SectionType.valueOf(rs.getString("type"));
	    r.setSection(type, JsonParser.read(content, Section.class));
	  }
  }


}
