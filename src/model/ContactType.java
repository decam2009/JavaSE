package model;

/**
 * Created by BORIS on 27.01.17.
 */
public enum ContactType
  {
    PHONE ("Телефон"),
    MOBILE ("Мобильный телефон"),
    HOME_PHONE("Домашний телефон"),
	SKYPE("Скайп"),
	MAIL("Почта"),
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
  }
