package sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by BORIS on 19.02.17.
 */
public interface ConnectionFactory
  {
    Connection getConnection() throws SQLException;
  }
