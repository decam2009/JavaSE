package sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by BORIS on 26.02.17.
 */
public interface SQLExecutor <T>
  {
    T execute (PreparedStatement ps) throws SQLException;
  }
