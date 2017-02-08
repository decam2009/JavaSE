package serializer;

import model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by BORIS on 08.02.17.
 */
public interface StreamSerializer
  {
    void doWrite (Resume r, OutputStream os) throws IOException;
    Resume doRead (InputStream is) throws IOException;
  }
