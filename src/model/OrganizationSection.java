package model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by BORIS on 27.01.17.
 */
public class OrganizationSection extends Section
  {
    private final List <Organization> organizations;

	public OrganizationSection (List<Organization> organizations)
	  {
		Objects.requireNonNull(organizations, "organization can not be null");
	    this.organizations = organizations;
	  }

	public OrganizationSection(Organization... organizations)
	  {
		this(Arrays.asList(organizations));
      }

	public List<Organization> getOrganizations()
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
