package model;

import java.io.Serializable;

/**
 * Created by BORIS on 26.01.17.
 */
public enum SectionType implements Serializable
  {
	PERSONAL ("Личные качества"),
	OBJECTIVE ("Позиция"),
	ACHIEVEMENT ("Достижения"),
	QUALIFICATIONS ("Квалификация"),
	EXPERIENCE ("Опыт работы"),
	EDUCATION ("Образование");

	private final String title;

	SectionType(String title)
	{
	  this.title = title;
	}
  }
