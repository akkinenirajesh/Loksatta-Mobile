package org.loksatta.android.util;

/**
 * Helper class providing constants and intents common to other classes in the
 * app.
 */
public class UrlUtility {

	public static final char SEPERATOR = '/';

	public static final String BASE_URL = "https://mobile.loksatta.org/";

	public static final String FEEDS = "feeds";

	public static final String FEEDS_SINCE = "feeds/since/";

	public static final String LEADERS = "leaders";

	public static String leaderURL(String state, String district,
			String constituency) {
		return LEADERS + SEPERATOR + state + SEPERATOR + district + SEPERATOR
				+ constituency;
	}

}
