package util;

import storage.SQLStorage;
import storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by BORIS on 19.02.17.
 */
public class Config {

  private static final File PROPS = new File (getHomeDir(), "./config/resume.properties");
  private static final Config INSTANCE = new Config();
  Properties prop = new Properties();
  private File storageDir;
  private String dbUrl;
  private String dbUser;
  private String dbPassword;
  private Storage storage;

  public static Config getInstance()
    {
	  return INSTANCE;
    }

  private Config()
  {
	try (InputStream is = new FileInputStream(PROPS))
	{
	  prop.load(is);
	  storageDir = new File(prop.getProperty("storage.dir"));
	  dbUrl = prop.getProperty("db.url");
	  dbUser = prop.getProperty("db.user");
	  dbPassword = prop.getProperty("db.password");
	  storage = new SQLStorage(dbUrl, dbUser, dbPassword);
	}
	catch (IOException e)
	{
	  throw new IllegalStateException("Invalid config file" + PROPS.getAbsolutePath());
	}
	catch (SQLException e)
	{
	  e.printStackTrace();
	}
  }

  public File getStorageDir()
  {
	return storageDir;
  }

  public String getDbUrl()
    {
	  return dbUrl;
    }

  public String getDbUser()
    {
	  return dbUser;
    }

  public String getDbPassword()
    {
	  return dbPassword;
    }

  public Storage getStorage()
  {
	return storage;
  }

  private static File getHomeDir ()
  {
    String prop = System.getProperty("homeDir");
    File homeDir = new File (prop == null? "." : prop);
    if (!homeDir.isDirectory())
	  {
		throw new IllegalStateException(homeDir + "is not directory");
	  }
	return homeDir;
  }

}
