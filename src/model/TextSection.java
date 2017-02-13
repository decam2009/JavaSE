package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by BORIS on 27.01.17.
 */
public class TextSection extends Section implements Serializable
{

  public TextSection()
    {
    }

  private static final long serialVersionUID = 1L;
  private String content;

  public String getContent()
    {
	  return content;
    }

  public void setContent(String content)
    {
	  this.content = content;
    }

  @Override
  public boolean equals(Object o)
  {
	if (this == o) return true;
	if (!(o instanceof TextSection)) return false;

	TextSection that = (TextSection) o;

	return content.equals(that.content);
  }

  @Override
  public int hashCode()
  {
	return content.hashCode();
  }

  public TextSection(String content)
    {
	  Objects.requireNonNull(content, "Content can not be empty.");
      this.content = content;
    }
}
