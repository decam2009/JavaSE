package storage;

import exception.StorageException;
import model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by BORIS on 05.02.17.
 */
public class ObjectPathStorage extends AbstractPathStorage
 {
   public ObjectPathStorage(String dir)
   {
	 super(dir);
   }

   @Override
   protected void doWrite(Resume r, Path newos) throws IOException
     {
	   OutputStream os =  Files.newOutputStream(newos);
	   ObjectOutputStream oos = new ObjectOutputStream(os);
	   oos.writeObject(r);
	 }

   @Override
   protected Resume doRead(Path newis) throws IOException
     {
	   try
	     (
	       InputStream is =  Files.newInputStream(newis);
		 )
	     {
		   ObjectInputStream ois = new ObjectInputStream (is);
	       return (Resume) ois.readObject();
	     }
	   catch (ClassNotFoundException e)
	     {
		   throw new StorageException(null,"Error read resume", e);
	     }
	 }
 }
