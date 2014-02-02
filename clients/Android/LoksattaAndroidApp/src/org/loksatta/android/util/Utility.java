package org.loksatta.android.util;

import java.util.Calendar;
import java.util.Date;

public class Utility {

	public static final String LEADER = "Leader";
	public static final String NEWS_FEED = "News Feed";
	public static final String VOLUNTEER = "Volunteer";

	/**
	 * Default Feed Since(2 months)
	 * 
	 * @return
	 */
	public static Date getDefaultFeedSince() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -2);
		return cal.getTime();
	}

}
