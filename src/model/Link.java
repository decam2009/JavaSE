package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by BORIS on 27.01.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable
  {
	public Link()
	  {
	  }

	private String url;
    private String name;

	public Link(String url, String name)
	{
	  Objects.requireNonNull(name,"Name can not be empty.");
	  this.url = url;
	  this.name = name;
	}

	public String getUrl()
	{
	  return url;
	}

	public String getName()
	{
	  return name;
	}

	@Override
	public String toString() {
	  return "Link{" +
			  "url='" + url + '\'' +
			  ", name='" + name + '\'' +
			  '}';
	}

	@Override
	public boolean equals(Object o) {
	  if (this == o) return true;
	  if (!(o instanceof Link)) return false;

	  Link link = (Link) o;

	  if (url != null ? !url.equals(link.url) : link.url != null) return false;
	  return name.equals(link.name);
	}

	@Override
	public int hashCode() {
	  int result = url != null ? url.hashCode() : 0;
	  result = 31 * result + name.hashCode();
	  return result;
	}
  }
