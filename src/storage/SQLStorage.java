package storage;

import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import sql.ConnectionFactory;

import java.sql.*;
import java.util.List;

/**
 * Created by BORIS on 19.02.17.
 */
public class SQLStorage implements Storage
  {
	public final ConnectionFactory connectionFactory;

	public SQLStorage(String dbUrl, String dbUser, String dbPassword)
	  {
	    connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	  }

	@Override
	public void save(Resume r)
	  {
		try (Connection conn = connectionFactory.getConnection();
			 PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)"))
		{
		  preparedStatement.setString(1, r.getUuid());
		  preparedStatement.setString(2, r.getFullName());
		  preparedStatement.execute();
		}
		catch (SQLException e)
		{
		  throw new StorageException(e);
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
	public void delete(String uuid) {

	}

	@Override
	public int size() {
	  return 0;
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
	public List<Resume> getAllSorted() {
	  return null;
	}

	@Override
	public Resume[] getAll() {
	  return new Resume[0];
	}
  }
