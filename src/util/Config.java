package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by BORIS on 19.02.17.
 */
public class Config {

  private static final File PROPS = new File ("./config/resume.properties");
  private static final Config INSTANCE = new Config();
  private Properties prop = new Properties();
  private File storageDir;
  private String dbUrl;
  private String dbUser;
  private String dbPassword;

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
	}
	catch (IOException e)
	{
	  throw new IllegalStateException("Invalid config file" + PROPS.getAbsolutePath());
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
}
