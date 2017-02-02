package model;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by BORIS on 01.02.17.
 */
public class DateUtil
  {
    public final static LocalDate NOW = LocalDate.of(3000,1,1);

    public static LocalDate of (int year, Month month)
	  {
	    return LocalDate.of(year, month, 1);
	  }
  }
