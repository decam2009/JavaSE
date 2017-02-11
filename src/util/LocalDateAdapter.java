package util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Created by BORIS on 11.02.17.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
  @Override
  public LocalDate unmarshal(String v) throws Exception
    {
	  return LocalDate.parse(v);
    }

  @Override
  public String marshal(LocalDate v) throws Exception
    {
	  return v.toString();
    }
}
