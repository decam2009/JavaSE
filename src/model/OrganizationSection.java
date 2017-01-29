package model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by BORIS on 27.01.17.
 */
public class OrganizationSection extends Section
  {
    private final Map <Organization, List <Organization>> organizations;

	public OrganizationSection (Map <Organization, List<Organization>> organizations)
	  {
		Objects.requireNonNull(organizations, "organization can not be null");
	    this.organizations = organizations;
	  }

	public Map<Organization, List<Organization>> getOrganizations()
	  {
	    return organizations;
	  }

	@Override
	public String toString()
	{
	  return "OrganizationSection{" +
			  "organizations=" + organizations +
			  "} " + super.toString();
	}

	@Override
	public boolean equals(Object o)
	{
	  if (this == o) return true;
	  if (!(o instanceof OrganizationSection)) return false;

	  OrganizationSection that = (OrganizationSection) o;

	  return organizations.equals(that.organizations);
	}

	@Override
	public int hashCode()
	{
	  return organizations.hashCode();
	}
  }
