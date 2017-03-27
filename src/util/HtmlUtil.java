package util;

import model.Organization;


/**
 * Created by BORIS on 27.03.17.
 */
public class HtmlUtil
  {
	public static boolean isEmpty(String str)
	  {
        return str == null || str.trim().length() == 0;
	  }

	public static String formatDates (Organization.Position position)
	  {
	    return DateUtil.format (position.getStartDate()) + " - " + DateUtil.format(position.getEndDate());
	  }
  }
