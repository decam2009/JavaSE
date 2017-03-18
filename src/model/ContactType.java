package model;

/**
 * Created by BORIS on 27.01.17.
 */
public enum ContactType
  {
    PHONE ("Телефон"),
    MOBILE ("Мобильный телефон"),
    HOME_PHONE("Домашний телефон"),
	SKYPE("Скайп")
	  {
		@Override
		public String toHtml0(String value)
		  {
		    return "<a href='skype: " + value + "'>" + value + "</a>";
		  }
	  },
	MAIL("Почта")
	  {
		@Override
		public String toHtml0(String value)
		{
		  return "<a href='mailto: " + value + "'>" + value + "</a>";
		}

	  },
	LINKEDIN("Профиль LynkedIn"),
	GITHUB("Профиль Github"),
	STACKOVERFLOW("Профиль Stackoverflow"),
	FACEBOOK("Профиль Facebook"),
	VK("Профиль VK"),
	INSTAGRAM("Профиль Instagram"),
	HOME_PAGE("Домашняя страница");

	private final String title;

	ContactType(String title)
	  {
	    this.title = title;
	  }

	public String getTitle()
	{
	  return title;
	}

	public String toHtml0 (String value)
	{
	  return title + ":" + value;
	}

	public String toHtml (String value)
	  {
	    return (value == null) ? "" : toHtml0(value);
	  }
  }
