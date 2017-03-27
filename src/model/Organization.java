package model;

import util.DateUtil;
import util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static util.DateUtil.NOW;

/**
 * Created by BORIS on 27.01.17.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable
  {
	public Organization()
	  {
	  }

	private static final long serialVersionUID = 1L;
    private Link homePage;
    private List<Position> positions;
    public static final Organization EMPTY = new Organization("","", Position.EMPTY);

	public Organization(String name, String url, Position...positions)
	  {
	    this (new Link(name, url), Arrays.asList(positions));
	  }

	public Organization(Link homePage, List<Position> positions)
	  {
	    this.homePage = homePage;
	    this.positions = positions;
	  }

	public List<Position> getPositions()
	  {
	    return positions;
	  }

	@Override
	public boolean equals(Object o) {
	  if (this == o) return true;
	  if (o == null || getClass() != o.getClass()) return false;

	  Organization that = (Organization) o;

	  return homePage != null ? homePage.equals(that.homePage) : that.homePage == null;
	}

	@Override
	public int hashCode() {
	  return homePage != null ? homePage.hashCode() : 0;
	}

	@Override
	public String toString() {
	  return "Organization{" +
			  "homePage=" + homePage +
			  '}';
	}

	public Link getHomePage()
	  {
	    return homePage;
	  }

	/**
	 * Created by BORIS on 01.02.17.
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Position implements Serializable
	  {
		public static final Position EMPTY = new Position();
	    public Position()
		{
		}
	    @XmlJavaTypeAdapter(LocalDateAdapter.class)
		private LocalDate startDate;
		@XmlJavaTypeAdapter(LocalDateAdapter.class)
		private LocalDate endDate;
		private String title;
		private String description;

		public Position(LocalDate startDate, LocalDate endDate, String title, String description)
		  {
			Objects.requireNonNull(startDate, "startDate can not be empty.");
			Objects.requireNonNull(endDate, "endDate can not be empty.");
			Objects.requireNonNull(title, "Title can not be empty.");
			this.startDate = startDate;
			this.endDate = endDate;
			this.title = title;
			this.description = description;
		  }

		public Position(int startYear, Month startMonth, String title, String description)
		  {
			this (DateUtil.of(startYear, startMonth), NOW, title, description);
		  }

		public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description)
		{
		  this (DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth) , title, description);
		}

		@Override
		public boolean equals(Object o)
		  {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Position position = (Position) o;

			if (!startDate.equals(position.startDate)) return false;
			if (!endDate.equals(position.endDate)) return false;
			if (!title.equals(position.title)) return false;
			return description.equals(position.description);
		  }

		@Override
		public int hashCode()
		  {
			int result = startDate.hashCode();
			result = 31 * result + endDate.hashCode();
			result = 31 * result + title.hashCode();
			result = 31 * result + description.hashCode();
			return result;
		  }

		@Override
		public String toString()
		  {
			return "Position{" +
					  "startDate=" + startDate +
					  ", endDate=" + endDate +
					  ", title='" + title + '\'' +
					  ", description='" + description + '\'' +
				  '}';
		  }

		public LocalDate getStartDate()
		  {
		    return startDate;
		  }

		public LocalDate getEndDate()
		  {
		    return endDate;
		  }

		public String getTitle()
		  {
		    return title;
		  }

		public String getDescription()
		  {
		    return description;
		  }
	  }
  }
