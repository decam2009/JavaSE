package storage;

import exception.NotExistStorageException;
import model.Resume;
import sql.SQLHepler;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
  public void save(Resume r) {
	sqlHepler.<Void>execute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)", ps ->
	{
	  ps.setString(1, r.getUuid());
	  ps.setString(2, r.getFullName());
	  ps.execute();
	  return null;
	});
  }

  @Override
  public Resume get(String uuid) {
	return sqlHepler.execute("SELECT * FROM resume WHERE uuid = ?", ps ->
	{
	  ps.setString(1, uuid);
	  ResultSet rs = ps.executeQuery();
	  if (!rs.next()) {
		throw new NotExistStorageException(uuid);
	  }
	  Resume r = new Resume(uuid, rs.getString("full_name"));
	  return r;
	});
  }

  @Override
  public void update(Resume oldR, Resume newR)
    {
	  sqlHepler.<Void> execute("UPDATE resume set full_name = ? WHERE uuid = ?", ps ->
	  {
	    ps.setString(1, newR.getFullName());
	    ps.setString(2, oldR.getUuid());
	    if (ps.executeUpdate() == 0)
		 {
		   throw new NotExistStorageException("Resume doesn't exist" + oldR.getUuid());
		 }
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
	  sqlHepler.execute("SELECT * FROM resume r ORDER BY r.full_name, r.uuid", ps ->
	    {
	      ResultSet rs = ps.executeQuery();
	      while (rs.next())
		    {
		      resumeList.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
			}
		  return resumeList;
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
}
