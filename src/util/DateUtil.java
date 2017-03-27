package util;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * Created by BORIS on 01.02.17.
 */
public class DateUtil
  {
    public final static LocalDate NOW = LocalDate.of(3000,1,1);
    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static LocalDate of (int year, Month month)
	  {
	    return LocalDate.of(year, month, 1);
	  }

	public static String format(LocalDate date)
	{
	  if (date==null)
	    {
	      return "";
		}
		return date.equals(NOW) ? "Сейчас" : date.format(DATE_FORMATTER);
	}

	public static LocalDate parce(String date)
	  {
		if (HtmlUtil.isEmpty(date) || "Сейчас".equals(date)) return NOW;
	    YearMonth yearMonth = YearMonth.parse(date, DATE_FORMATTER);
		return LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1);
	  }
  }
