package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import sql.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by BORIS on 19.02.17.
 */
public class SQLStorage implements Storage
  {
	public final ConnectionFactory connectionFactory;
	private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());
	private final List<Resume> resumeList = new ArrayList<>();

	public SQLStorage(String dbUrl, String dbUser, String dbPassword)
	  {
	    connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	  }

	@Override
	public void save(Resume r)
	  {
		try (Connection conn = connectionFactory.getConnection();
			 PreparedStatement preparedStatement = conn.prepareStatement( "INSERT INTO resume (uuid, full_name) VALUES (?, ?)"))
		{
		  preparedStatement.setString(1, r.getUuid());
		  preparedStatement.setString(2, r.getFullName());
		  preparedStatement.execute();
		}
		catch (SQLException e)
		{
		  throw new ExistStorageException(r.getUuid() + "already exists");
		}

	  }

	@Override
	public Resume get(String uuid) {
	  try (Connection conn = connectionFactory.getConnection();
		   PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM resume WHERE uuid = ?"))
	  {
		preparedStatement.setString(1, uuid);
		ResultSet rs = preparedStatement.executeQuery();
		if (!rs.next())
		  {
		    throw new NotExistStorageException(uuid);
		  }
		Resume r = new Resume(uuid, rs.getString("full_name"));
		return r;
	  }
	  catch (SQLException e)
	  {
		throw new StorageException(e);
	  }
	}

	@Override
	public void delete(String uuid)
	  {
	    try (Connection conn = connectionFactory.getConnection();
	          PreparedStatement ps = conn.prepareStatement("DELETE FROM resume WHERE uuid = ?"))
		  {
		    ps.setString(1, uuid);
			ps.execute();
			int k = ps.executeUpdate();
			if (k == 0)
			  {
			    throw new NotExistStorageException("Not exist" + uuid);
			  }
		  }
		catch (SQLException e)
		  {
		    throw new StorageException(e);
		  }
	  }

	@Override
	public int size()
	  {
		  try (Connection conn = connectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume",
						               ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY))
			{
  				ResultSet rs = ps.executeQuery();
  				rs.last();
  				int rowCount = rs.getRow();
  				rs.beforeFirst();
  				return rowCount;
			}
		 catch (SQLException e)
		   {
		     throw new StorageException(e);
		   }
	  }

	@Override
	public void clear()
	  {
	    try (Connection conn = connectionFactory.getConnection();
			 PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM resume"))
		  {
			preparedStatement.execute();
		  }
		catch (SQLException e)
		  {
		    throw new StorageException(e);
		  }
	  }

	@Override
	public void update(Resume oldR, Resume newR) {

	}

	@Override
	public List<Resume> getAllSorted()
	  {
		  try (Connection conn = connectionFactory.getConnection();
			   PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume"))
			{
			  ResultSet rs = ps.executeQuery();
			  int rowCount = rs.getMetaData().getColumnCount();
			  while (rs.next())
			    {
			      for (int i = 1; i <= rowCount; i++)
				    {
				      Resume r = new Resume(rs.getString("uuid"), rs.getString("full_name"));
					  r.setUuid(r.getUuid().replace(" ", ""));
				      resumeList.add(r);
					}
				}
			}
		 catch (SQLException e)
	 	   {
	  		  e.printStackTrace();
		   }
		 resumeList.sort(RESUME_COMPARATOR);
		 return resumeList;
	  }

	@Override
	public Resume[] getAll()
	  {
	    try (Connection conn = connectionFactory.getConnection();
	         PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume"))
		  {
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			  {
				   Resume r = new Resume(rs.getString("uuid"), rs.getString("full_name"));
				   r.setUuid(r.getUuid().replace(" ", ""));
				   resumeList.add(r);
			  }
		  }
		catch (SQLException e)
		  {
		    throw new StorageException(e);
		  }
		Resume [] resumeArr = new Resume[0];
		return resumeList.toArray(resumeArr);
	  }
  }
