package serializer;

import model.Resume;
import util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by BORIS on 11.02.17.
 */
public class JsonStreamSerializer implements StreamSerializer
  {
	@Override
	public void doWrite(Resume r, OutputStream os) throws IOException
	  {
		try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8))
		{
		  JsonParser.write(r, w);
		}

	  }

	@Override
	public Resume doRead(InputStream is) throws IOException
	  {
		try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8))
		{
		  return JsonParser.Reader(r, Resume.class);
		}
	  }
  }
