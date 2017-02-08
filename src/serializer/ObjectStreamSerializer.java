package serializer;

import exception.StorageException;
import model.Resume;

import java.io.*;

/**
 * Created by BORIS on 03.02.17.
 */
public class ObjectStreamSerializer implements StreamSerializer
  {

	@Override
	public void doWrite(Resume r, OutputStream os) throws IOException
	  {
	    try (ObjectOutputStream oos = new ObjectOutputStream(os))
		  {
		    oos.writeObject(r);
		  }
	  }

	@Override
	public Resume doRead(InputStream is) throws IOException
	  {
	    try (ObjectInputStream ois = new ObjectInputStream(is))
		  {
		    return (Resume) ois.readObject();
		  }
		  catch (ClassNotFoundException e)
		  {
		    throw new StorageException (null, "Error read resume", e);
		  }
	  }
  }
