package model;

import java.util.Objects;

/**
 * Created by BORIS on 27.01.17.
 */
public class TextSection extends Section
{
  private final String content;

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
