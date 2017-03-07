package storage;

import exception.NotExistStorageException;
import model.ContactType;
import model.Resume;
import sql.SQLHepler;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
		insertContact(conn, r);
	    return null;
      });
    }

  @Override
  public Resume get(String uuid) {
	return sqlHepler.execute("SELECT r.uuid, r.full_name, c.type, c.value FROM resume r , contact c " +
								  "WHERE r.uuid = c.resume_uuid AND r.uuid = ?",
	ps ->
	{
	  ps.setString(1, uuid);
	  ResultSet rs = ps.executeQuery();
	  if (!rs.next()) {
		throw new NotExistStorageException(uuid);
	  }
	  Resume r = new Resume(uuid, rs.getString("full_name"));
	  do
	    {
		  addContact(rs, r);
		} while (rs.next());
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
		    insertContact (conn, newR);
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
	  sqlHepler.execute("SELECT * FROM resume r, contact c WHERE  r.uuid = c.resume_uuid ORDER BY r.full_name, r.uuid", ps ->
	    {
	      ResultSet rs = ps.executeQuery();
	      Map <String, Resume> map = new HashMap<>();
	      while (rs.next())
		    {
		      String uuid = rs.getString("uuid");
		      Resume resume = map.get(uuid);
		      if (resume == null)
			    {
			      resume = new Resume(uuid, rs.getString("full_name"));
			      map.put(uuid, resume);
				}
			  addContact (rs, resume);
			}
		  return new ArrayList<> (map.values());
	    });
	  return null;
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
	  sqlHepler.execute("SELECT * FROM resume", ps ->
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
	  return resumeList.toArray(new Resume[0]);
    }

  private void insertContact (Connection conn, Resume newR) throws SQLException
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

  private void deleteContact (Connection conn, Resume oldR)
  {
	sqlHepler.execute("DELETE FROM contact WHERE resume_uuid = ?", ps -> {
	  ps.setString(1, oldR.getUuid());
	  ps.execute();
	  return null;
	});
  }

  private void addContact (ResultSet rs, Resume r) throws SQLException
  {
	String value = rs.getString("value");
	if (value != null)
	  {
		r.addContact(ContactType.valueOf(rs.getString("type")), rs.getString("value"));
	  }
  }


}
