package sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by BORIS on 02.03.17.
 */
public interface SQLTransaction <T>
  {
    T execute (Connection conn) throws SQLException;
  }
