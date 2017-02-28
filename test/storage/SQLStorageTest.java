package storage;

import util.Config;

import java.sql.SQLException;

/**
 * Created by BORIS on 19.02.17.
 */
public class SQLStorageTest extends AbstractStorageTest
{
  public SQLStorageTest() throws SQLException {
	  super(new SQLStorage(Config.getInstance().getDbUrl(), Config.getInstance().getDbUser(), Config.getInstance().getDbPassword()));
    }
}
