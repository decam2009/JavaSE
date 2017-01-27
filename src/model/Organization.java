package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by BORIS on 27.01.17.
 */
public class Organization
  {
    private final Link homePage;

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

	public Organization(String name, String url, LocalDate startDate, LocalDate endDate, String title, String description) {
	  Objects.requireNonNull(startDate, "startDate can not be empty.");
	  Objects.requireNonNull(endDate, "endDate can not be empty.");
	  Objects.requireNonNull(title, "Title can not be empty.");
	  this.homePage = new Link(name, url);
	  this.startDate = startDate;
	  this.endDate = endDate;
	  this.title = title;
	  this.description = description;
	}

	@Override
	public boolean equals(Object o) {
	  if (this == o) return true;
	  if (o == null || getClass() != o.getClass()) return false;

	  Organization that = (Organization) o;

	  if (!homePage.equals(that.homePage)) return false;
	  if (!startDate.equals(that.startDate)) return false;
	  if (!endDate.equals(that.endDate)) return false;
	  if (!title.equals(that.title)) return false;
	  return description != null ? description.equals(that.description) : that.description == null;
	}

	@Override
	public int hashCode() {
	  int result = homePage.hashCode();
	  result = 31 * result + startDate.hashCode();
	  result = 31 * result + endDate.hashCode();
	  result = 31 * result + title.hashCode();
	  result = 31 * result + (description != null ? description.hashCode() : 0);
	  return result;
	}

	@Override
	public String toString()
	{
	  return "Organization{" +
			  "homePage=" + homePage +
			  ", startDate=" + startDate +
			  ", endDate=" + endDate +
			  ", title='" + title + '\'' +
			  ", description='" + description + '\'' +
			  '}';
	}
  }
