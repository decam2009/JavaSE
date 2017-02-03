package model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by BORIS on 27.01.17.
 */
public class ListSection extends Section {
    private final List<String> items;

	public ListSection(List<String> items)
	  {
		Objects.requireNonNull(items, "Item can not be empty.");
	    this.items = items;
	  }

	public ListSection(String...items)
	{
	  Objects.requireNonNull(items, "Item can not be empty.");
	  this.items = Arrays.asList(items);
	}

	public List<String> getItems()
	  {
	    return items;
	  }

	@Override
	public boolean equals(Object o)
	{
	  if (this == o) return true;
	  if (!(o instanceof ListSection)) return false;

	  ListSection that = (ListSection) o;

	  return items.equals(that.items);
	}

	@Override
	public int hashCode()
	{
	  return items.hashCode();
	}

	@Override
	public String toString()
	{
	  return "ListSection{" +
			  "items=" + items +
			  '}';
	}
  }
