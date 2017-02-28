package sql;

import exception.ExistStorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by BORIS on 26.02.17.
 */
public class SQLHepler
  {
	private final ConnectionFactory connectionFactory;

	public SQLHepler(ConnectionFactory connectionFactory)
	  {
	    this.connectionFactory = connectionFactory;
	  }

	public void execute (String sql)
	{
	  execute(sql, PreparedStatement::execute);
	}

	public <T> T execute (String sql, SQLExecutor<T> executor)
	 {
	   try (Connection conn = connectionFactory.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql))
	   {
		 return executor.execute(preparedStatement);
	   }
	   catch (SQLException e)
	   {
		 throw new ExistStorageException(null);
	   }
	 }
  }
