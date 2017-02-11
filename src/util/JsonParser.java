package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Section;

import java.io.Reader;
import java.io.Writer;

/**
 * Created by BORIS on 11.02.17.
 */
public class JsonParser
  {
    private static Gson GSON = new GsonBuilder()
			.registerTypeAdapter(Section.class, new JsonSectionAdapter<>())
			.create();
    public static <T> T Reader (Reader reader, Class<T> clazz)
	  {
	    return GSON.fromJson(reader, clazz);
	  }
	public static <T> void write (T object, Writer writer)
	  {
	    GSON.toJson(object, writer);
	  }

  }
